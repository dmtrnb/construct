package ru.example.construct.repository.mapper;

import ru.example.construct.repository.entity.PropertyItem;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class PropertyMapper {
    public static Properties fromEntityToDto(Set<PropertyItem> propertyItems) {
        Properties properties = new Properties();

        propertyItems.forEach((p) -> {
            properties.put(p.getKey(), p.getValue());
        });

        return properties;
    }

    public static Set<PropertyItem> fromDtoToEntity(Properties properties) {
        Set<PropertyItem> set = new HashSet<>();

        properties.keySet().forEach((k) -> {
            String value = String.valueOf(properties.getProperty((String) k));
            set.add(PropertyItem.builder()
                    .key((String) k)
                    .value(value)
                    .build());
        });

        return set;
    }
}
