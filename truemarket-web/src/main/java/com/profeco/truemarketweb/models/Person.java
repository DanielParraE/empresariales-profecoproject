package com.profeco.truemarketweb.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    private String userId;

    private String fullName;
    private String lastName;
    private String marketId;
    private String authenticationType;
    private String password;
}