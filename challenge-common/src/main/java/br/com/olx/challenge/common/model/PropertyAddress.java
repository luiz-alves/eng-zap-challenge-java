package br.com.olx.challenge.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PropertyAddress {

    private String city;
    private String neighborhood;
    private GeoLocation geoLocation;

}
