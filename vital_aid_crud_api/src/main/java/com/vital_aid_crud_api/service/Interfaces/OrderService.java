package com.vital_aid_crud_api.service.Interfaces;

import java.util.List;

import com.vital_aid_crud_api.Payloads.OrderDTO;

public interface OrderService {

    public OrderDTO makeOrder(Long productId,OrderDTO orderDTO);
    public OrderDTO getOrderDetailsByOrderId(Long orderId);
    public List<OrderDTO> getAllOrdersList();
    public List<OrderDTO> getAllOrdersListOfAnUser();
    // public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO);
    public void deleteOrder(Long orderId);
}
