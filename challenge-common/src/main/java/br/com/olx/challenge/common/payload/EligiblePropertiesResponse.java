package br.com.olx.challenge.common.payload;

import br.com.olx.challenge.common.model.Property;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EligiblePropertiesResponse {

    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalCount;
    @JsonProperty("listings")
    private List<Property> listEligibleProperties;

}
