package com.map.nudonado.booth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

}
