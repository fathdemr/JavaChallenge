package com.fatihdemir.javachallenge.services.impl;

import com.fatihdemir.javachallenge.dto.product.DtoProduct;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import com.fatihdemir.javachallenge.entity.Product;
import com.fatihdemir.javachallenge.repository.ProductRepository;
import com.fatihdemir.javachallenge.services.IProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public DtoProduct createProduct(DtoProductIU dtoProductIU,String clientIP) {

        DtoProduct response = new DtoProduct();
        Product product = new Product();

        BeanUtils.copyProperties(dtoProductIU, product);

        product.setCreatedBy(clientIP);

        Product dbProduct = productRepository.save(product);


        BeanUtils.copyProperties(dbProduct, response);

        return response;
    }

    @Override
    public DtoProduct getProductById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        DtoProduct response = new DtoProduct();
        BeanUtils.copyProperties(product, response);

        return response;
    }

    @Override
    public List<DtoProduct> getProduct() {
        List<DtoProduct> dtoList = new ArrayList<>();
        List<Product> products = productRepository.findAllStudents();

        for (Product product : products) {
            DtoProduct dtoProduct = new DtoProduct();
            BeanUtils.copyProperties(product, dtoProduct);
            dtoList.add(dtoProduct);
        }

        return dtoList;
    }

    @Override
    public DtoProduct updateProduct(UUID productId, DtoProductIU dtoProductIU, String clientIP) {

        DtoProduct dtoProduct = new DtoProduct();

        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product dbProduct = productOptional.get();

            dbProduct.setProductName(dtoProductIU.getProductName());
            dbProduct.setProductPrice(dtoProductIU.getProductPrice());
            dbProduct.setProductQuantity(dtoProductIU.getProductQuantity());
            dbProduct.setUpdatedAt(LocalDateTime.now());
            dbProduct.setCreatedBy(clientIP);

            Product updatedProduct = productRepository.save(dbProduct);

            BeanUtils.copyProperties(updatedProduct, dtoProduct);
            return dtoProduct;
        }

        throw new EntityNotFoundException("Product with ID " + productId + " not found");
    }

    @Override
    public DtoProduct deleteProduct(DtoProductIU dtoProductIU) {
        return null;
    }
}
