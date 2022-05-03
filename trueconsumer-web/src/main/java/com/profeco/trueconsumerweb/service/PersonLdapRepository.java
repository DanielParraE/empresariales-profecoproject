package com.profeco.trueconsumerweb.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import java.util.List;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;

import com.profeco.trueconsumerweb.models.Person;
import com.profeco.trueconsumerweb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

@Service
public class PersonLdapRepository implements PersonRepository {
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
        attrs.put("consumerId", p.getConsumerid());
        return attrs;
    }
    public Name buildDn(String userId) {
        return LdapNameBuilder.newInstance(BASE_DN).add("ou", "consumers").add("uid", userId).build();
    }
    public Name buildBaseDn() {
        return LdapNameBuilder.newInstance(BASE_DN).add("ou", "consumers").build();
    }
    @Override
    public List<Person> retrieve() {
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        List<Person> people = ldapTemplate.search(query().where("objectclass").is("person").and("consumerid").isPresent(),
                new PersonAttributeMapper());
        return people;
    }
    private class PersonAttributeMapper implements AttributesMapper<Person> {
        @Override
        public Person mapFromAttributes(Attributes attributes) throws NamingException {
            Person person = new Person();
            person.setUserId(null != attributes.get("uid") ? attributes.get("uid").get().toString() : null);
            person.setFullName(null != attributes.get("cn") ? attributes.get("cn").get().toString() : null);
            person.setLastName(null != attributes.get("sn") ? attributes.get("sn").get().toString() : null);
            person.setConsumerid(
                    null != attributes.get("consumerid") ? attributes.get("consumerid").get().toString() : null);
            return person;
        }
    }
}