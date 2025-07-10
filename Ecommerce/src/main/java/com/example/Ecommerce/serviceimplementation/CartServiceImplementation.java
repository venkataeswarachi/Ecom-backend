package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.CartModel;
import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.CartDTO;
import com.example.Ecommerce.payload.ItemDTO;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.security.JwtProvider;
import com.example.Ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtProvider jwtProvider;



    public ResponseEntity<CartDTO> addToCart(Long item_id ,String email){


        UserModel user = userRepository.findByEmail(email).orElseThrow(()->new UserNotFound("user not found!"));
        ItemModel itemModel = itemRepository.findById(item_id).orElseThrow(()->new RuntimeException("Item Not Found"));
        modelMapper.typeMap(ItemModel.class,ItemDTO.class).addMappings(mapper->
                mapper.map(src->src.getUser().getUser_id(),ItemDTO::setSeller_id));
        ItemDTO itemDTO = modelMapper.map(itemModel,ItemDTO.class);
        if (!itemDTO.getSeller_id().equals(user.getUser_id())) {
            CartModel cart = new CartModel();
            cart.setItem(itemModel);
            cart.setUser(user);
            CartModel cartModel = cartRepository.save(cart);
            modelMapper.typeMap(CartModel.class, CartDTO.class).addMappings(mapper -> {
                mapper.map(src -> src.getUser().getUser_id(), CartDTO::setUser_id);

            });
            CartDTO cartDTO = modelMapper.map(cartModel,CartDTO.class);
            cartDTO.setItem(itemDTO);
            return ResponseEntity.ok(cartDTO);
        }else{
            return  ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }



    }

    @Override
    public List<CartDTO> cartItems(Long user_id) {
        UserModel userModel = userRepository.findById(user_id).orElseThrow(()->new UserNotFound("user Not Found"));
        List<CartModel> cartItems = cartRepository.findByUser(userModel);


        List<CartDTO> carts = cartItems.stream()
                .map(item -> modelMapper.map(item,CartDTO.class))
                .collect(Collectors.toList());

        return carts;
    }

    @Override
    public ResponseEntity<String> deleteCartItem(Long cartId) {


        cartRepository.deleteById(cartId);
        return ResponseEntity.ok("Deleted successfully");
    }
}
