package br.com.olx.challenge.common.service;

import br.com.olx.challenge.common.enums.BusinessType;
import br.com.olx.challenge.common.model.Location;
import br.com.olx.challenge.common.model.Property;
import br.com.olx.challenge.common.payload.EligiblePropertiesResponse;
import br.com.olx.challenge.common.portal.Portal;
import br.com.olx.challenge.common.portal.PortalSupplier;
import br.com.olx.challenge.common.portal.ZapPortal;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PortalPropertyServiceTest {

    private final EasyRandom random = new EasyRandom();

    @Mock
    private PortalSupplier portalSupplier;

    @Mock
    private ZapPortal zapPortal;

    @InjectMocks
    private PortalPropertyService portalPropertyService;

    @Test
    void should_get_properties_from_portal_return_eligible_zap_properties() {
        Mockito.when(portalSupplier.supplyPortal(Portal.ZAP.name)).thenReturn(zapPortal);
        Mockito.when(zapPortal.getListEligibleProperty()).thenReturn(initListOfPropertiesForZapPortal());
        EligiblePropertiesResponse response = portalPropertyService.getPropertiesFromPortal(Portal.ZAP.name, 0, 1);

        assertEquals(1, response.getListEligibleProperties().size());
        assertEquals(2, response.getTotalCount());
        assertEquals(0, response.getPageNumber());
        assertEquals(1, response.getPageSize());
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
