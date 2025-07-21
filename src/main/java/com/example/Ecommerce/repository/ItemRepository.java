package com.example.Ecommerce.repository;

import com.example.Ecommerce.models.CartModel;
import com.example.Ecommerce.models.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Repository
public interface ItemRepository extends JpaRepository<ItemModel,Long> {
    List<ItemModel> findByitemIdIn(List<Long> itemIds);
    List<ItemModel> findByCategory(String category);

    @Query("SELECT DISTINCT i.category from ItemModel i")
    List<String> findcategories();
    @Query("SELECT i FROM ItemModel i WHERE LOWER(i.item_name) LIKE LOWER(CONCAT('%', :item_name, '%')) OR LOWER(i.category) LIKE LOWER(CONCAT('%', :item_name, '%'))")
    List<ItemModel> findByitem_name(@Param("item_name") String item_name);



}
