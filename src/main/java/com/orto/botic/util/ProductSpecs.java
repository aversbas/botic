package com.orto.botic.util;

import com.orto.botic.entities.Category;
import com.orto.botic.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Product> titleContains(String word) { // title LIKE 'apple%'
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> { return criteriaBuilder.like(root.get("title"), "%" + word + "%");};
    }

    public static Specification<Product> priceGreaterThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }

    public static Specification<Product> priceLesserThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
        };
    }

    public static Specification<Product> colorContains(String color) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> { return criteriaBuilder.equal(root.get("color"), color  );};
    }

    public static Specification<Product> categoryContains(String category) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> { return criteriaBuilder.equal(root.get("category"), category  );};
    }
}
