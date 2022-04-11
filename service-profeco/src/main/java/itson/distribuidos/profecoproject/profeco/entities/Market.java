package itson.distribuidos.profecoproject.profeco.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_markets")
@Data  @AllArgsConstructor  @NoArgsConstructor @Builder
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String rfc;
    @Column(name = "web_page")
    private String webPage;
}
