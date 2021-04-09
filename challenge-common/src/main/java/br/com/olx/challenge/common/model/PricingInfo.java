package br.com.olx.challenge.common.model;

import br.com.olx.challenge.common.enums.BusinessType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricingInfo {

    private String period;
    private BusinessType businessType;
    private BigDecimal yearlyIptu;
    private BigDecimal price;

    private BigDecimal rentalTotalPrice;
    private BigDecimal monthlyCondoFee;

}
