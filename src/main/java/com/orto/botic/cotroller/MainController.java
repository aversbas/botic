package com.orto.botic.cotroller;

import com.orto.botic.entities.Comment;
import com.orto.botic.entities.Product;
import com.orto.botic.service.CommentService;
import com.orto.botic.service.ProductService;
import com.orto.botic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductService productService;

    UserService userService;

    CommentService commentService;

    @GetMapping("/comment/{productId}")
    public String createComment(Model model, @PathVariable("productId") Long productId) {
        Comment comment = new Comment();
        Product product = productService.findById(productId);
        comment.setProduct(product);
        model.addAttribute("comment", comment);
        return "create-comment-page";
    }

    @PostMapping("/comment/{productId}")
    public ModelAndView saveComment(Comment comment,Principal principal,@PathVariable("productId") Long productId) {
        var modelAndView = new ModelAndView();
        comment.setProduct(productService.findById(productId));
        comment.setUser(userService.findUserByUserName(principal.getName()));
        commentService.saveComment(comment);
        modelAndView.addObject("successMessage", "Коментар збережено");
        modelAndView.addObject("comment",comment);
        modelAndView.setViewName("create-comment-page");
        return modelAndView;
    }


    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }


    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/productListCurrentId/{productId}")
    public ModelAndView getProductPage(@PathVariable("productId") Long productId) {
        var modelAndView = new ModelAndView();
        Product  product = productService.findById(productId);
        modelAndView.addObject("productListId",product);
        modelAndView.setViewName("prod-id");
        return modelAndView;
    }

//    @GetMapping("/example")
//    public String getExamplePage() {
//        return "example";
//    }
}
