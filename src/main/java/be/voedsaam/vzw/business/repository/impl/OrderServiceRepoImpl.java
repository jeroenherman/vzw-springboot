package be.voedsaam.vzw.business.repository.impl;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.repository.OrderRepository;
import be.voedsaam.vzw.enums.OrderStatus;
import be.voedsaam.vzw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class OrderServiceRepoImpl implements OrderService {

    private OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<?> listAll() {
        List<Order> tasks = new ArrayList<>();
        orderRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    @Override
    public Order getById(Long id) {
        Optional<Order> o = orderRepository.findById(id);
        return o.orElse(null);
    }
    @Override
    public Order saveOrUpdate(Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            Optional<Order> o = orderRepository.findById(id);
            o.ifPresent(order -> orderRepository.delete(order));
        }
    }

    @Override
    public List<Order> listNewOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.NEW) ;
    }

    @Override
    public List<Order> listCompletedOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.COMPLETED) ;
    }

    @Override
    public List<Order> listOrdersInProgress() {
        return orderRepository.findAllByOrderStatus(OrderStatus.IN_PROGRESS) ;
    }

    @Override
    public List<Order> listCancelledOrders() {
        return orderRepository.findAllByOrderStatus(OrderStatus.CANCELLED) ;
    }
}
