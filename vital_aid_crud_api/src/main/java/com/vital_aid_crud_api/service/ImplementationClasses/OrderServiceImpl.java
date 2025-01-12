package com.vital_aid_crud_api.service.ImplementationClasses;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vital_aid_crud_api.Entity.Order;
import com.vital_aid_crud_api.Entity.Product;
import com.vital_aid_crud_api.Entity.User;
import com.vital_aid_crud_api.Exception.InsufficientStockException;
import com.vital_aid_crud_api.Exception.ResourceNotFoundException;
import com.vital_aid_crud_api.Payloads.OrderDTO;
import com.vital_aid_crud_api.repository.OrderRepository;
import com.vital_aid_crud_api.repository.ProductRepository;
import com.vital_aid_crud_api.repository.UserRepository;
import com.vital_aid_crud_api.service.Interfaces.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper ordeMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    
                                            // LIST OF ALL ORDERS
                                        
    @Transactional
    @Override
    public List<OrderDTO> getAllOrdersList() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(order -> convertToDTO(order)).toList();
    }

                                            // GET ORDER DETAILS BY ORDER ID
    
    @Transactional
    @Override
    public OrderDTO getOrderDetailsByOrderId(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                        ()-> new ResourceNotFoundException("Order", "id", orderId));
        OrderDTO orderDTO = convertToDTO(order);
        return orderDTO;
    }

                                            // ORDER LIST OF AN USER

    @Transactional
    @Override
    public List<OrderDTO> getAllOrdersListOfAnUser()
    {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User madeByUser = userRepository.findByPersonEmail(userEmail).
                            orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        List<Order> orderList = madeByUser.getMadeOrders();
        return orderList.stream().map(order -> convertToDTO(order)).toList();
    }

                                            // MAKE AN ORDER

    @Transactional
    @Override
    public OrderDTO makeOrder(Long productId,OrderDTO orderDTO) {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User madeByUser = userRepository.findByPersonEmail(userEmail).
                            orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        Product orderedProduct = productRepository.findById(productId).
                            orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        
        int productCurrentStock = orderedProduct.getProductStockQuantity();
        int orderedQuantity = orderDTO.getOrderedQuantity();
        if(productCurrentStock < orderedQuantity) {
            throw new InsufficientStockException("Insufficient Stock. Only " + productCurrentStock + " left.");
        }

        orderedProduct.setProductStockQuantity(productCurrentStock - orderedQuantity);

        Order order = convertToEntity(orderDTO);
        order.setOrderMadeByUser(madeByUser);
        order.setOrderMadeForProduct(orderedProduct);

        order = orderRepository.save(order);
        return convertToDTO(order);
    }

                                            // DELETE ORDER

    @Transactional
    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                        ()-> new ResourceNotFoundException("Order", "id", orderId));

        User madeByUser = order.getOrderMadeByUser();
        madeByUser.getMadeOrders().remove(order);
        userRepository.save(madeByUser);
                
        Product madeForProduct = order.getOrderMadeForProduct();
        madeForProduct.getProductOrders().remove(order);
        productRepository.save(madeForProduct);
        
        orderRepository.delete(order);
    }
                                                // HELPER METHODS

                                        // CONVERTING ORDERDTO TO ORDER ENTITY

    public Order convertToEntity(OrderDTO orderDTO) {
        Order order = ordeMapper.map(orderDTO, Order.class);
        return order;
    }

                                        // CONVERTING ORDER ENTITY TO ORDERDTO

    public OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = ordeMapper.map(order, OrderDTO.class);
        orderDTO.setOrderMadeBy(order.getOrderMadeByUser().getPersonName());
        orderDTO.setOrderMadeFor(order.getOrderMadeForProduct().getProductName());
        orderDTO.setOrderedProductPhotoUrl(order.getOrderMadeForProduct().getProductPhotoUrl());
        orderDTO.setOrderedProductCategory(order.getOrderMadeForProduct().getProductCategory());
        return orderDTO;
    }                             
}
