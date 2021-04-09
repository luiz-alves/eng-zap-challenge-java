package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.exception.UnloadedDatabaseException;
import br.com.olx.challenge.common.model.Location;
import br.com.olx.challenge.common.model.Property;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AbstractPortalTest {

    private AbstractPortal abstractPortal;

    private final EasyRandom random = new EasyRandom();

    @BeforeEach
    void init(){
        abstractPortal = Mockito.mock(AbstractPortal.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    void should_filter_properties_for_portal() {
        abstractPortal.filterPropertiesForPortal(initListOfPropertiesForAbstractPortal());
        assertEquals(1, abstractPortal.getListEligibleProperty().size());
    }

    @Test
    void should_get_list_eligible_property_throwsException() {
        assertThrows(UnloadedDatabaseException.class, () -> abstractPortal.getListEligibleProperty());
    }

    @Test
    void should_get_list_eligible_property_returns_list() {
        abstractPortal.filterPropertiesForPortal(initListOfPropertiesForAbstractPortal());
        assertDoesNotThrow(() -> abstractPortal.getListEligibleProperty());
        assertNotNull(abstractPortal.getListEligibleProperty());
    }

    private Property getValidProperty() {
        return random.nextObject(Property.class);
    }

    private Property getPropertyLocationToZero(){
        Property property = getValidProperty();
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(Double.valueOf(0d)).lat(Double.valueOf(0d)).build());
        return property;
    }

    private Property getPropertyCondoFeeToZero(){
        Property property = getValidProperty();
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.ZERO);
        return property;
    }

    private Property getPropertyUsableAreaToZero(){
        Property property = getValidProperty();
        property.setUsableAreas(Double.valueOf(0d));
        return property;
    }

    private Property getEligibleProperty(){
        Property property = getValidProperty();
        property.setUsableAreas(100d);
        property.getAddress().getGeoLocation().setLocation(Location.builder().lon(10d).lat(10d).build());
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.TEN);
        return property;
    }


    private List<Property> initListOfPropertiesForAbstractPortal(){
        return List.of(getPropertyCondoFeeToZero(), getPropertyUsableAreaToZero(), getPropertyLocationToZero(), getEligibleProperty());
    }


}
