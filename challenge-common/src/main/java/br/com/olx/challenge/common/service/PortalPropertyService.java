package br.com.olx.challenge.common.service;

import br.com.olx.challenge.common.model.Property;
import br.com.olx.challenge.common.payload.EligiblePropertiesResponse;
import br.com.olx.challenge.common.portal.AbstractPortal;
import br.com.olx.challenge.common.portal.PortalSupplier;
import br.com.olx.challenge.common.util.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortalPropertyService {

    private final PortalSupplier portalSupplier;

    public EligiblePropertiesResponse getPropertiesFromPortal(String portalName, Integer page, Integer size){

        AbstractPortal portal = portalSupplier.supplyPortal(portalName);
        Pageable<Property> pageableProperty = new Pageable<>(page, size, portal.getListEligibleProperty());
        return EligiblePropertiesResponse.builder()
            .listEligibleProperties(pageableProperty.getPageOfFullList())
            .pageNumber(page)
            .pageSize(size)
            .totalCount(portal.getListEligibleProperty().size())
            .build();

    }


}
