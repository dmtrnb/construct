package ru.example.construct.repository.mapper;

import ru.example.construct.repository.dto.FeatureDto;
import ru.example.construct.repository.entity.Feature;

public class FeatureMapper {
    public static FeatureDto fromEntityToDto(Feature feature) {
        FeatureDto featureDto = new FeatureDto();

        featureDto.setType(feature.getType());
        featureDto.setProperties(PropertyMapper.fromEntityToDto(feature.getProperties()));
        featureDto.setGeometryDto(GeometryMapper.fromEntityToDto(feature.getGeometry()));

        return featureDto;
    }

    public static Feature fromDtoToEntity(FeatureDto featureDto) {
        Feature feature = Feature.builder()
                .type(featureDto.getType())
                .geometry(GeometryMapper.fromDtoToEntity(featureDto.getGeometryDto()))
                .properties(PropertyMapper.fromDtoToEntity(featureDto.getProperties()))
                .build();

        feature.getProperties().forEach((p) -> p.setFeature(feature));
        feature.getGeometry().setFeature(feature);
        return feature;
    }
}
