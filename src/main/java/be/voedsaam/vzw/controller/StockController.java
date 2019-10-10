package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.service.ProductService;
import be.voedsaam.vzw.service.StockService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.BasketDTO;
import be.voedsaam.vzw.service.dto.DeliveryDTO;
import be.voedsaam.vzw.service.dto.StockDTO;
import be.voedsaam.vzw.service.dto.TransferDTO;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;
    private StockMapper stockMapper;
    private UserService userService;
    private PartnerMapper partnerMapper;
    private ProductService productService;
    private ProductMapper productMapper;

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
    public void setStockMapper(StockMapper stockMapper) {
        this.stockMapper = stockMapper;
    }

    @Autowired
    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }
    @Secured({"ROLE_COORDINATOR","ROLE_LOGISTICS"})
    @RequestMapping({"/list", "/"})
    public String listStocks(Model model){
        model.addAttribute("stocks", stockMapper.mapToDTO((List<Stock>)stockService.listAll()));
        return "stock/list";
    }
    @Secured({"ROLE_PARTNER"})
    @RequestMapping("listbyuser")
    public String listStockByUser(Model model, Principal user) {
        model.addAttribute("stocks", stockMapper.mapToDTO(stockService.listAllByUser(user.getName())));
        return "stock/list";
    }

    @RequestMapping("/show/{id}")
    public String get(@PathVariable Integer id, Model model){
        Stock stock = stockService.getById(id.longValue());
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        return "stock/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Stock stock = stockService.getById(id.longValue());
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        List<Partner> currentUsers = stockService.getById(id.longValue()).getUsers()
                .stream()
                .collect(Collectors.toList());
        List<Partner> possibleUsers = userService.listAllPartners();
        possibleUsers.removeAll(currentUsers);
        model.addAttribute("currentUsers",partnerMapper.mapToDTO(currentUsers));
        model.addAttribute("possibleUsers",partnerMapper.mapToDTO(possibleUsers));
        model.addAttribute("products", productMapper.mapToDTO(stock.getProducts()));
        return "stock/form";
    }

    @RequestMapping("/new")
    public String newStock(Model model){
        Stock stock = new Stock();
        stock.setName("Nieuwe Stock");
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        return "stock/form";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(StockDTO dto){
        Stock stock = stockMapper.mapToObj(dto);
        stock = stockService.saveOrUpdate(stock);
        return "redirect:/stock/";
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        stockService.delete(id.longValue());
        return "redirect:/stock/list";
    }

    @Transactional
    @RequestMapping("{idStock}/deleteuser/{idUser}")
    public String deletUserFromStock(@PathVariable Integer idStock ,@PathVariable Integer idUser){
        Stock stock = stockService.getById(idStock.longValue());
        Partner partner = userService.getPartnerById(idUser.longValue());
        try {
            stock.removeUser(partner);
        }
        catch (UnsupportedOperationException e){
            return "redirect:error";
        }
        stockService.saveOrUpdate(stock);
        return "redirect:/stock/edit/" + stock.getId();
    }
    @Transactional
    @RequestMapping("{idStock}/adduser/{idUser}")
    public String addUserToStock(@PathVariable Integer idStock ,@PathVariable Integer idUser){
        Stock stock = stockService.getById(idStock.longValue());
        Partner user = userService.getPartnerById(idUser.longValue());
        try {
            stock.addUser(user);
        }
        catch (UnsupportedOperationException e){
            return "redirect:error";
        }
        stockService.saveOrUpdate(stock);
        return "redirect:/stock/edit/" + stock.getId();
    }

    @Transactional
    @RequestMapping("{idStock}/addDelivery")
    public String addDelivery(@PathVariable Integer idStock, Model model){
        Stock stock = stockService.getById(idStock.longValue());
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        DeliveryDTO deliveryDTO = new DeliveryDTO();
        deliveryDTO.setProducts(productMapper.mapToDTO((List<Product>)productService.listAll()));
        model.addAttribute("delivery", deliveryDTO);

        return "stock/delivery";
    }

    @Transactional
    @PostMapping("delivery/{idStock}")
    public String confirmDelivery(@PathVariable Integer idStock, @ModelAttribute DeliveryDTO deliveryDTO, Model model){
        Stock stock = stockService.getById(idStock.longValue());
        Map<Product, Integer > delivery = productMapper.mapToObj(deliveryDTO.getProducts());
        for (Product p: delivery.keySet()
             ) {
            stock.addProduct(p,delivery.get(p));
        }

        return "redirect:/stock/edit/" + stock.getId();
    }
    @Transactional
    @RequestMapping("{idStock}/transferStock")
    public String transferStock(@PathVariable Integer idStock, Model model){
        Stock stock = stockService.getById(idStock.longValue());
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        List<Stock> stocks =(List<Stock>) stockService.listAll();
        stocks.remove(stock);
        model.addAttribute("stocks", stockMapper.mapToDTO(stocks));
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setIdFromStock(stock.getId());
        transferDTO.setProducts(productMapper.mapToDTO(stock.getProducts()));
        model.addAttribute("transfer", transferDTO);

        return "stock/transfer";
    }
    @Transactional
    @PostMapping("/transfer")
    public String transferStockPost(@ModelAttribute TransferDTO transferDTO){
        Stock fromStock = stockService.getById(transferDTO.getIdFromStock());
        Stock toStock = stockService.getById(transferDTO.getIdToStock());
        Map<Product, Integer> products = productMapper.mapToObj(transferDTO.getProducts());
        for (Product p: products.keySet()
             ) {
            fromStock.removeProduct(p,products.get(p));
            toStock.addProduct(p,products.get(p));
        }

        return "redirect:/stock/edit/" + toStock.getId();
    }
    @Transactional
    @RequestMapping("{idStock}/newOrder")
    public String newOrder(@PathVariable Integer idStock, Model model){
        Stock stock = stockService.getById(idStock.longValue());
        model.addAttribute("stock", stockMapper.mapToDTO(stock));
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setIdStock(stock.getId());
        basketDTO.setProducts(productMapper.mapToDTO(stock.getProducts()));
        basketDTO.setPickup(true);
        model.addAttribute("basket", basketDTO);

        return "order/transfer";
    }


}
