package itson.distribuidos.profecoproject.consumer.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import itson.distribuidos.profecoproject.consumer.models.Market;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_complains")
@Data @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Complain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private String description;

    private String evidence;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "consumer_id")
    private Consumer author;

    @Column(name = "market_id")
    private Long marketID;

    @Transient
    private Market market;
}
