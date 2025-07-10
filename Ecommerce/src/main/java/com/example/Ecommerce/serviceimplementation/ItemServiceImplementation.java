package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.ItemDTO;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImplementation implements ItemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ItemDTO createItem(Long user_id, ItemDTO itemDTO) {
        UserModel userModel = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFound("User Not Found"));

        // Manual conversion
        ItemModel itemModel = modelMapper.map(itemDTO, ItemModel.class);
        try {
            itemModel.setImagesJson(objectMapper.writeValueAsString(itemDTO.getImages()));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert image array to JSON", e);
        }

        itemModel.setUser(userModel);
        ItemModel savedItem = itemRepository.save(itemModel);

        ItemDTO savedDTO = modelMapper.map(savedItem, ItemDTO.class);
        try {
            savedDTO.setImages(objectMapper.readValue(savedItem.getImagesJson(), String[].class));
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to image array", e);
        }
        savedDTO.setSeller_id(savedItem.getUser().getUser_id());
        return savedDTO;
    }

    @Override
    public List<ItemDTO> getItems() {
        return itemRepository.findAll().stream().map(item -> {
            ItemDTO dto = modelMapper.map(item, ItemDTO.class);
            try {
                dto.setImages(objectMapper.readValue(item.getImagesJson(), String[].class));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dto.setSeller_id(item.getUser().getUser_id());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ItemDTO> getCategory(String category) {
        return itemRepository.findByCategory(category).stream().map(item -> {
            ItemDTO dto = modelMapper.map(item, ItemDTO.class);
            try {
                dto.setImages(objectMapper.readValue(item.getImagesJson(), String[].class));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dto.setSeller_id(item.getUser().getUser_id());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getCategories() {
        return itemRepository.findcategories();
    }

    @Override
    public List<ItemDTO> getByName(String itemName) {
        return itemRepository.findByitem_name(itemName).stream().map(item -> {
            ItemDTO dto = modelMapper.map(item, ItemDTO.class);
            try {
                dto.setImages(objectMapper.readValue(item.getImagesJson(), String[].class));
            } catch (Exception e) {
                e.printStackTrace();
            }
            dto.setSeller_id(item.getUser().getUser_id());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItemByID(Long id) {
        ItemModel itemModel = itemRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("Item Not Found"));

        ItemDTO dto = modelMapper.map(itemModel, ItemDTO.class);
        try {
            dto.setImages(objectMapper.readValue(itemModel.getImagesJson(), String[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        dto.setSeller_id(itemModel.getUser().getUser_id());
        return dto;
    }
}
