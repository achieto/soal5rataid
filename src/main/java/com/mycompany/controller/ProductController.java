/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Product;
import com.mycompany.service.ProductService;
import java.util.List;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

/**
 *
 * @author msius
 */
@Controller
public class ProductController {
    private ProductService _productService;
    
    public ProductController(ProductService s) {
        this._productService = s;
    }
    
    @QueryMapping
    public Product getProduct(@Argument Long Id) {
        return _productService.getProduct(Id);
    }
    
    @QueryMapping
    public List<Product> getAllProducts() {
        return _productService.getAllProducts();
    }
}
