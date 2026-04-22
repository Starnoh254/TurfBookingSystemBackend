package com.starnoh.turf.bookingsystem.backend.controller;

import com.starnoh.turf.bookingsystem.backend.dto.TurfRequest;
import com.starnoh.turf.bookingsystem.backend.dto.TurfResponse;
import com.starnoh.turf.bookingsystem.backend.service.TurfService;
import com.starnoh.turf.bookingsystem.backend.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TurfController {

    private final TurfService turfService;

    public TurfController(TurfService turfService) {
        this.turfService = turfService;
    }

    @GetMapping("/turfs")
    public ApiResponse<List<TurfResponse>> getAllTurfs(){

        List<TurfResponse> response = turfService.getAllTurfs();
        return ApiResponse.success(response);
    }

    @GetMapping("/turfs/{id}")
    public ApiResponse<TurfResponse> getTurfById(@PathVariable Long id){

        TurfResponse response = turfService.getTurfById(id);
        return ApiResponse.success(response);
    }

    @PostMapping("/turfs")
    public ApiResponse<TurfResponse> createTurf(@RequestBody TurfRequest request){
        TurfResponse response = turfService.createTurf(request);
        return ApiResponse.success(response);
    }
}
