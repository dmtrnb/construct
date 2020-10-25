package ru.example.construct.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "feature")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Feature {

    @Id
    @Column(name = "feature_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "feature", cascade = CascadeType.ALL)
    private Set<PropertyItem> properties;
}
