package com.orto.botic.service;

import com.orto.botic.entities.Category;
import com.orto.botic.entities.Product;
import com.orto.botic.entities.ProductType;
import com.orto.botic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProd(Product product) {
        productRepository.save(product);
    }

    public Product get(long id) {
        return productRepository.findById(id).get();
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByTitleContains(String title) {
        return productRepository.findProductByTitleContains(title);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Page<Product> getProductsWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Product> productSpecification) {
        return productRepository.findAll(productSpecification, PageRequest.of(pageNumber, pageSize));
    }

    public Page<Product> getProductsWithPaging(int pageNumber, int pageSize) {
        return productRepository.findAll( PageRequest.of(pageNumber, pageSize));
    }

    public Product findByColor(String color) {
        return productRepository.findProductByColor(color);
    }

    public Product findByCategory(Category category) {
        return (Product) productRepository.findProductByCategory(category);
    }

    public List<Product> getProductsWithCategoryAndType(Category category, ProductType productType) {
        return  productRepository.findProductsByCategoryAndType(category,productType);
    }
}
