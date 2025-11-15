/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.resolver;

import com.mycompany.model.Product;
import com.mycompany.service.ProductService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 *
 * @author msius
 */

@Controller
public class ProductResolver {

    private final ProductService productService;

    public ProductResolver(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        return productService.getProductById(id);
    }

    @QueryMapping
    public List<Product> getProductsByName(@Argument String name) {
        return productService.getProductsByName(name);
    }

    @QueryMapping
    public List<Product> getProductsByCategory(@Argument String category) {
        return productService.getProductsByCategory(category);
    }

    @MutationMapping
    public Product addProduct(@Argument("input") Product input) {
        return productService.addProduct(input);
    }

    @MutationMapping
    public Product updateProduct(@Argument Long id, @Argument("input") Product input) {
        return productService.updateProduct(id, input);
    }

    @MutationMapping
    public boolean deleteProduct(@Argument Long id) {
        return productService.deleteProduct(id);
    }
}
