package ru.example.construct.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.locationtech.jts.geom.Geometry;

import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeatureDto {

    private String type;
    private Properties properties;

    @JsonIgnore
    private Geometry geometry;

    @JsonProperty("geometry")
    private GeometryDto geometryDto;

    public FeatureDto(String type, Properties properties, Geometry geometry) {
        this.type = type;
        this.properties = properties;
        this.geometry = geometry;
        this.geometryDto = new GeometryDto("LineString", geometry.getCoordinates());
    }
}
