package com.profeco.consumer.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketDTO {
    private String UUID;
    private String name;
    private String rfc;
    private String image;
    private Date messageDate;
}
