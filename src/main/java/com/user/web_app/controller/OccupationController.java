package com.user.web_app.controller;


import com.user.web_app.dto.OccupationDTO;
import com.user.web_app.dto.OccupationResponse;
import com.user.web_app.service.OccupationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class OccupationController {

    @Autowired
    private OccupationService occupationService;

    @PostMapping("occupations")
    public ResponseEntity<OccupationDTO> addOccupation(@Valid @RequestBody OccupationDTO occupationDTO){
        return ResponseEntity.ok(occupationService.createOccupation(occupationDTO));
    }

    @PutMapping("occupations/{id}")
    public ResponseEntity<OccupationDTO> updateOccupation(@Valid @RequestBody OccupationDTO occupationDTO
            ,@PathVariable int id){
        return ResponseEntity.ok(occupationService.updateOccupation(occupationDTO,id));
    }

    @GetMapping("occupations/{id}")
    public ResponseEntity<OccupationDTO> getOccupation(@PathVariable int id){
        return ResponseEntity.ok(occupationService.findOccupation(id));
    }

    @GetMapping("occupations")
    public ResponseEntity<OccupationResponse> getOccupations(
            @RequestParam(name="pageNumber",defaultValue = "0",required = false)int pageNumber,
            @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = "asc",required = false) String sortOrder,
            @RequestParam(name = "pageSize",defaultValue = "25",required = false)int pageSize){

        return ResponseEntity.ok(occupationService.findAll(pageNumber,pageSize,sortOrder,sortBy));
    }

    @DeleteMapping("occupations/{id}")
    public ResponseEntity<Object> deleteOccupation(@PathVariable int id){
        occupationService.deleteOccupation(id);
        return ResponseEntity.ok("Occupation successfully deleted");
    }
}
