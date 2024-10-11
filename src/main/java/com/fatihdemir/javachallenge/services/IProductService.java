package com.fatihdemir.javachallenge.services;

import com.fatihdemir.javachallenge.dto.product.DtoProduct;
import com.fatihdemir.javachallenge.dto.product.DtoProductIU;

import java.util.List;
import java.util.UUID;

public interface IProductService {

    public DtoProduct createProduct(DtoProductIU dtoProductIU, String clientIP);

    public DtoProduct getProductById(UUID productId);

    public List<DtoProduct> getProduct();

    public DtoProduct updateProduct(UUID productId, DtoProductIU dtoProductIU, String clientIP);

    public DtoProduct deleteProduct(DtoProductIU dtoProductIU);

}
