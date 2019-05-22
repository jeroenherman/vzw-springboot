package be.voedsaam.vzw.service.mapper;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.repository.OrderRepository;
import be.voedsaam.vzw.commons.AbstractMapper;
import be.voedsaam.vzw.service.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class OrderMapper extends AbstractMapper<Order,OrderDTO> {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public OrderDTO mapToDTO(Order b) {
        if (b==null)
        return null;
        OrderDTO d = new OrderDTO();
        d.setId(b.getId());
        if (b.getPickupDateTime()!=null)
        d.setPickupDateTime(b.getPickupDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
        d.setPickUp(b.getPickupDateTime());
        d.setOrderStatus(b.getOrderStatus());
        if (b.getPartner()!=null)
        d.setPartner(b.getPartner().getFullName());
        if (b.getStock()!=null)
            d.setStock(b.getStock().getName());
        return d;
    }

    @Override
    public Order mapToObj(OrderDTO d) {
        if (d ==null)
        return null;
        Order b = new Order();
        Optional<Order> o = Optional.empty();
        if (d.getId()!=null)
            o = orderRepository.findById(d.getId());

        if(o.isPresent())
            b = o.get();
        b.setId(d.getId());
        b.setPickupDateTime(LocalDateTime.parse(d.getPickupDateTime(),DateTimeFormatter.ISO_DATE_TIME));
        b.setOrderStatus(d.getOrderStatus());
        return b;
    }
}
