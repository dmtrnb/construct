package ru.example.construct.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "point")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Point {

    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double x;
    private double y;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geometry_id")
    private GeometryEntity geometry;
}
