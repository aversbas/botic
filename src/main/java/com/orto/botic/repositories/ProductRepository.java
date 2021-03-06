package com.orto.botic.repositories;

import com.orto.botic.entities.Category;
import com.orto.botic.entities.Product;
import com.orto.botic.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAll();
    List<Product> findProductByTitleContains(String title);
    Product findByTitle(String title);
    Product findProductByColor(String color);
    List<Product> findProductByCategory(Category category);
    List<Product> findProductsByCategoryAndType(Category category, ProductType type);


}
