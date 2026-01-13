package com.user.web_app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "occupation")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotBlank
    @Size(min = 3,message = "title should be at least 3 characters")
    private String title;

    @OneToOne(mappedBy = "occupation", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private User user;

}
