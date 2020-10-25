package ru.example.construct.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.example.construct.repository.entity.Feature;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {
}
