package org.example.dto;

import lombok.*;
import org.example.entity.Doctor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
@NoArgsConstructor(force = true) //?
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})

public class HospitalDto {

    private Long id;

    @NonNull
    private String name;

    private List<Doctor> doctors;
}
