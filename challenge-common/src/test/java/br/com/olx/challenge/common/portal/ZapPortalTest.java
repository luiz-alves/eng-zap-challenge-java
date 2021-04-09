package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.config.ZapParameter;
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
class ZapPortalTest {

    private final EasyRandom random = new EasyRandom();

    @InjectMocks
    private ZapPortal zapPortal;

    @Mock
    private ZapParameter zapParameter;

    @Mock
    private BoundingBox boundingBox;

    @BeforeEach
    void init(){
        when(boundingBox.getMinLat()).thenReturn(Double.valueOf(1d));
        when(boundingBox.getMaxLat()).thenReturn(Double.valueOf(100d));
        when(boundingBox.getMinLon()).thenReturn(Double.valueOf(1d));
        when(boundingBox.getMaxLon()).thenReturn(Double.valueOf(100d));

        when(zapParameter.getMinimumPriceForRental()).thenReturn(BigDecimal.valueOf(100d));
        when(zapParameter.getMinimumPriceForSale(true)).thenReturn(BigDecimal.valueOf(100d));
        when(zapParameter.getMinimumSquareMeterPrice()).thenReturn(BigDecimal.valueOf(100d));
    }

    @Test
    void should_filter_properties_for_portal() {
        zapPortal.filterPropertiesForPortal(initListOfPropertiesForZapPortal());
        assertEquals(2, zapPortal.getListEligibleProperty().size());
    }

    private Property getValidProperty() {
        return random.nextObject(Property.class);
    }

    private Property getEligiblePropertyForRental(){
        Property property = getValidProperty();
        property.getPricingInfos().setBusinessType(BusinessType.RENTAL);
        property.getPricingInfos().setRentalTotalPrice(BigDecimal.valueOf(101d));
        property.setUsableAreas(100d);
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(10d).lat(10d).build());
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.TEN);
        return property;
    }

    private Property getEligiblePropertyForSale(){
        Property property = getValidProperty();
        property.getPricingInfos().setBusinessType(BusinessType.SALE);
        property.setUsableAreas(1d);
        property.getPricingInfos().setPrice(BigDecimal.valueOf(101d));
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(10d).lat(10d).build());
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.TEN);
        return property;
    }

    private List<Property> initListOfPropertiesForZapPortal(){
        return List.of(getEligiblePropertyForRental(), getEligiblePropertyForSale());
    }

}
