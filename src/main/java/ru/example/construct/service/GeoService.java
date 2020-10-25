package ru.example.construct.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.example.construct.repository.FeatureRepository;
import ru.example.construct.repository.dto.FeatureCollectionDto;
import ru.example.construct.repository.dto.FeatureDto;
import ru.example.construct.repository.entity.Feature;
import ru.example.construct.repository.mapper.FeatureMapper;
import ru.example.construct.repository.specification.GeoSpecification;
import ru.example.construct.service.util.FeaturesOperation;
import ru.example.construct.service.util.GeoJsonParser;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GeoService {

    private final FeatureRepository repository;

    @Autowired
    public GeoService(FeatureRepository featureRepository) {
        this.repository = featureRepository;
    }

    public FeatureCollectionDto intersection(String geoJson) {
        FeatureCollectionDto featureCollection = GeoJsonParser.fromJsonToFeatureCollectionOfLineStrings(geoJson);
        List<FeatureDto> list = Objects.requireNonNull(featureCollection).getFeatures();
        FeaturesOperation.intersectsLineStrings(list);

        List<Feature> features = list.stream().map(FeatureMapper::fromDtoToEntity).collect(Collectors.toList());
        features = StreamSupport.stream(repository.saveAll(features).spliterator(), false).collect(Collectors.toList());
        if (features.isEmpty()) {
            return null;
        }

        return featureCollection;
    }

    public FeatureCollectionDto getFeatureCollectionByState(boolean state) {
        List<Feature> features = repository.findAll(GeoSpecification.getFeatureWithColorRedOrBlue(state));
        List<FeatureDto> featureDtos = features.stream().map(FeatureMapper::fromEntityToDto).collect(Collectors.toList());

        return new FeatureCollectionDto(GeoJsonParser.FEATURE_COLLECTION, featureDtos);
    }
}
