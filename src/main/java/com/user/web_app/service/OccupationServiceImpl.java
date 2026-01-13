package com.user.web_app.service;


import com.user.web_app.dto.OccupationDTO;
import com.user.web_app.dto.OccupationResponse;
import com.user.web_app.entity.Occupation;
import com.user.web_app.exception.APIException;
import com.user.web_app.exception.ResourceNotFoundException;
import com.user.web_app.repository.OccupationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OccupationServiceImpl implements OccupationService{

    @Autowired
    private OccupationRepository occupationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public OccupationDTO createOccupation(OccupationDTO occupationDTO) {
        Occupation occupation = occupationRepository.findByTitle(occupationDTO.getTitle());

        if(occupation != null)
            throw new APIException("Occupation title already exist");

        occupation = new Occupation();
        occupation.setTitle(occupationDTO.getTitle());

        return modelMapper.map(occupationRepository.save(occupation),OccupationDTO.class);
    }

    @Transactional
    @Override
    public OccupationDTO updateOccupation(OccupationDTO occupationDTO,int id) {
        Occupation occupation = occupationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Occupation of id:"+id+" does not exist"));

        occupation.setTitle(occupationDTO.getTitle());

        return modelMapper.map(occupationRepository.save(occupation), OccupationDTO.class);
    }

    @Override
    public OccupationDTO findOccupation(int id) {
        Occupation occupation = occupationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Occupation of id:"+id+" does not exist"));

        return modelMapper.map(occupation, OccupationDTO.class);
    }

    @Override
    public OccupationResponse findAll(int pageNumber, int pageSize, String sortOrder, String sortBy) {

        Sort sort = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Occupation> page = occupationRepository.findAll(pageable);

        List<OccupationDTO> occupationDTOS = page.stream().map(occupation ->
                modelMapper.map(occupation, OccupationDTO.class)).toList();

        OccupationResponse occupationResponse = new OccupationResponse();

        occupationResponse.setLastPage(page.isLast());
        occupationResponse.setPageNumber(page.getNumber());
        occupationResponse.setTotalPages(page.getTotalPages());
        occupationResponse.setPageSize(page.getSize());
        occupationResponse.setUserDTO(occupationDTOS);

        return occupationResponse;
    }

    @Transactional
    @Override
    public void deleteOccupation(int id) {
        Occupation occupation = occupationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Occupation of id:"+id+" does not exist"));

        occupationRepository.delete(occupation);
    }
}
