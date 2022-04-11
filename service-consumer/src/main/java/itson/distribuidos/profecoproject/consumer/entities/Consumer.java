package itson.distribuidos.profecoproject.consumer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_consumers")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;
    private String rfc;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
}
