package com.vital_aid_crud_api.web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vital_aid_crud_api.Payloads.OrderDTO;
import com.vital_aid_crud_api.service.Interfaces.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vital_aid/orderProduct")
public class OrderController {
    @Autowired
    private OrderService orderService;

                                       // LIST OF ALL ORDERS

    @GetMapping("/allOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersList(){
        List<OrderDTO> orders = orderService.getAllOrdersList();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

                                    // GET ORDER DETAILS BY ORDER ID
                                
    @GetMapping("/orderDetails/{Id}")
    public ResponseEntity<OrderDTO> getOrderDetailsByOrderId(@PathVariable Long Id){
        OrderDTO order =  orderService.getOrderDetailsByOrderId(Id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

                                        // ORDER LIST OF AN USER

    @GetMapping("/userOrders")
    public ResponseEntity<List<OrderDTO>> getAllOrdersListOfAnUser(){
        List<OrderDTO> userOrders = orderService.getAllOrdersListOfAnUser();
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }
    
                                            // MAKE ORDER

    @PostMapping("/makeOrder/{Id}")
    public ResponseEntity<String> makeOrder(@PathVariable Long Id,@Valid @RequestBody OrderDTO orderDTO){
        orderDTO = orderService.makeOrder(Id, orderDTO);
        return new ResponseEntity<>("Order made successfully", HttpStatus.CREATED);
    }

                                           // DELETE ORDER

    @DeleteMapping("/deleteOrder/{Id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long Id){
        orderService.deleteOrder(Id);
        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
    }
}
