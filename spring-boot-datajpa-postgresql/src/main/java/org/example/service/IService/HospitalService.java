package org.example.service.IService;

import org.example.dto.HospitalDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HospitalService {
    HospitalDto save(HospitalDto kisiDto);

    void delete(Long id);

    List<HospitalDto> getAll();

    Page<HospitalDto> getAll(Pageable pageable);
}
