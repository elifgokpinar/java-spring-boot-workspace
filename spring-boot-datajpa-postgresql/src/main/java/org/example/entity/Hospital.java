package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hospital")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString

/*
* Getter
* Setter
* RequiredArgsConstructor
* ToString
* EqualsAndHasCode
*
* Bu annotationlar için @Data yazılır.
* */


public class Hospital {
    @Id
    @SequenceGenerator(name = "seq_hospital", allocationSize = 1) // 1'er olarak artması için
    @GeneratedValue(generator = "seq_hospital", strategy = GenerationType.SEQUENCE) //postgresql'de bu şekilde yapıyoruz.
    private long id;

    @Column(name="name")
    private String name;

    @OneToMany //1 hastanede birçok doktor olabilir.
    @JoinColumn(name="doctor_id")
    private List<Doctor> doctors;
}
