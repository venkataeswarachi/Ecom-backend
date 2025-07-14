package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.OrderModel;
import com.example.Ecommerce.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
    @Query("SELECT o FROM OrderModel o WHERE o.buyer = :buyer AND o.status = True")
    List<OrderModel> findByBuyer(@Param("buyer") UserModel buyer);
    @Query("SELECT o FROM OrderModel o WHERE o.seller = :user AND o.status = True")
    List<OrderModel> findBySeller(UserModel user);
}
