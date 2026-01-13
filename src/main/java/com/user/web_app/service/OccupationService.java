package com.user.web_app.service;

import com.user.web_app.dto.OccupationDTO;
import com.user.web_app.dto.OccupationResponse;
import jakarta.validation.Valid;

public interface OccupationService {
    OccupationDTO createOccupation(@Valid OccupationDTO occupationDTO);

    OccupationDTO updateOccupation(@Valid OccupationDTO occupationDTO,int id);

    OccupationDTO findOccupation(int id);

    OccupationResponse findAll(int pageNumber, int pageSize, String sortOrder, String sortBy);

    void deleteOccupation(int id);
}
