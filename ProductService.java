package com.example.ProductManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }


    public Product getProduct(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    public Page<Product> findPaginated(int pageNo , int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return productRepository.findAll(pageable);
    }
}
