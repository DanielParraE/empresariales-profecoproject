package itson.distribuidos.profecoproject.consumer.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor  @NoArgsConstructor  @Builder
public class Market {
    private Long id;
    private String name;
    private String rfc;
    private String webPage;
}
