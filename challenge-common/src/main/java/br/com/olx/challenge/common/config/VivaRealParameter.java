package br.com.olx.challenge.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties("vivareal.parameter")
@Setter
public class VivaRealParameter {

    @Getter
    private BigDecimal maximumPriceForSale;

    private BigDecimal maximumPriceForRental;

    private BigDecimal boundingBoxAreaRentalFee;

    @Getter
    private BigDecimal rentalPriceDiscount;

    public BigDecimal getMaximumPriceForRental(Boolean insideBoundingBox){
        return insideBoundingBox ? calculateMaximumPriceForRentalWhenInsideBoundingBox() : maximumPriceForRental;
    }

    private BigDecimal calculateMaximumPriceForRentalWhenInsideBoundingBox(){
        return maximumPriceForRental.multiply(BigDecimal.ONE.add(boundingBoxAreaRentalFee.divide(BigDecimal.valueOf(100))));
    }

}
