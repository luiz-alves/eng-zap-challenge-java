package br.com.olx.challenge.common.model;

import br.com.olx.challenge.common.config.BoundingBox;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class PropertyTest {

    private final EasyRandom random = new EasyRandom();

    @Test
    void should_get_square_meter_returns_value() {
        Property property = getValidProperty();
        Assertions.assertNotNull(property.getSquareMeterPrice());
    }

    @Test
    void should_has_geo_position_returns_true() {
        Property property = getValidProperty();
        Assertions.assertTrue(property.hasGeoPosition());
    }

    @Test
    void should_is_inside_of_bounding_box_returns_true() {
        BoundingBox boundingBox = BoundingBox.builder()
            .minLat(1d)
            .maxLat(10d)
            .minLon(1d)
            .maxLon(10d).build();

        Location location = Location.builder()
            .lon(5d)
            .lat(5d)
            .build();

        Property property = getValidProperty();
        property.getAddress().getGeoLocation().setLocation(location);

        Assertions.assertTrue(property.isInsideOfBoundingBox(boundingBox));
    }

    @Test
    void should_is_inside_of_bounding_box_returns_false() {
        BoundingBox boundingBox = BoundingBox.builder()
            .minLat(1d)
            .maxLat(10d)
            .minLon(1d)
            .maxLon(10d).build();

        Location location = Location.builder()
            .lon(15d)
            .lat(5d)
            .build();

        Property property = getValidProperty();
        property.getAddress().getGeoLocation().setLocation(location);

        Assertions.assertFalse(property.isInsideOfBoundingBox(boundingBox));
    }

    @Test
    void should_is_condo_fee_less_than_rental_price_with_discount_returns_true() {
        Property property = getValidProperty();
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.valueOf(50));
        property.getPricingInfos().setRentalTotalPrice(BigDecimal.valueOf(100));

        Assertions.assertTrue(property.isCondoFeeLessThanRentalPriceWithDiscount(BigDecimal.valueOf(30)));
    }

    @Test
    void should_is_condo_fee_less_than_rental_price_with_discount_returns_false() {
        Property property = getValidProperty();
        property.getPricingInfos().setMonthlyCondoFee(BigDecimal.valueOf(1000));
        property.getPricingInfos().setRentalTotalPrice(BigDecimal.valueOf(100));

        Assertions.assertFalse(property.isCondoFeeLessThanRentalPriceWithDiscount(BigDecimal.valueOf(30)));
    }

    private Property getValidProperty() {
        return random.nextObject(Property.class);
    }

}
