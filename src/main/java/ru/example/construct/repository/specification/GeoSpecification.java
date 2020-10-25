package ru.example.construct.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.example.construct.repository.entity.Feature;
import ru.example.construct.repository.entity.Feature_;
import ru.example.construct.service.util.FeaturesOperation;

public class GeoSpecification {

    public static Specification<Feature> getFeatureWithColorRedOrBlue(boolean state) {
        String key = FeaturesOperation.COLOR;
        String value = (state ? FeaturesOperation.RED : FeaturesOperation.BLUE);

        return ((root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(Feature_.type), "Feature");
        });
    }
}
