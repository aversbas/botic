package com.orto.botic.cotroller;

import com.orto.botic.entities.Product;
import com.orto.botic.service.ProductService;
import com.orto.botic.util.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ShopController {
        private static final int INITIAL_PAGE = 0;
        private static final int PAGE_SIZE = 12;

    private ProductService productsService;

    @Autowired
    public void setProductsService(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String shopPage(HttpServletRequest request, Model model) {

        int page = 0; //default page number is 0 (yes it is weird)
        int size = 12; //default page size is 10

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("products", productsService.getProductsWithPaging(page, size));
        return "shop-page";
    }
}
