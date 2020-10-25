package ru.example.construct.service;

import org.springframework.stereotype.Service;
import ru.example.construct.repository.dto.FeatureCollectionDto;
import ru.example.construct.repository.dto.FeatureDto;
import ru.example.construct.service.util.FeaturesOperation;
import ru.example.construct.service.util.GeoJsonParser;

import java.util.*;

@Service
public class GeoService {

    public FeatureCollectionDto intersection(String geoJson) {
        FeatureCollectionDto featureCollection = GeoJsonParser.fromJsonToFeatureCollectionOfLineStrings(geoJson);
        List<FeatureDto> list = Objects.requireNonNull(featureCollection).getFeatures();
        FeaturesOperation.intersectsLineStrings(list);
        return featureCollection;
    }

    public String getFeatureCollectionByState(boolean state) {
        return Boolean.toString(state);
    }
}
