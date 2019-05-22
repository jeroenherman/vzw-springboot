package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.commons.CRUDService;

import java.util.List;

public interface OrderService extends CRUDService<Order> {

    List<Order> listNewOrders();
    List<Order> listCompletedOrders();
    List<Order> listOrdersInProgress();
    List<Order> listCancelledOrders();
    List<Order> listAllByUser(String name);
}
