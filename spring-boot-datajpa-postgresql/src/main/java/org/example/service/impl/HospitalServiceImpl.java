package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dto.HospitalDto;
import org.example.entity.Doctor;
import org.example.entity.Hospital;
import org.example.repository.DoctorRepo;
import org.example.repository.HospitalRepo;
import org.example.service.IService.HospitalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepo hospitalRepo;
    private final DoctorRepo doctorRepo;

    @Override
    public HospitalDto save(HospitalDto hospitalDto) {
        Hospital kisi = new Hospital();
        kisi.setName(hospitalDto.getName());
        final  Hospital hospitalDb = hospitalRepo.save(kisi);

        List<Doctor> doctorList = new ArrayList<>();
        hospitalDto.getDoctors().forEach(item -> {
            Doctor doctor = new Doctor();
            doctor.setName(item.getName());
            doctor.setLevel(Doctor.Level.FELLOW);
            doctor.setHospital(hospitalDb);
            doctorList.add(doctor);
        });
        doctorRepo.saveAll(doctorList);
        hospitalDto.setId(hospitalDb.getId());
        return hospitalDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<HospitalDto> getAll() {
        List<Hospital> kisiler = hospitalRepo.findAll();
        List<HospitalDto> kisiDtos = new ArrayList<>();

        kisiler.forEach(it -> {
            HospitalDto kisiDto =new HospitalDto();
            kisiDto.setId(it.getId());
            kisiDto.setName(it.getName());
            kisiDto.setDoctors(
                    it.getDoctors() != null ?
                            null
                            : null);
            kisiDtos.add(kisiDto);
        });
        return kisiDtos;
    }

    @Override
    public Page<HospitalDto> getAll(Pageable pageable) {
        return null;
    }
}
