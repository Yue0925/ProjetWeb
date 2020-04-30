package com.example.MakeUp.ProductInOrder;

import com.example.MakeUp.Order.Order;
import com.example.MakeUp.Order.OrderRepository;
import com.example.MakeUp.Person.Person;
import com.example.MakeUp.Person.PersonRepository;
import com.example.MakeUp.Product.Product;
import com.example.MakeUp.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class ProductInOrderCommandLineRunner implements CommandLineRunner {
    @Autowired
     ProductInOrderRepository productInOrderRepository;
    @Autowired
     OrderRepository orderRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ProductRepository productRepository;
/*
    public ProductInOrderCommandLineRunner(ProductInOrderRepository productInOrderRepository,
                                           OrderRepository orderRepository, PersonRepository personRepository,
                                           ProductRepository productRepository){
        this.productInOrderRepository = productInOrderRepository;
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.productRepository = productRepository;
    }*/

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("PRODUCTINORDER & ORDER");

         List<Person> ps = (List<Person>) personRepository.findAll();

        for(int i = 0; i < ps.size(); i++){ // for all customer create an order
            Person p = ps.get(i);

            Order order = new Order();
            order.setPerson(p);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse("2020-04-17");
            order.setDate(date);
            order.setStatus("created");

            List<Product> products = (List<Product>) productRepository.findAll();

            ProductInOrder productInOrder = new ProductInOrder();
            productInOrder.setOrder(order);

            Product product = products.get(getRandomNumberInRange(1, 99));
            productInOrder.setProduct(product);

            productInOrder.setNumber(getRandomNumberInRange(1, 5));

            orderRepository.save(order);
            productInOrderRepository.save(productInOrder);
        }


    }
}
