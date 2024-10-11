package com.fatihdemir.javachallenge.controller;

import com.fatihdemir.javachallenge.dto.product.DtoProduct;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

public interface IProductController {

    public DtoProduct createProduct(DtoProductIU dtoProductIU, HttpServletRequest request);

    public DtoProduct getProductById(UUID productId);

    public List<DtoProduct> getProduct();

    public DtoProduct updateProduct(UUID productId, DtoProductIU dtoProductIU, HttpServletRequest request);

}
