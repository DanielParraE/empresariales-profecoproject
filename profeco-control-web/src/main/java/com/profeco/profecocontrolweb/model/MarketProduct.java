package com.profeco.profecocontrolweb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketProduct implements Serializable {

    private Long id;

    private Market market;

    private Product product;

    private List<Inconsistency> inconsistencies;

    private float price;
}
