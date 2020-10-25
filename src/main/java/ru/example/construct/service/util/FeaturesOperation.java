package ru.example.construct.service.util;

import org.locationtech.jts.geom.Geometry;
import ru.example.construct.repository.dto.FeatureDto;

import java.util.List;
import java.util.Properties;

public class FeaturesOperation {

    public static final String COLOR = "color";
    public static final String RED = "red";
    public static final String BLUE = "blue";

    public static void intersectsLineStrings(List<FeatureDto> list) {
        FeatureDto featureOne;
        FeatureDto featureTwo;
        Geometry geometryOne;
        Geometry geometryTwo;
        Properties propertiesOne;
        Properties propertiesTwo;

        for (int i = 0; i < list.size() - 1; i++) {
            featureOne = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                featureTwo = list.get(j);

                geometryOne = featureOne.getGeometry();
                geometryTwo = featureTwo.getGeometry();
                if (geometryOne.intersects(geometryTwo)) {
                    propertiesOne = featureOne.getProperties();
                    propertiesTwo = featureTwo.getProperties();

                    propertiesOne.put(COLOR, RED);
                    propertiesTwo.put(COLOR, RED);
                }
            }
        }

        list.forEach((feature) -> {
            if (!feature.getProperties().containsKey(COLOR) || !feature.getProperties().get(COLOR).equals(RED)) {
                feature.getProperties().put(COLOR, BLUE);
            }
        });
    }
}
