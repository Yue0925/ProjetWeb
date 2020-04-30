package com.example.MakeUp.Product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class ProductCommandLineRunner implements CommandLineRunner {
     ProductRepository productRepository;

    public ProductCommandLineRunner(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("CREATE PRODUCTS");
        List<String> brands = Arrays.asList("Antipodes", "Benefit", "Clinique", "Estée Lauder", "Giorgio Armani",
                "Jurlique", "Lime Crime", "MAC", "NARS", "Stila" );

        List<String> names = Arrays.asList("Concealer", "Foundation", "Powder", "Lipstick", "Eyeshadow palette",
                "Mascara", "Eyeliner", "Blush", "Highlight", "Contour" );


        for(String brand: brands){
            for (String name: names){
                Product p = new Product();
                p.setBrand(brand);
                p.setName(name);
                p.setStock(100);
                p.setPrice(getRandomNumberInRange(10, 60));
                p.setFeedBack(getRandomNumberInRange(3, 5));
                productRepository.save(p);
            }
        }
    }
}
