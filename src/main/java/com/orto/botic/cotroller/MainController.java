package com.orto.botic.cotroller;

import com.orto.botic.entities.Comment;
import com.orto.botic.entities.Product;
import com.orto.botic.entities.User;
import com.orto.botic.service.CommentService;
import com.orto.botic.service.ProductService;
import com.orto.botic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createcomment")
    public String saveComment(Model model, Principal principal, @ModelAttribute("comment") Comment comment) {
        User user = userService.findUserByUserName(principal.getName());
        comment.setUser(user);
        commentService.saveComment(comment);
        return "redirect:/products";
    }

    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }

//    @GetMapping("/admin")
//    public String getAdminPage() {
//        return "admin";
//    }


    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }
}
