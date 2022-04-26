package com.profeco.market.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketDTO implements Serializable {
    private String UUID;
    private String name;
    private String rfc;
    private String image;
    private Date messageDate;
}
