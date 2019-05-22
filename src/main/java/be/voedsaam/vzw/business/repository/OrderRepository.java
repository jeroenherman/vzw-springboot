package be.voedsaam.vzw.business.repository;


import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.User;
import be.voedsaam.vzw.enums.OrderStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    List<Order> findAllByPartner(User user);
}
