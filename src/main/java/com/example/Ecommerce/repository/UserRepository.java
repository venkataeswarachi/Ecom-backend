package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {

    Optional<UserModel> findByEmail(String username);
}
