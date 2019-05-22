package be.voedsaam.vzw.controller;

import be.voedsaam.vzw.business.Product;
import be.voedsaam.vzw.enums.ProductType;
import be.voedsaam.vzw.service.ProductService;
import be.voedsaam.vzw.service.dto.ProductDTO;
import be.voedsaam.vzw.service.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setUserService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/list", "/"})
    public String listProducts(Model model){
        model.addAttribute("products", productMapper.mapToDTO((List<Product>)productService.listAll()));

        return "product/list";
    }

    @RequestMapping("/show/{id}")
    public String get(@PathVariable Integer id, Model model){
        Product product = productService.getById(id.longValue());
        model.addAttribute("product", productMapper.mapToDTO(product));

        return "product/show";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product product = productService.getById(id.longValue());

        model.addAttribute("product", productMapper.mapToDTO(product));

        model.addAttribute("types", ProductType.values());
        return "product/form";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        Product product = new Product();
        product.setName("Nieuw Product");
        product.setProductType(ProductType.FEAD);
        model.addAttribute("product", productMapper.mapToDTO(product));
        model.addAttribute("types",ProductType.values());
        return "product/form";
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(ProductDTO dto){
        Product product = productMapper.mapToObj(dto);
        product = productService.saveOrUpdate(product);
        return "redirect:/product/";
    }

    @RequestMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable Integer id){
        productService.delete(id.longValue());
        return "redirect:/product/list";
    }
}
