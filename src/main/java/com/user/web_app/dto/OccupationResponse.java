package com.user.web_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupationResponse {
    private int pageNumber;
    private int pageSize;
    private boolean isLastPage;
    private int totalPages;
    private List<OccupationDTO> userDTO = new ArrayList<>();
}
