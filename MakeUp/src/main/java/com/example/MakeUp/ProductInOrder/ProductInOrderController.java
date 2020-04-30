package com.example.MakeUp.ProductInOrder;

import com.example.MakeUp.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class ProductInOrderController {
    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Error")
    @ExceptionHandler(Exception.class)
    public void error() {
    }
     ProductInOrderRepository productInOrderRepository;

    @Autowired
    public ProductInOrderController(ProductInOrderRepository productInOrderRepository){ this.productInOrderRepository = productInOrderRepository;}


    //get a list of productInOrder
    @RequestMapping(value="/getProInOrds", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ProductInOrder> getAllProductInOrder(){
        return productInOrderRepository.findAll();
    }

    //create a new productInOrder
    @RequestMapping(value = "/newProInOrd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProductInOrder(@RequestBody ProductInOrder productInOrder) throws Exception{
        ProductInOrder p = new ProductInOrder(productInOrder.getId(), productInOrder.getProduct(),
                productInOrder.getOrder(), productInOrder.getNumber());
        productInOrderRepository.save(p);
    }

    //get the feature of a productInOrder
    @RequestMapping(value = "/getProInOrd/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ProductInOrder getProductInOrder(@PathVariable(value = "id") Long id){
        Optional<ProductInOrder> v = productInOrderRepository.findById(id);
        return v.get();
    }

    //delete a productInOrder
    @Transactional
    @RequestMapping(value = "/delProInOrd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductInOrder(@PathVariable(value = "id") Long id) throws Exception{
        productInOrderRepository.deleteById(id);
    }


    //update a productInOrder
    @RequestMapping(value = "/updProInOrd", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProductInOrder(@RequestBody ProductInOrder productInOrder){
        ProductInOrder p = new ProductInOrder(productInOrder.getId(), productInOrder.getProduct(),
                productInOrder.getOrder(), productInOrder.getNumber());
        productInOrderRepository.save(p);
    }
}
