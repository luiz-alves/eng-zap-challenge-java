package br.com.olx.challenge.common.model;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.enums.BusinessType;
import br.com.olx.challenge.common.enums.ListingStatus;
import br.com.olx.challenge.common.enums.ListingType;
import br.com.olx.challenge.common.util.LocationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Property {

    private String id;
    private Double usableAreas;
    private ListingType listingType;
    private ListingStatus listingStatus;
    private Integer parkingSpaces;
    private Boolean owner;
    private List<URL> images;
    private PropertyAddress address;
    private Integer bathrooms;
    private Integer bedrooms;
    private PricingInfo pricingInfos;
    private String createdAt;
    private String updatedAt;

    public Boolean hasGeoPosition(){
        try{
            Location location = getLocation();
            return location.getLat().compareTo(Double.valueOf(0d)) != 0
                && location.getLon().compareTo(Double.valueOf(0d)) != 0;
        }catch(Exception e){
            return false;
        }
    }

    @JsonIgnore
    public BigDecimal getSquareMeterPrice(){
        return pricingInfos.getPrice().divide(BigDecimal.valueOf(usableAreas), 2, RoundingMode.HALF_UP);
    }

    public Boolean isInsideOfBoundingBox(BoundingBox boundingBox){
        return LocationUtils.isInsideBoundingBox(getLocation(), boundingBox);
    }

    @JsonIgnore
    private Location getLocation(){
        return address.getGeoLocation().getLocation();
    }

    @JsonIgnore
    public BigDecimal getSalePrice(){
        return pricingInfos.getPrice();
    }

    @JsonIgnore
    public BigDecimal getRentalPrice(){
        return pricingInfos.getRentalTotalPrice();
    }

    @JsonIgnore
    public BigDecimal getCondoFee(){
        return pricingInfos.getMonthlyCondoFee();
    }

    @JsonIgnore
    public BusinessType getBusinessType(){
        return pricingInfos.getBusinessType();
    }

    public Boolean isCondoFeeLessThanRentalPriceWithDiscount(BigDecimal rentalPriceDiscount){
        return getCondoFee().compareTo(calculateRentalPriceWithDiscount(rentalPriceDiscount)) < 0;
    }

    private BigDecimal calculateRentalPriceWithDiscount(BigDecimal rentalPriceDiscount){
        return getRentalPrice().multiply(BigDecimal.ONE.add(rentalPriceDiscount.divide(BigDecimal.valueOf(100))));
    }

}
