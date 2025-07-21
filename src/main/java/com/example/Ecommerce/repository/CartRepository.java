package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.CartModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.ItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<CartModel,Long> {
    List<CartModel> findByUser(UserModel userModel);
}
