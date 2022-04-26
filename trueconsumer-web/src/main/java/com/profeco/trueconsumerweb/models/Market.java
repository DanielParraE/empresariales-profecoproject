package com.profeco.trueconsumerweb.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Market implements Serializable {
    private int id;
    private String name;
    private String webPage;
    private String image;
}
