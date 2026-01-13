package com.user.web_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.user.web_app.entity.Occupation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private  Integer id;

    @NotNull
    @NotBlank
    @Size(min = 3,message = "first name should be at least 3 characters")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3,message = "last name should be at least 3 characters")
    private String lastName;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 4,message = "Profession should be at least 4 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String profession;

    private OccupationDTO occupationDTO;

}
