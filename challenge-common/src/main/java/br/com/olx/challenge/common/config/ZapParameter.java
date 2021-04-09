package br.com.olx.challenge.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@ConfigurationProperties("zap.parameter")
@Setter

public class ZapParameter {

    @Getter
    private BigDecimal minimumPriceForRental;

    private BigDecimal minimumPriceForSale;

    private BigDecimal boundingBoxAreaSaleDiscount;

    @Getter
    private BigDecimal minimumSquareMeterPrice;

    public BigDecimal getMinimumPriceForSale(Boolean insideBoundingBox){
        return insideBoundingBox ? calculateMinimumPriceForSaleWhenInsideBoundingBox() : minimumPriceForSale;
    }

    private BigDecimal calculateMinimumPriceForSaleWhenInsideBoundingBox(){
        return minimumPriceForSale.multiply(BigDecimal.ONE.subtract(boundingBoxAreaSaleDiscount.divide(BigDecimal.valueOf(100))));
    }

}
