package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.config.VivaRealParameter;
import br.com.olx.challenge.common.enums.BusinessType;
import br.com.olx.challenge.common.model.Location;
import br.com.olx.challenge.common.model.Property;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VivaRealPortalTest {

    private final EasyRandom random = new EasyRandom();

    @InjectMocks
    private VivaRealPortal vivaRealPortal;

    @Mock
    private VivaRealParameter vivaRealParameter;

    @Mock
    private BoundingBox boundingBox;

    @BeforeEach
    void init(){
        when(boundingBox.getMinLat()).thenReturn(Double.valueOf(1d));
        when(boundingBox.getMaxLat()).thenReturn(Double.valueOf(100d));
        when(boundingBox.getMinLon()).thenReturn(Double.valueOf(1d));
        when(boundingBox.getMaxLon()).thenReturn(Double.valueOf(100d));

        when(vivaRealParameter.getMaximumPriceForSale()).thenReturn(BigDecimal.valueOf(100d));
        when(vivaRealParameter.getMaximumPriceForRental(true)).thenReturn(BigDecimal.valueOf(100d));
        when(vivaRealParameter.getRentalPriceDiscount()).thenReturn(BigDecimal.valueOf(10d));
    }

    @Test
    void should_filter_properties_for_portal() {
        vivaRealPortal.filterPropertiesForPortal(initListOfPropertiesForVivaRealPortal());
        assertEquals(2, vivaRealPortal.getListEligibleProperty().size());
    }

    private Property getValidProperty() {
        return random.nextObject(Property.class);
    }

    private Property getEligiblePropertyForRental(){
        Property property = getValidProperty();
        property.getPricingInfos().setBusinessType(BusinessType.RENTAL);
        property.getPricingInfos().setRentalTotalPrice(BigDecimal.valueOf(80d));
        property.setUsableAreas(1d);
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(10d).lat(10d).build());
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.TEN);
        return property;
    }

    private Property getEligiblePropertyForSale(){
        Property property = getValidProperty();
        property.getPricingInfos().setBusinessType(BusinessType.SALE);
        property.getPricingInfos().setPrice(BigDecimal.valueOf(99d));
        property.setUsableAreas(1d);
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(10d).lat(10d).build());
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.TEN);
        return property;
    }

    private List<Property> initListOfPropertiesForVivaRealPortal(){
        return List.of(getEligiblePropertyForRental(), getEligiblePropertyForSale());
    }

}
