package com.orto.botic.cotroller;

import com.orto.botic.entities.Category;
import com.orto.botic.entities.Product;
import com.orto.botic.service.CategoryService;
import com.orto.botic.service.ProductService;
import com.orto.botic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/productsoffilter")
public class ProductController {

    private UserService userService;
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public void setProductsService(ProductService productsService,CategoryService categoryService) {
        this.productService = productsService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ModelAndView filterProductPage(Product product) {
        var modelAndView = new ModelAndView();
        List<Category> categoryList = categoryService.getAllCategories();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("/shop-page");
        return modelAndView;
    }
}
