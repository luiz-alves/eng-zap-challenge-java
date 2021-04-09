package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.config.VivaRealParameter;
import br.com.olx.challenge.common.enums.BusinessType;
import br.com.olx.challenge.common.model.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@RequiredArgsConstructor
public class VivaRealPortal extends AbstractPortal {

    private final VivaRealParameter vivaRealParameter;
    private final BoundingBox boundingBox;

    @Override
    public void filterPropertiesForPortal(final List<Property> listProperty) {
        super.filterPropertiesForPortal(listProperty);
        super.setListEligibleProperty(super.getListEligibleProperty().stream().filter(property -> filterForRental(property) || filterForSale(property)).collect(
            Collectors.toUnmodifiableList()));
    }

    @Override
    public String getPortal() {
        return Portal.VIVAREAL.name;
    }

    private Boolean filterForRental(Property property){
        return property.getBusinessType().equals(BusinessType.RENTAL)
            && property.isCondoFeeLessThanRentalPriceWithDiscount(vivaRealParameter.getRentalPriceDiscount())
            && validateMaximumPriceForRental(property);
    }

    private Boolean filterForSale(Property property){
        return property.getBusinessType().equals(BusinessType.SALE)
            && property.getSalePrice().compareTo(vivaRealParameter.getMaximumPriceForSale()) <= 0;
    }

    private Boolean validateMaximumPriceForRental(Property property) {
        return property.getRentalPrice().compareTo(vivaRealParameter.getMaximumPriceForRental(property.isInsideOfBoundingBox(boundingBox))) <= 0;
    }

}
