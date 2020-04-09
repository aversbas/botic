package com.orto.botic.service;

import com.orto.botic.entities.Product;
import com.orto.botic.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        product.setId(product.getId());
        product.setTitle(product.getTitle());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setPhotoName(product.getPhotoName());
        product.setCategory(product.getCategory());
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
}
