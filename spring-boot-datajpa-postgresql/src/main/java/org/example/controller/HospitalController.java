package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.HospitalDto;
import org.example.entity.Hospital;
import org.example.service.IService.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<HospitalDto> kaydet(@Valid @RequestBody HospitalDto hospitalDto) {
        return ResponseEntity.ok(hospitalService.save(hospitalDto));
    }

    @GetMapping
    public ResponseEntity<List<HospitalDto>> tumunuListele() {
        return ResponseEntity.ok(hospitalService.getAll());
    }
}
