package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Stock;
import be.voedsaam.vzw.business.impl.Employee;
import be.voedsaam.vzw.business.impl.Partner;
import be.voedsaam.vzw.enums.Role;
import be.voedsaam.vzw.service.StockService;
import be.voedsaam.vzw.service.UserService;
import be.voedsaam.vzw.service.dto.StockDTO;
import be.voedsaam.vzw.service.mapper.PartnerMapper;
import be.voedsaam.vzw.service.mapper.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/stock")
public class StockController {

    private StockService stockService;
    private StockMapper stockMapper;
    private UserService userService;
    private PartnerMapper partnerMapper;

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

    @RequestMapping({"/list", "/"})
    public String listStocks(Model model){
        model.addAttribute("stocks", stockMapper.mapToDTO((List<Stock>)stockService.listAll()));
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
        return "stock/form";
    }

    @RequestMapping("/new")
    public String newStock(Model model){
        Stock stock = new Stock();
        stock.setName("Nieuw Stock");
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
}
