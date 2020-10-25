package ru.example.construct.repository.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FeatureCollectionDto {

    private String type;
    private List<FeatureDto> features;
}
