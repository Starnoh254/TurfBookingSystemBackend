package com.starnoh.turf.bookingsystem.backend.service;

import com.starnoh.turf.bookingsystem.backend.dto.TurfRequest;
import com.starnoh.turf.bookingsystem.backend.dto.TurfResponse;
import com.starnoh.turf.bookingsystem.backend.entity.Turf;
import com.starnoh.turf.bookingsystem.backend.repository.TurfRepository;
import com.starnoh.turf.bookingsystem.backend.util.TurfMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TurfService {

    private final TurfRepository turfRepository;

    public TurfService(TurfRepository turfRepository) {
        this.turfRepository = turfRepository;
    }

    public List<TurfResponse> getAllTurfs(){
        List<Turf> turfs = turfRepository.findAll();
        return turfs.stream()
                .map(TurfMapper::toResponse)
                .toList();

    }

    public TurfResponse getTurfById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Turf ID cannot be null");
        }

        Turf turf = turfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turf not found"));

        return TurfMapper.toResponse(turf);
    }

    public TurfResponse createTurf(TurfRequest request){

        Turf turf = TurfMapper.toEntity(request);

        Turf savedTurf = turfRepository.save(turf);

        return TurfMapper.toResponse(savedTurf);


    }

}
