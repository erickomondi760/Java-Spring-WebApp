package com.user.web_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.web_app.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OccupationDTO {
    @NotNull
    @NotBlank
    @Size(min = 3,message = "title should be at least 3 characters")
    private String title;

    private UserDTO userDTO;
}
