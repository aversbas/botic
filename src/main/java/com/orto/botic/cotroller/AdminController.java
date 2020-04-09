package com.orto.botic.cotroller;

import com.orto.botic.entities.Product;
import com.orto.botic.entities.User;
import com.orto.botic.repositories.ProductRepository;
import com.orto.botic.service.ProductService;
import com.orto.botic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService){this.productService = productService;}

    @GetMapping
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }

    @GetMapping(value = "/productlist")
    public ModelAndView productListPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.getAllProducts();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("admin/product-list");
        return modelAndView;
    }

    @GetMapping(value = "/new")
    public ModelAndView newProductPage() {
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        modelAndView.addObject("product", product);
        modelAndView.setViewName("admin/new-product");
        return modelAndView;
    }

    @PostMapping(value = "/save")
    public ModelAndView saveProduct(Product product) {
            var modelAndView = new ModelAndView();
//            var product = new Product();
            productService.saveProd(product);
        modelAndView.addObject("successMessage", "Продукт збережено");
        modelAndView.addObject("product",product);
        modelAndView.setViewName("/admin/new-product");
        return modelAndView;
    }

//    @PostMapping(value = "/save")
//    public ModelAndView saveProduct(Product product) {
//        ModelAndView modelAndView = new ModelAndView();
//
//            productService.saveProd(product);
//            modelAndView.addObject("successMessage", "Продукт збережено");
//            modelAndView.addObject("prod", new Product());
//
//        return new ModelAndView("redirect:/admin");
//    }


}
