package com.example.ecommerce.Service;

import com.example.ecommerce.Dto.Product.ProductDto;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Model.Product;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProductById(long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isEmpty() ? new Product() : product.get();
    }

    public List<ProductDto> listProducts() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            productDtoList.add(new ProductDto(product));
        }
        return productDtoList;
    }

    public void addProduct(ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(long productID, ProductDto productDto, Category category) {
        Product product = new Product(productDto, category);
        productRepository.updateById(productID, product);
    }
}
