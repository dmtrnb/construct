package ru.example.construct.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.example.construct.repository.entity.Feature;
import ru.example.construct.repository.entity.Feature_;
import ru.example.construct.repository.entity.PropertyItem;
import ru.example.construct.service.util.FeaturesOperation;

import javax.persistence.criteria.Join;

public class GeoSpecification {

    public static Specification<Feature> getFeatureWithColorRedOrBlue(boolean state) {
        String key = FeaturesOperation.COLOR;
        String value = (state ? FeaturesOperation.RED : FeaturesOperation.BLUE);

        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<Feature, PropertyItem> join = root.join("properties");
            return criteriaBuilder.and(criteriaBuilder.equal(join.get("key"), key),
                    criteriaBuilder.equal(join.get("value"), value));
        });
    }
}
