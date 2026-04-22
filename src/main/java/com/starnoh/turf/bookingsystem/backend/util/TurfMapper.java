package com.starnoh.turf.bookingsystem.backend.util;

import com.starnoh.turf.bookingsystem.backend.dto.TurfRequest;
import com.starnoh.turf.bookingsystem.backend.dto.TurfResponse;
import com.starnoh.turf.bookingsystem.backend.entity.Turf;

public class TurfMapper {
    public static Turf toEntity(TurfRequest request) {
        Turf turf = new Turf();
        turf.setTurfName(request.getTurfName());
        turf.setTurfLocation(request.getTurfLocation());
        turf.setTurfDesc(request.getTurfDesc());
        turf.setTurfPriceRate(request.getTurfPriceRate());
        turf.setPhoneNumber(request.getPhoneNumber());
        return turf;
    }

    public static TurfResponse toResponse(Turf turf) {
        TurfResponse tr = new TurfResponse();
        tr.setTurfId(turf.getId());
        tr.setPhoneNumber(turf.getPhoneNumber());
        tr.setTurfName(turf.getTurfName());
        tr.setTurfPriceRate(turf.getTurfPriceRate());
        tr.setTurfDesc(turf.getTurfDesc());
        tr.setTurfLocation(turf.getTurfLocation());
        return tr;
    }
}
