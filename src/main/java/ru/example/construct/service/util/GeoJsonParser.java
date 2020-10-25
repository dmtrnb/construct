package ru.example.construct.service.util;

import com.google.gson.*;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateList;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import ru.example.construct.service.dto.FeatureCollectionDto;
import ru.example.construct.service.dto.FeatureDto;
import ru.example.construct.service.util.exception.NonLineStringException;
import ru.example.construct.service.util.exception.WrongGeoJsonFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GeoJsonParser {

    public static final String TYPE = "type";
    public static final String FEATURE = "Feature";
    public static final String FEATURES = "features";
    public static final String FEATURE_COLLECTION = "FeatureCollection";
    public static final String GEOMETRY = "geometry";
    public static final String LINE_STRING = "LineString";
    public static final String PROPERTIES = "properties";
    public static final String COORDINATES = "coordinates";

    public static FeatureCollectionDto fromJsonToFeatureCollectionOfLineStrings(final String geoJson) {
        List<FeatureDto> features;
        String typeOfFeatureCollection;

        try {
            JsonObject root = createRootObject(geoJson);

            typeOfFeatureCollection = root.get(TYPE).getAsString();
            if (typeOfFeatureCollection == null || !typeOfFeatureCollection.equals(FEATURE_COLLECTION)) {
                return null;
            }

            JsonArray features_element = root.getAsJsonArray(FEATURES);
            features = createFeatures(features_element);
        } catch (WrongGeoJsonFormatException e) {
            return null;
        }

        return new FeatureCollectionDto(typeOfFeatureCollection, features);
    }

    private static JsonObject createRootObject(final String geoJson) throws WrongGeoJsonFormatException {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(geoJson);
        JsonObject root = element.getAsJsonObject();

        if (!root.has(TYPE) || !root.has(FEATURES)) {
            throw new WrongGeoJsonFormatException("Problems with root");
        }

        return root;
    }

    private static List<FeatureDto> createFeatures(JsonArray features_element) throws WrongGeoJsonFormatException {
        String typeOfFeature;
        Properties properties;
        Geometry geometry;
        Gson gson = new Gson();
        List<FeatureDto> features = new ArrayList<>();
        GeometryFactory geometryFactory = new GeometryFactory();

        for (JsonElement e: features_element) {
            JsonObject o = e.getAsJsonObject();
            if (!o.has(TYPE) || !o.has(GEOMETRY) || !o.has(PROPERTIES)) {
                throw new WrongGeoJsonFormatException("Problems with feature");
            }

            typeOfFeature = o.get(TYPE).getAsString();
            if (typeOfFeature == null || !typeOfFeature.equals(FEATURE)) {
                throw new WrongGeoJsonFormatException("Problems with feature");
            }

            properties = gson.fromJson(o.get(PROPERTIES).getAsJsonObject(), Properties.class);

            try {
                geometry = createGeometry(o, geometryFactory);
            } catch (NonLineStringException nonLineStringException) {
                continue;
            }
            features.add(new FeatureDto(typeOfFeature, properties, geometry));
        }
        return features;
    }

    private static Geometry createGeometry(JsonObject o, GeometryFactory geometryFactory) throws NonLineStringException, WrongGeoJsonFormatException {
        Geometry geometry;
        CoordinateList coordinates = new CoordinateList();
        String typeOfGeometry;
        JsonArray jsonArray;
        double x, y;

        JsonObject geom = o.get(GEOMETRY).getAsJsonObject();
        if (!geom.has(TYPE) || !geom.has(COORDINATES)) {
            throw new WrongGeoJsonFormatException("Problems with geometry");
        }

        typeOfGeometry = geom.get(TYPE).getAsString();
        if (typeOfGeometry == null || !typeOfGeometry.equals(LINE_STRING)) {
            throw new NonLineStringException("It is not LineString geometry");
        }

        for (JsonElement elem: geom.get(COORDINATES).getAsJsonArray()) {
            jsonArray = elem.getAsJsonArray();
            x = jsonArray.size() > 0 ? jsonArray.get(0).getAsDouble() : 0;
            y = jsonArray.size() > 1 ? jsonArray.get(1).getAsDouble() : 0;
            coordinates.add(new Coordinate(x, y));
        }
        geometry = geometryFactory.createLineString(coordinates.toCoordinateArray());
        return geometry;
    }
}
