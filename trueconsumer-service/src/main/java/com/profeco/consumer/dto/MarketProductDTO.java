package com.profeco.consumer.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.profeco.consumer.entities.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketProductDTO {
    private Long id;

    private MarketDTO market;

    private Double price;
}
