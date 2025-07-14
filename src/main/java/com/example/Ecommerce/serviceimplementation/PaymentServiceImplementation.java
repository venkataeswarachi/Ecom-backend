package com.example.Ecommerce.serviceimplementation;

import com.example.Ecommerce.exceptionHandlers.UserNotFound;
import com.example.Ecommerce.models.ItemModel;
import com.example.Ecommerce.models.OrderModel;
import com.example.Ecommerce.models.PaymentModel;
import com.example.Ecommerce.models.UserModel;
import com.example.Ecommerce.payload.PaymentVerficationDTO;
import com.example.Ecommerce.repository.ItemRepository;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.PaymentRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.MailService;
import com.example.Ecommerce.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.time.LocalDateTime;
import java.util.Map;



@Service
public class PaymentServiceImplementation implements PaymentService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
     private OrderRepository orderRepository;
    @Autowired
    private MailService mailService;
    public ResponseEntity<String> createOrder( Map<String, Object> data) throws RazorpayException {
        Long amount = Long.parseLong(data.get("amount").toString()) * 100;

            RazorpayClient client = new RazorpayClient(keyId, keySecret);


            JSONObject options = new JSONObject();
            options.put("amount", amount);
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());

            Order order = client.orders.create(options); // Razorpay Order


            return ResponseEntity.ok(order.toJson().toString());




        // send Razorpay order JSON back to frontend
    }
    public ResponseEntity<String> verifyPayment(PaymentVerficationDTO dto){
        try{
            String payload = dto.getOrderID() + "|" + dto.getPaymentID();
            String expectedSignature = hmacSha256(payload, keySecret);
            if(!expectedSignature.equals(dto.getSignature())){
                return ResponseEntity.badRequest().body("Signature Doesn't Match : Payment ISSUE");
            }
            OrderModel orderModel = orderRepository.findById(dto.getOrderRefID()).orElseThrow(()-> new RuntimeException("Order Not Found"));
            UserModel buyer = userRepository.findById(dto.getBuyerID()).orElseThrow(()->new UserNotFound("User Not Found"));
            ItemModel item = itemRepository.findById(dto.getItemID()).orElseThrow(()-> new RuntimeException("Item Not Found."));
            UserModel seller = item.getUser();

            orderModel.setStatus(true);
            // Create PaymentModel
            PaymentModel paymentModel = new PaymentModel();
            paymentModel.setOrderId(dto.getOrderID());
            paymentModel.setSignature(dto.getSignature());
            paymentModel.setPaymentId(dto.getPaymentID());

            paymentModel.setStatus("CREATED");
            paymentModel.setPaymentDate(LocalDateTime.now());

            paymentModel.setBuyer(buyer);
            paymentModel.setSeller(seller);
            paymentModel.setItem(item);
            paymentModel.setOrder(orderModel);
            paymentModel.setAmount(dto.getAmount());

            paymentRepository.save(paymentModel);
            String message = "Order For : "+buyer.getName()+"\nEmail : "+buyer.getEmail()+"\nItem Details :\nItem Name: "+item.getItem_name()+"\nQuantity : "+orderModel.getQuantity()+"\nOrder Id : "+orderModel.getOrderId()+"\nShipping Address : "+orderModel.getAddress()+"\nContact Number : "+orderModel.getMobile()+"\nPayment Details : \nPayment ID : "+paymentModel.getPaymentId();
            String notify = "***Order will be delivered within 10 Days***\nItem Details -\nItem Name : "+item.getItem_name()+"\nPrice : "+item.getItem_price()+"\nQuantity : "+orderModel.getQuantity()+"\nTotal Amount : "+item.getItem_price()*orderModel.getQuantity()+"\nPayment Id : "+paymentModel.getPaymentId()+"\nOrder Id : "+paymentModel.getOrderId()+"\n\n\nDo Shopping in Ecom.\n\nThank you.";
            notifyBuyer(buyer.getEmail(), "Thanks For Your Order",notify);
            notifySeller(seller.getEmail(), "New Order Placed",message);
            return ResponseEntity.ok("Payment verified and saved.");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Payment verification failed: " + e.getMessage());
        }

    }

    private String hmacSha256(String data, String secret) throws Exception {
        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);
        return Hex.encodeHexString(sha256Hmac.doFinal(data.getBytes()));
    }

    private void notifySeller(String sellerEmail,String subject,String message){

        mailService.sendEmail(sellerEmail,subject,message);
    }
    private void notifyBuyer(String buyerEmail,String subject,String message){

        mailService.sendEmail(buyerEmail,subject,message);
    }
}
