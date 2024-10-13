package com.fatihdemir.javachallenge.controller.impl;

import com.fatihdemir.javachallenge.util.IpAdressUtil;
import com.fatihdemir.javachallenge.controller.IProductController;
import com.fatihdemir.javachallenge.dto.product.DtoProduct;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import com.fatihdemir.javachallenge.services.impl.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController implements IProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/create")
    @Override
    public DtoProduct createProduct(@RequestBody DtoProductIU dtoProductIU, HttpServletRequest request) {
        String clientIp = IpAdressUtil.getClientIp(request);
        return productService.createProduct(dtoProductIU, clientIp);
    }

    @GetMapping("list/{productID}")
    @Override
    public DtoProduct getProductById(@PathVariable(name = "productID") UUID productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/list")
    @Override
    public List<DtoProduct> getProduct() {
        return productService.getProduct();
    }

    @PutMapping("/update/{productId}")
    @Override
    public DtoProduct updateProduct(@PathVariable(name = "productId") UUID productId, @RequestBody DtoProductIU dtoProductIU, HttpServletRequest request) {
        String clientIp = IpAdressUtil.getClientIp(request);
        return productService.updateProduct(productId, dtoProductIU, clientIp);
    }
}
