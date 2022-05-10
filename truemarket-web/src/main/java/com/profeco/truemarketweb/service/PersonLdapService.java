package com.profeco.truemarketweb.service;

import com.profeco.truemarketweb.models.Person;
import com.profeco.truemarketweb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class PersonLdapService implements PersonRepository {
    public static final String BASE_DN = "dc=profeco,dc=org";
    @Autowired
    private LdapTemplate ldapTemplate;
    @Override
    public String create(Person p) {
        Name dn = buildDn(p.getUserId());
        ldapTemplate.bind(dn, null, buildAttributes(p));
        return p.getUserId() + " created successfully";
    }
    @Override
    public String update(Person p) {
        Name dn = buildDn(p.getUserId());
        ldapTemplate.rebind(dn, null, buildAttributes(p));
        return p.getUserId() + " updated successfully";
    }
    @Override
    public String remove(String userId) {
        Name dn = buildDn(userId);
        // ldapTemplate.unbind(dn, true); //Remove recursively all entries
        ldapTemplate.unbind(dn);
        return userId + " removed successfully";
    }
    private Attributes buildAttributes(Person p) {
        BasicAttribute ocattr = new BasicAttribute("objectclass");
        ocattr.add("top");
        ocattr.add("person");
        Attributes attrs = new BasicAttributes();
        attrs.put(ocattr);
        attrs.put("uid", p.getUserId());
        attrs.put("cn", p.getFullName());
        attrs.put("sn", p.getLastName());
        attrs.put("marketId", p.getMarketId());
        attrs.put("authenticationType", p.getAuthenticationType());
        attrs.put("userPassword", p.getPassword());
        return attrs;
    }
    public Name buildDn(String userId) {
        return LdapNameBuilder.newInstance(BASE_DN).add("ou", "markets").add("uid", userId).build();
    }
    public Name buildBaseDn() {
        return LdapNameBuilder.newInstance(BASE_DN).add("ou", "markets").build();
    }
    @Override
    public List<Person> findAll() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        List<Person> people = ldapTemplate.search(query().where("objectclass").is("person").and("marketId").isPresent(),
                new PersonAttributeMapper());
        return people;
    }

    @Override
    public Person findByUID(String uid) {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        List<Person> people = ldapTemplate.search(query().where("objectclass").is("person").and("marketId").isPresent().and("uid").is(uid),
                new PersonAttributeMapper());
        return (people.isEmpty()) ? null: people.get(0);
    }

    private class PersonAttributeMapper implements AttributesMapper<Person> {
        @Override
        public Person mapFromAttributes(Attributes attributes) throws NamingException {
            Person person = new Person();
            person.setUserId(null != attributes.get("uid") ? attributes.get("uid").get().toString() : null);
            person.setFullName(null != attributes.get("cn") ? attributes.get("cn").get().toString() : null);
            person.setLastName(null != attributes.get("sn") ? attributes.get("sn").get().toString() : null);
            person.setMarketId(
                    null != attributes.get("marketId") ? attributes.get("marketId").get().toString() : null);
            person.setAuthenticationType(
                    null != attributes.get("authenticationType") ? attributes.get("authenticationType").get().toString() : null);
            return person;
        }
    }
}