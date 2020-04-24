package com.orto.botic.cotroller;

import com.orto.botic.entities.Category;
import com.orto.botic.entities.Product;
import com.orto.botic.service.CategoryService;
import com.orto.botic.service.ProductService;
import com.orto.botic.util.ProductSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ShopController {

    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 12;

    private ProductService productsService;
    private CategoryService categoryService;

    @Autowired
    public void setProductsService(ProductService productsService,CategoryService categoryService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
    }

//    @GetMapping
//    public String shopPage(HttpServletRequest request, Model model,
//                           @RequestParam(value = "word", required = false) String word,
//                           @RequestParam(value = "min", required = false) Double min,
//                           @RequestParam(value = "max", required = false) Double max,
//                           @RequestParam(value = "color", required = false) String color
//    ) {
//
//        int page = 0; //default page number is 0 (yes it is weird)
//        int size = 12; //default page size is 12
//
//        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
//            page = Integer.parseInt(request.getParameter("page")) - 1;
//        }
//
//        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
//            size = Integer.parseInt(request.getParameter("size"));
//        }
//
//        Specification<Product> spec = Specification.where(null);
//        StringBuilder filters = new StringBuilder();
//        if (word != null) {
//            spec = spec.and(ProductSpecs.titleContains(word));
//            filters.append("&word=" + word);
//        }
//        if (min != null) {
//            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
//            filters.append("&min=" + min);
//        }
//        if (max != null) {
//            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
//            filters.append("&max=" + max);
//        }
//        if (min != null) {
//            spec = spec.and(ProductSpecs.colorContains(color));
//            filters.append("&color=" + color);
//        }
//
//        model.addAttribute("products", productsService.getProductsWithPaging(page, size));
//
//        model.addAttribute("filters", filters.toString());
//
//        model.addAttribute("min", min);
//        model.addAttribute("max", max);
//        model.addAttribute("word", word);
//        model.addAttribute("color", color);
//
//        return "shop-page";
//    }

    @GetMapping
    public String shopPage(Model model,Product product,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "min", required = false) Double min,
                           @RequestParam(value = "max", required = false) Double max,
//                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "color", required = false) String color
    ) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
            filters.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }
        if (color != null) {
            spec = spec.and(ProductSpecs.colorContains(color));
            filters.append("&color=" + color);
        }
//        if (category != null) {
//            spec = spec.and(ProductSpecs.categoryContains(category));
//            filters.append("&category=" + category);
//        }

        Page<Product> products = productsService.getProductsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);

        Product colorList = productsService.findByColor(color);

        List<Category> categoryList = categoryService.getAllCategories();

        model.addAttribute("categoryList",categoryList);
//        model.addAttribute("category",category);

        model.addAttribute("products", products.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", products.getTotalPages());

        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);
        model.addAttribute("colorList", colorList);

        return "shop-page";
    }

}
