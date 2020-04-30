package com.example.MakeUp.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class ProductController {

    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error")
    @ExceptionHandler(Exception.class)
    public void error() {
    }

     ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //get a list of product
    @RequestMapping(value="/getProducts", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> getAllProducts(){
        return productRepository.findAll();
    }

    //create a new product
    @RequestMapping(value = "/newProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody Product product) throws Exception{
        Product p = new Product(product.getId(), product.getName(),
                product.getPrice(), product.getBrand(), product.getFeedBack(),
                product.getStock());
        productRepository.save(p);
    }

    //get the feature of a product
    @RequestMapping(value = "/getProduct/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(value = "id") Long id){
        Optional<Product> v = productRepository.findById(id);
        return v.get();
    }

    //delete a product
    @Transactional
    @RequestMapping(value = "/delProduct/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable(value = "id") Long id) throws Exception{
        productRepository.deleteById(id);
    }


    //update a product
    @RequestMapping(value = "/updProduct", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product){
            Product p = new Product(product.getId(), product.getName(),
                    product.getPrice(), product.getBrand(), product.getFeedBack(),
                    product.getStock());
            productRepository.save(p);
    }
}
