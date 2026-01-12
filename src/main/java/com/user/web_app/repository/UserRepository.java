package com.user.web_app.repository;

import com.user.web_app.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(@Email @NotBlank @NotNull String email);

    @Query("select u from User u join fetch u.occupation o where o.title = ?1")
    Page<User> findByOccupation(Pageable pageable, String occupation);
}
