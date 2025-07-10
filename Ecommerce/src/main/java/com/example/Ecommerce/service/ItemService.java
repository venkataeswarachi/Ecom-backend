package com.example.Ecommerce.service;


import com.example.Ecommerce.payload.ItemDTO;

import java.util.List;


public interface ItemService {
    public ItemDTO createItem(Long user_id,ItemDTO itemDTO);
    public List<ItemDTO> getItems();
    public List<ItemDTO> getCategory(String category);
    public List<String> getCategories();
    public List<ItemDTO> getByName(String itemName);
    public ItemDTO getItemByID(Long id);
}
