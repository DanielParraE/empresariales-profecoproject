package itson.distribuidos.profecoproject.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reviews")
@Data  @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private float rating;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_review")
    private TypeReview typeReview;
}
