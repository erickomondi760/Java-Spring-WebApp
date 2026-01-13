package com.user.web_app.repository;

import com.user.web_app.entity.Occupation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation,Integer> {
    Occupation findByTitle(@NotNull @NotBlank @Size(min = 3,message = "title should be at least 3 characters") String title);
}
