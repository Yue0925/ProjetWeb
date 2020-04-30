package com.example.MakeUp.Order;

import com.example.MakeUp.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class OrderController {
    @ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Error")
    @ExceptionHandler(Exception.class)
    public void error() {
    }

     OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    //get a list of orders
    @RequestMapping(value="/getOrders", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Order> getAllOrders(){
        return orderRepository.findAll();
    }


    //create a new order ????
    @RequestMapping(value = "/newOrder", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addOrder(@RequestBody Order order) throws Exception{
        Order o = new Order(order.getId(), order.getDate(), order.getPerson(), order.getStatus());
        orderRepository.save(o);
    }

    //get the feature of an order
    @RequestMapping(value = "/getOrder/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Order getOrder(@PathVariable(value = "id") Long id){
        Optional<Order> v = orderRepository.findById(id);
        return v.get();
    }

    //delete an order
    //@Transactional
    @RequestMapping(value = "/delOrder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable(value = "id") Long id) throws Exception{
        orderRepository.deleteById(id);
    }


    //update an order
    @RequestMapping(value = "/updOrder", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(@RequestBody Order order){
        Order o = new Order(order.getId(), order.getDate(), order.getPerson(), order.getStatus());
        orderRepository.save(o);

    }


}
