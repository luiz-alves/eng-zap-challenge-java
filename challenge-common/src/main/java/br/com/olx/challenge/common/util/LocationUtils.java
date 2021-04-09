package br.com.olx.challenge.common.util;

import br.com.olx.challenge.common.config.BoundingBox;
import br.com.olx.challenge.common.model.Location;

public class LocationUtils {

    public static Boolean isInsideBoundingBox(Location location, BoundingBox boundingBox){
        return isBetweenLat(location, boundingBox) && isBetweenLon(location, boundingBox);
    }

    private static Boolean isBetweenLat(Location location, BoundingBox boundingBox){
        return isBetweenValue(location.getLat(), boundingBox.getMinLat(), boundingBox.getMaxLat());
    }

    private static Boolean isBetweenLon(Location location, BoundingBox boundingBox){
        return isBetweenValue(location.getLon(), boundingBox.getMinLon(), boundingBox.getMaxLon());
    }

    private static Boolean isBetweenValue(Double value, Double min, Double max){
        return value >= min && value <= max;
    }

}
