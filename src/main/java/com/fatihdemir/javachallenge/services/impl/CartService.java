package com.fatihdemir.javachallenge.services.impl;

import com.fatihdemir.javachallenge.dto.product.DtoProductIU;
import com.fatihdemir.javachallenge.entity.Cart;
import com.fatihdemir.javachallenge.entity.CartItem;
import com.fatihdemir.javachallenge.entity.Customer;
import com.fatihdemir.javachallenge.entity.Product;
import com.fatihdemir.javachallenge.repository.CustomerRepository;
import com.fatihdemir.javachallenge.exceptions.ShowException;
import com.fatihdemir.javachallenge.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import com.fatihdemir.javachallenge.dto.cart.DtoCart;
import com.fatihdemir.javachallenge.repository.CartItemRepository;
import com.fatihdemir.javachallenge.repository.CartRepository;
import com.fatihdemir.javachallenge.repository.ProductRepository;
import com.fatihdemir.javachallenge.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    @Transactional
    public DtoCart addProductToCart(UUID customerId, UUID productId, Long quantity, String clientIp) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", customerId));

        Cart cart = new Cart();

        if (customer.getCart() == null) {
            cart = new Cart();
            cart.setCreatedAt(LocalDateTime.now());
            cart.setCustomer(customer);
            cart.setCartItems(new ArrayList<>());
            cart.setCreatedAt(LocalDateTime.now());
            cart.setCreatedBy(clientIp);

            cartRepository.save(cart);

            customer.setCart(cart);
            customerRepository.save(customer);
        } else {
            cart = customer.getCart();
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cart.getId(), productId);

        if (cartItem != null) {
            throw new ShowException("Product " + product.getProductName() + " already exists in the cart");
        }

        if (product.getProductQuantity() == 0) {
            throw new ShowException(product.getProductName() + " is not available");
        }

        if (product.getProductQuantity() < quantity) {
            throw new ShowException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getProductQuantity() + ".");
        }

        CartItem newCartItem = new CartItem();

        newCartItem.setProduct(product);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(quantity);
        newCartItem.setProductPrice(product.getProductPrice());

        cartItemRepository.save(newCartItem);

        product.setProductQuantity(product.getProductQuantity() - quantity);

        cart.setTotalPrice(cart.getTotalPrice() + product.getProductPrice() * quantity );

        DtoCart dtoCartIU = modelMapper.map(cart, DtoCart.class);

        List<DtoProductIU> dtoProductIU = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), DtoProductIU.class)).collect(Collectors.toList());

        dtoCartIU.setProducts(dtoProductIU);

        return dtoCartIU;
    }

    @Override
    public List<DtoCart> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();

        if (carts.isEmpty()) {
            throw new ShowException("No cart exists");
        }

        List<DtoCart> cartDTOs = carts.stream().map(cart -> {
            DtoCart cartDTO = modelMapper.map(cart, DtoCart.class);

            List<DtoProductIU> products = cart.getCartItems().stream()
                    .map(p -> modelMapper.map(p.getProduct(), DtoProductIU.class)).collect(Collectors.toList());

            cartDTO.setProducts(products);

            return cartDTO;

        }).collect(Collectors.toList());

        return cartDTOs;
    }

    @Override
    @Transactional
    public String deleteProductFromCart(UUID cartId, UUID productId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "CartId", cartId));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ResourceNotFoundException("Product", "productId", productId);
        }

        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity()));

        Product product = cartItem.getProduct();

        product.setProductQuantity(product.getProductQuantity() + cartItem.getQuantity());

        cartItemRepository.deleteCartItemByProductIdAndCartId(cartId, productId);

        return "Product " + cartItem.getProduct().getProductName() + " removed from the cart !!!";
    }

    @Override
    public DtoCart getCart(UUID userId, UUID cartId) {
        Cart cart = cartRepository.findCartByCustomerIdAndCartId(userId, cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }

        DtoCart dtoCart = modelMapper.map(cart, DtoCart.class);

        List<DtoProductIU> products = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), DtoProductIU.class)).collect(Collectors.toList());

        dtoCart.setProducts(products);

        return dtoCart;
    }

    @Override
    public DtoCart updateProductQuantityInCart(UUID cartId, UUID productId, Long quantity) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        if (product.getProductQuantity() == 0) {
            throw new ShowException(product.getProductName() + " is not available");
        }

        if (product.getProductQuantity() < quantity) {
            throw new ShowException("Please, make an order of the " + product.getProductName()
                    + " less than or equal to the quantity " + product.getProductQuantity() + ".");
        }

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ShowException("Product " + product.getProductName() + " not available in the cart!!!");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());

        product.setProductQuantity(product.getProductQuantity() + cartItem.getQuantity() - quantity);

        cartItem.setProductPrice(product.getProductPrice());
        cartItem.setQuantity(quantity);

        cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * quantity));

        cartItemRepository.save(cartItem);

        DtoCart dtoCart = modelMapper.map(cart, DtoCart.class);

        List<DtoProductIU> dtoProductIUS = cart.getCartItems().stream()
                .map(p -> modelMapper.map(p.getProduct(), DtoProductIU.class)).collect(Collectors.toList());

        dtoCart.setProducts(dtoProductIUS);

        return dtoCart;

    }

    @Override
    public void updateProductInCarts(UUID cartId, UUID productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

        CartItem cartItem = cartItemRepository.findCartItemByProductIdAndCartId(cartId, productId);

        if (cartItem == null) {
            throw new ShowException("Product " + product.getProductName() + " not available in the cart!!!");
        }

        double cartPrice = cart.getTotalPrice() - (cartItem.getProductPrice() * cartItem.getQuantity());

        cartItem.setProductPrice(product.getProductPrice());

        cart.setTotalPrice(cartPrice + (cartItem.getProductPrice() * cartItem.getQuantity()));

        cartItemRepository.save(cartItem);
    }

    public void emptyCart(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "cartId", cartId));

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems != null && !cartItems.isEmpty()) {
            cartItemRepository.deleteAll(cartItems);
        }

        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }

}

















