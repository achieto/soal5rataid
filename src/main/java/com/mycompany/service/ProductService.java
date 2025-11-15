/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Product;
import com.mycompany.repository.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author msius
 */
@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final WebClient webClient;

    public ProductService(ProductRepository productRepository, WebClient.Builder webClientBuilder) {
        this.productRepository = productRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/catalog").build();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return (Product) productRepository.findById(id);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryContainingIgnoreCase(category);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = (Product) productRepository.findById(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setCategory(updatedProduct.getCategory());
            product.setPrice(updatedProduct.getPrice());
            product.setDescription(updatedProduct.getDescription());
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<Product> getAllProductsFromCatalog() {
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class)
                .collectList()
                .block();
    }

    public Product getProductFromCatalog(Long id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product addProductToCatalog(Product product) {
        return webClient.post()
                .uri("/products")
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Product updateProductInCatalog(Long id, Product product) {
        return webClient.put()
                .uri("/products/{id}", id)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public boolean deleteProductFromCatalog(Long id) {
        webClient.delete()
                .uri("/products/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
        return true;
    }
}
