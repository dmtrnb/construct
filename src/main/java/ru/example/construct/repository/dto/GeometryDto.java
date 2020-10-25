package ru.example.construct.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.locationtech.jts.geom.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class GeometryDto {

    private String type;

    @JsonIgnore
    private Coordinate[] coordinates;

    @JsonProperty("coordinates")
    private List<List<Double>> list_coord;

    public GeometryDto(String type, Coordinate[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;

        list_coord = new ArrayList<>();
        Arrays.stream(coordinates).forEach((c) ->
                list_coord.add(Stream.of(c.getX(), c.getY()).collect(Collectors.toList())));
    }
}
