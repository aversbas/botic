package com.orto.botic.cotroller;

import com.orto.botic.entities.Product;
import com.orto.botic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductService productService;

//    @RequestMapping("/productList")
//    public String viewHomePage(Model model) {
//        List<Product> listProducts = productService.getAllProducts();
//        model.addAttribute("listProducts", listProducts);
//        return "shop-page";
//    }
}
