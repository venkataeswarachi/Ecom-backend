package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewModel,Long > {
    List<ReviewModel> findByItem(ItemModel item);
    @Query("SELECT AVG(rating)  FROM ReviewModel WHERE item=:item")
    int findRating(@Param("item") ItemModel item);


}
