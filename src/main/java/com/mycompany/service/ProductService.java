/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author msius
 */
@Service
public class ProductService {
    
    private final List<Product> products = new ArrayList<>(
            Arrays.asList(new Product((long)1, "Laptop", "Elektronik"), new Product((long)2, "Ponsel", "Elektronik"))
    );
    
    public List<Product> getAllProducts() {
       return products;
    }
    
    public Product getProduct(Long id) {
        Product result = new Product();
        
        for (Product p : products) {
            if (p.getId().equals(id)) {
                result = p;
                break;
            }
        }
        
        return result;
    }
}
