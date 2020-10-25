package ru.example.construct.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "geometry")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GeometryEntity {

    @Id
    @Column(name = "geometry_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "geometry", cascade = CascadeType.ALL)
    private List<Point> coordinates;
}
