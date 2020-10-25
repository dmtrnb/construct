package ru.example.construct.repository.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "property")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyItem {

    @Id
    @Column(name = "property_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    private String key;
    private String value;

    @Override
    public String toString() {
        return key + ": " + value;
    }
}
