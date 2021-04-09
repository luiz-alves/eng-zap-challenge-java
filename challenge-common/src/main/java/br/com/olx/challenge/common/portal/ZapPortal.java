package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.config.ZapParameter;
import br.com.olx.challenge.common.enums.BusinessType;
import br.com.olx.challenge.common.model.Property;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Getter
@RequiredArgsConstructor
public class ZapPortal extends AbstractPortal {

    private final ZapParameter zapParameter;
    private final BoundingBox boundingBox;

    @Override
    public void filterPropertiesForPortal(final List<Property> listProperty) {
        super.filterPropertiesForPortal(listProperty);
        super.setListEligibleProperty(super.getListEligibleProperty().stream().filter(property -> filterForRental(property) || filterForSale(property)).collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public String getPortal() {
        return Portal.ZAP.name;
    }

    private Boolean filterForRental(Property property){
        return property.getBusinessType().equals(BusinessType.RENTAL)
            && property.getRentalPrice().compareTo(zapParameter.getMinimumPriceForRental()) >= 0;
    }

    private Boolean filterForSale(Property property){
        return property.getBusinessType().equals(BusinessType.SALE)
            && property.getSquareMeterPrice().compareTo(zapParameter.getMinimumSquareMeterPrice()) > 0
            && validateMinimumPriceForSale(property);
    }

    private Boolean validateMinimumPriceForSale(Property property) {
        return property.getSalePrice().compareTo(zapParameter.getMinimumPriceForSale(property.isInsideOfBoundingBox(boundingBox))) >= 0;
    }

}
