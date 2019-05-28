package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Order;
import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.enums.OrderStatus;
import be.voedsaam.vzw.service.OrderService;
import be.voedsaam.vzw.service.ProductService;
import be.voedsaam.vzw.service.StockService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.BasketDTO;
import be.voedsaam.vzw.service.dto.OrderDTO;
import be.voedsaam.vzw.service.mapper.OrderMapper;
import be.voedsaam.vzw.service.mapper.PartnerMapper;
import be.voedsaam.vzw.service.mapper.ProductMapper;
import be.voedsaam.vzw.service.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private OrderMapper orderMapper;
    private UserService userService;
    private PartnerMapper partnerMapper;
    private ProductService productService;
    private ProductMapper productMapper;
    private StockService stockService;
    private StockMapper stockMapper;
    @Autowired
    public void setStockMapper(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setPartnerMapper(PartnerMapper partnerMapper) {
        this.partnerMapper = partnerMapper;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/list", "/"})
    public String listOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((List<Order>)orderService.listAll()));
        return "order/list";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/listNew"})
    public String listNewOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((orderService.listNewOrders())));
        return "order/list";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/listClosed"})
    public String listClosedOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((orderService.listClosedOrders())));
        return "order/list";
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/listCompleted"})
    public String listCompletedOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((orderService.listCompletedOrders())));
        return "order/list";
    }

    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/listInProgress"})
    public String listInProgressOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((orderService.listOrdersInProgress())));
        return "order/list";
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/listCancelled"})
    public String listCancelledOrders(Model model){
        model.addAttribute("orders", orderMapper.mapToDTO((orderService.listCancelledOrders())));
        return "order/list";
    }

    @Secured({"ROLE_PARTNER"})
    @RequestMapping("listbyuser")
    public String listOrdersbyUser(Model model, Principal user) {
        model.addAttribute("orders", orderMapper.mapToDTO(orderService.listAllByUser(user.getName())));
        return "order/list";
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS","ROLE_PARTNER"})
    @RequestMapping("/show/{id}")
    public String get(@PathVariable Integer id, Model model){
        Order order = orderService.getById(id.longValue());
        model.addAttribute("order", orderMapper.mapToDTO(order));
        return "order/show";
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS","ROLE_PARTNER"})
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Order order = orderService.getById(id.longValue());
        model.addAttribute("order", orderMapper.mapToDTO(order));
        model.addAttribute("stock", stockMapper.mapToDTO(order.getStock()));

        List<Partner> possibleUsers = userService.listAllPartners();
        possibleUsers.remove(order.getPartner());
        model.addAttribute("currentUser",partnerMapper.mapToDTO(order.getPartner()));
        model.addAttribute("possibleUsers",partnerMapper.mapToDTO(possibleUsers));
        model.addAttribute("products", productMapper.mapToDTO(order.getProducts()));
        model.addAttribute("types",OrderStatus.values());
        return "order/form";
    }
    @Secured({"ROLE_PARTNER"})
    @RequestMapping("/new")
    public String newOrder(Model model, Principal principal ){
        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW);
        LocalDate date = LocalDate.now();
        order.setPickupDateTime(LocalDateTime.of(date, LocalTime.of(17,0)));
        order.setPartner((Partner) userService.findByEmail(principal.getName()));
        model.addAttribute("order", orderMapper.mapToDTO(order));
        model.addAttribute("types",OrderStatus.values());
        return "order/form";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(OrderDTO dto){
        Order order = orderMapper.mapToObj(dto);
        order = orderService.saveOrUpdate(order);
        return "redirect:/sendOrderMailToPartner/"+order.getId();
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        Order order =  orderService.getById(id.longValue());
        if (order.getOrderStatus().equals(OrderStatus.NEW)||(order.getOrderStatus().equals(OrderStatus.CANCELLED))) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            Map<Product, Integer> toTransfer = order.getProducts();
            for (Product p : toTransfer.keySet()
                    ) {
                order.getStock().addProduct(p, toTransfer.get(p));
            }
            orderService.delete(id.longValue());
        }
        return "redirect:/order/list";
    }

    @RequestMapping("/cancel/{id}")
    @Transactional
    public String cancel(@PathVariable Integer id){
        Order order =  orderService.getById(id.longValue());
        if (order.getOrderStatus().equals(OrderStatus.NEW)&&(!order.getOrderStatus().equals(OrderStatus.CANCELLED))) {
            order.setOrderStatus(OrderStatus.CANCELLED);
            Map<Product, Integer> toTransfer = order.getProducts();
            for (Product p : toTransfer.keySet()
                    ) {
                order.getStock().addProduct(p, toTransfer.get(p));
            }
        }
        return "redirect:/order/listbyuser";
    }

    @Transactional
    @RequestMapping("{idOrder}/setPartner/{idUser}")
    public String setPartnerToOrder(@PathVariable Integer idOrder ,@PathVariable Integer idUser){
        Order order = orderService.getById(idOrder.longValue());
        Partner user = userService.getPartnerById(idUser.longValue());
        try {
            order.setPartner(user);
        }
        catch (UnsupportedOperationException e){
            return "redirect:error";
        }
        orderService.saveOrUpdate(order);
        return "redirect:/order/edit/" + order.getId();
    }
    @Transactional
    @PostMapping("/newOrder")
    public String newOrderPost(@ModelAttribute BasketDTO basketDTO, Principal user){
        Stock fromStock = stockService.getById(basketDTO.getIdStock());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.NEW);
        order.setPickupDateTime(LocalDateTime.parse(basketDTO.getPickupDateTime(), DateTimeFormatter.ISO_DATE_TIME));
        order.setPartner((Partner) userService.findByEmail(user.getName()));
        order.setStock(fromStock);
        Map<Product, Integer> products = productMapper.mapToObj(basketDTO.getProducts());
        for (Product p: products.keySet()
                ) {
            fromStock.removeProduct(p,products.get(p));
            order.addProduct(p,products.get(p));
        }
        order = orderService.saveOrUpdate(order);

        return "redirect:/sendOrderMailToPartner/" + order.getId();
    }
}
