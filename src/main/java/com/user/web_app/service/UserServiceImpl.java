package com.user.web_app.service;


import com.user.web_app.dto.OccupationDTO;
import com.user.web_app.dto.UserDTO;
import com.user.web_app.dto.UserResponse;
import com.user.web_app.entity.Occupation;
import com.user.web_app.entity.User;
import com.user.web_app.exception.APIException;
import com.user.web_app.exception.ResourceNotFoundException;
import com.user.web_app.repository.OccupationRepository;
import com.user.web_app.repository.UserRepository;
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
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OccupationRepository occupationRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = userRepository.findByEmail(userDTO.getEmail());

        if(user != null){
            throw new APIException("User with an email:"+userDTO.getEmail()+" already exists");
        }

        User newUser = new User();

        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());


        //Creating occupation
        Occupation occupation = occupationRepository.findByTitle(userDTO.getProfession());

        if(occupation != null)
            throw new APIException("Occupation title already exist");

        occupation = new Occupation();
        occupation.setTitle(userDTO.getProfession());

        Occupation savedOccupation = occupationRepository.save(occupation);
        newUser.setOccupation(savedOccupation);

        return modelMapper.map(userRepository.save(newUser), UserDTO.class);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO,int id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user of id:"+id+" does not exist"));

        user.setEmail(userDTO.getEmail());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());

        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO findUser(int id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user of id:"+id+" does not exist"));

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        OccupationDTO occupationDTO = null;

        if(user.getOccupation() != null){
            occupationDTO = modelMapper.map(user.getOccupation(), OccupationDTO.class);
            userDTO.setOccupationDTO(occupationDTO);
        }

       return userDTO;
    }

    @Override
    public UserResponse findAll(int pageNumber, int pageSize, String sortOrder, String sortBy) {

        Sort sort = sortOrder.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<User> page = userRepository.findAll(pageable);

        List<UserDTO> userDTOS = page.stream().map(user->{
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);

            OccupationDTO occupationDTO = null;

            if(user.getOccupation() != null){
                occupationDTO = modelMapper.map(user.getOccupation(), OccupationDTO.class);
                userDTO.setOccupationDTO(occupationDTO);
            }

            return userDTO;
        }).toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setLastPage(page.isLast());
        userResponse.setUserDTO(userDTOS);
        userResponse.setPageSize(page.getSize());
        userResponse.setTotalPages(page.getTotalPages());
        userResponse.setPageNumber(page.getNumber());

        return userResponse;
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user of id:"+id+" does not exist"));

        userRepository.delete(user);
        userRepository.flush();
    }

    @Override
    public UserResponse findUsersByOccupation(int pageNumber, int pageSize, String sortOrder,
                                              String sortBy, String occupation) {

        Sort sort = sortOrder.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<User> page = userRepository.findByOccupation(pageable,occupation);

        List<UserDTO> userDTOS = page.stream().map(user->{
            UserDTO userDTO = modelMapper.map(user,UserDTO.class);

            OccupationDTO occupationDTO = null;

            if(user.getOccupation() != null){
                occupationDTO = modelMapper.map(user.getOccupation(), OccupationDTO.class);
                userDTO.setOccupationDTO(occupationDTO);
            }

            return userDTO;
        }).toList();

        UserResponse userResponse = new UserResponse();
        userResponse.setLastPage(page.isLast());
        userResponse.setUserDTO(userDTOS);
        userResponse.setPageSize(page.getSize());
        userResponse.setTotalPages(page.getTotalPages());
        userResponse.setPageNumber(page.getNumber());

        return userResponse;
    }


}
