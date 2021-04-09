package br.com.olx.challenge.common.portal;

import br.com.olx.challenge.common.exception.UnloadedDatabaseException;
import br.com.olx.challenge.common.model.Property;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public abstract class AbstractPortal {

    private List<Property> listEligibleProperty;

    public void filterPropertiesForPortal(List<Property> listProperty){
        listEligibleProperty = removeAllUnableProperties(listProperty);
    }

    public abstract String getPortal();

    private List<Property> removeAllUnableProperties(List<Property> listProperty){
        return listProperty.stream()
            .filter(property -> {
                return hasGeoPosition(property)
                    && hasUsableArea(property)
                    && hasValidCondoFee(property);
            })
            .collect(Collectors.toList());
    }

    private Boolean hasGeoPosition(Property property){
        return property.hasGeoPosition();
    }

    private Boolean hasUsableArea(Property property){
        return property.getUsableAreas() > Double.valueOf(0);
    }

    private Boolean hasValidCondoFee(Property property){
        return property.getCondoFee() != null && property.getCondoFee().compareTo(BigDecimal.ZERO)>0;
    }

    public List<Property> getListEligibleProperty(){
        if (listEligibleProperty == null){
            throw new UnloadedDatabaseException("UNLOADED_DATABASE_ERROR_CODE");
        }
        return listEligibleProperty;
    }

}
