package org.example.entity;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString
public class Doctor implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_doctor", allocationSize = 1) // 1'er olarak artması için
    @GeneratedValue(generator = "seq_doctor", strategy = GenerationType.SEQUENCE) //postgresql'de bu şekilde yapıyoruz.
    private Long id;

    @Column(length = 100, name = "name")
    private String name;

    @Enumerated
    private Level level;

    @Column(name="department")
    private String department;

    @ManyToOne // Birçok doktor 1 hastane'de çalışabilir. (Burada bir doktor sadece 1 hastane'de çalışabilir olarak düşünüyoruz)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public enum Level {
        INTERN,
        RESIDENT,
        FELLOW
    }
}
