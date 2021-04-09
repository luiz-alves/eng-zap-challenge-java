package br.com.olx.challenge.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GeoLocation {

    private String precision;
    private Location location;

}
