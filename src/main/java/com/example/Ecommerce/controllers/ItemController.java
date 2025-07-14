package com.example.Ecommerce.controllers;

import com.example.Ecommerce.payload.ItemDTO;
import com.example.Ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/{user_id}")
    public ItemDTO postItem(@PathVariable Long user_id, @RequestBody ItemDTO itemDTO)
    {
        return itemService.createItem(user_id,itemDTO);
    }

    @GetMapping("/products")
    public List<ItemDTO> getitems(){
        return itemService.getItems();
    }
    @GetMapping("category/{category}")
    public List<ItemDTO> getCategory(@PathVariable String category){
        return itemService.getCategory(category);
    }
    @GetMapping("category")
    public  List<String> getCategories(){
        return itemService.getCategories();
    }
    @GetMapping("search/{name}")
    public List<ItemDTO> searchByName(@PathVariable String name){
        return itemService.getByName(name);
    }
    @GetMapping("/get/{itemId}")
    public ItemDTO getItemByID(@PathVariable Long itemId){
        return itemService.getItemByID(itemId);
    }
}
