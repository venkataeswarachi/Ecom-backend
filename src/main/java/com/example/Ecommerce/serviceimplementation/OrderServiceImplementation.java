package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.OrderModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.ItemDTO;
import com.example.Ecommerce.payload.OrderDTO;
import com.example.Ecommerce.payload.OrderDetailsDTO;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.MailService;
import com.example.Ecommerce.service.OrderService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Service
public class OrderServiceImplementation implements OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ResponseEntity<OrderDTO> placeOrder(Long itemId, Authentication authentication, OrderDetailsDTO orderDetailsDTO) {
        UserModel userModel = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new UserNotFound("User Not Found"));
        ItemModel itemModel = itemRepository.findById(itemId).orElseThrow(()->new RuntimeException("Item not found"));
        if (userModel.getUser_id().equals(itemModel.getUser().getUser_id())){ return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(null);
        }else {
        OrderModel orderModel = new OrderModel();
        orderModel.setOrderDate(LocalDate.now());
        orderModel.setAddress(orderDetailsDTO.getAddress());
        orderModel.setQuantity(orderDetailsDTO.getQuantity());
        orderModel.setMobile(orderDetailsDTO.getNumber());
        orderModel.setBuyer(userModel);
        orderModel.setItem(itemModel);
        orderModel.setSeller(itemModel.getUser());
        OrderModel savedOrder = orderRepository.save(orderModel);
            OrderDTO orderDTO = getOrderDTO(savedOrder);

            return ResponseEntity.ok(orderDTO);

    }}
    private OrderDTO getOrderDTO(OrderModel orderModel){
        modelMapper.typeMap(OrderModel.class,OrderDTO.class).addMappings(mapper->{
            mapper.map(src->src.getOrderId(),OrderDTO::setOrderId);
            mapper.map(src->src.getAddress(),OrderDTO::setAddress);
            mapper.map(src->src.getQuantity(),OrderDTO::setQuantity);
            mapper.map(src->src.getMobile(),OrderDTO::setMobile);
            mapper.map(src->src.getBuyer().getUser_id(),OrderDTO::setBuyerId);
            mapper.map(src->src.getItem().getUser().getUser_id(),OrderDTO::setSellerId);
            mapper.map(src->src.getItem().getItemId(),OrderDTO::setItemId);
            mapper.map(src->src.getOrderDate(),OrderDTO::setOrderDate);
            mapper.map(src->src.getQuantity(),OrderDTO::setQuantity);

        });
        return modelMapper.map(orderModel,OrderDTO.class);

    }
    public List<OrderDTO> getOrders(Long userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User Not Found"));
        List<OrderModel> orderModels = orderRepository.findByBuyer(user);

        // Ensure the type mapping i    s defined before mapping the list
        modelMapper.typeMap(OrderModel.class, OrderDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOrderId(), OrderDTO::setOrderId);
            mapper.map(src -> src.getAddress(), OrderDTO::setAddress);
            mapper.map(src -> src.getQuantity(), OrderDTO::setQuantity);
            mapper.map(src -> src.getMobile(), OrderDTO::setMobile);
            mapper.map(src -> src.getBuyer().getUser_id(), OrderDTO::setBuyerId);
            mapper.map(src -> src.getItem().getUser().getUser_id(), OrderDTO::setSellerId);
            mapper.map(src -> src.getItem().getItemId(), OrderDTO::setItemId);
            mapper.map(src->src.getOrderDate(),OrderDTO::setOrderDate);
            mapper.map(src->src.getStatus(),OrderDTO::setStatus);
        });


        // Map each OrderModel to OrderDTO
        return orderModels.stream()
                .map(orderModel -> modelMapper.map(orderModel, OrderDTO.class))
                .toList(); // or .collect(Collectors.toList()) if using Java < 16
    }

    @Override
    public List<OrderDTO> getRecieved(Long userId) {
        UserModel user = userRepository.findById(userId).orElseThrow(()->new UserNotFound("User Not Found"));
        List<OrderModel> orderModels = orderRepository.findBySeller(user);

        // Ensure the type mapping i    s defined before mapping the list
        modelMapper.typeMap(OrderModel.class, OrderDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getOrderId(), OrderDTO::setOrderId);
            mapper.map(src -> src.getAddress(), OrderDTO::setAddress);
            mapper.map(src -> src.getQuantity(), OrderDTO::setQuantity);
            mapper.map(src -> src.getMobile(), OrderDTO::setMobile);
            mapper.map(src -> src.getBuyer().getUser_id(), OrderDTO::setBuyerId);
            mapper.map(src -> src.getItem().getUser().getUser_id(), OrderDTO::setSellerId);
            mapper.map(src -> src.getItem().getItemId(), OrderDTO::setItemId);
            mapper.map(src->src.getOrderDate(),OrderDTO::setOrderDate);
            mapper.map(src->src.getStatus(),OrderDTO::setStatus);
        });


        // Map each OrderModel to OrderDTO
        return orderModels.stream()
                .map(orderModel -> modelMapper.map(orderModel, OrderDTO.class))
                .toList(); // or .collect(Collectors.toList()) if using Java <
    }


}
