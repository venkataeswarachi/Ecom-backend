package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentModel,Long> {
}
