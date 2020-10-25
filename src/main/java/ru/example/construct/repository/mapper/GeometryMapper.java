package ru.example.construct.repository.mapper;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;
import ru.example.construct.repository.dto.GeometryDto;
import ru.example.construct.repository.entity.GeometryEntity;
import ru.example.construct.repository.entity.Point;

import java.util.ArrayList;
import java.util.List;

public class GeometryMapper {

    public static GeometryDto fromEntityToDto(GeometryEntity geometryEntity) {
        CoordinateList coordinateList = new CoordinateList();

        geometryEntity.getCoordinates().forEach((c) -> coordinateList.add(new Coordinate(c.getX(), c.getY())));
        return new GeometryDto(geometryEntity.getType(), coordinateList.toCoordinateArray());
    }

    public static GeometryEntity fromDtoToEntity(GeometryDto geometryDto) {
        List<Point> coordinates = new ArrayList<>();
        GeometryEntity geometryEntity = GeometryEntity.builder().type(geometryDto.getType()).build();

        geometryDto.getList_coord().forEach((l) -> coordinates.add(Point.builder()
                .geometry(geometryEntity)
                .x(l.get(0))
                .y(l.get(1))
                .build()));
        geometryEntity.setCoordinates(coordinates);

        return geometryEntity;
    }
}
