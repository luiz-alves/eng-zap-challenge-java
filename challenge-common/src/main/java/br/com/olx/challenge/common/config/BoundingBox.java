package br.com.olx.challenge.common.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("bounding-box")
public class BoundingBox {

    private Double minLon;
    private Double minLat;
    private Double maxLon;
    private Double maxLat;

}
