package com.profeco.consumer.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketDTO {
    private Long id;
    private String name;
    private String rfc;
    private String image;
    private String webPage;
    private List<MarketProductDTO> marketProductList;
}
