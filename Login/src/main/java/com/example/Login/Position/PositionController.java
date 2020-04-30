package com.example.Login.Position;

import com.example.Login.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PositionController {

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Error")
    @ExceptionHandler(Exception.class)
    public void error() {
    }

    PositionRepository positionRepository;

    @Autowired
    public PositionController(PositionRepository positionRepository){this.positionRepository = positionRepository;}

    @Autowired
    RestTemplate restTemplate;


    //Verify Login http://localhost:8181/login?name=admin&password=admin
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/login", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Position verify(@RequestParam(value = "name", required = true) String name,
                                   @RequestParam(value = "password", required = true) String password){
        System.out.println("name: " + name);
        List<Position> usrs = (List<Position>) positionRepository.findAll();
        for(Position p: usrs){
            if (p.getName().equals(name) && p.getPassword().equals(password)){
                System.out.println("verify");
                return p;
            }
        }
        return null;
    }


    //display all products http://localhost:8181/products
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/products", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product[] getProductsList(){
        System.out.println("getProductsList");
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/getProducts", Product[].class);
        Product[] products = responseEntity.getBody();
        return products;
    }

    //find a product  http://localhost:8181/product/2
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/product/{id}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable(value = "id") String id){
        System.out.println("getProduct" + id);
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity("http://localhost:8080/getProduct/"+id, Product.class);
        Product product = responseEntity.getBody();
        System.out.println(product.getName());
        return product;
    }


    //display all positions http://localhost:8181/positions
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/positions", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Position[] getPositionsList(){
        ResponseEntity<Position[]> responseEntity = restTemplate.getForEntity("http://localhost:8181/getPositions", Position[].class);
        Position[] positions = responseEntity.getBody();
        System.out.println("getPositionsList");
        return positions;
    }

    //find a position  http://localhost:8181/position/2
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/position/{id}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Position getPosition(@PathVariable(value = "id") String id){
        System.out.println("getPosition" + id);
        ResponseEntity<Position> responseEntity = restTemplate.getForEntity("http://localhost:8181/getPosition/"+id, Position.class);
        Position position = responseEntity.getBody();
        System.out.println(position.getName());
        return position;
    }


    //add a product http://localhost:8181/updPosition
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody Product product){
        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("http://localhost:8080/newProduct", product, Product.class);
        System.out.println("addProduct");
    }

    //delete a product http://localhost:8181/delProduct/1
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/delProduct/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable(value = "id") Long id) throws Exception{
        ResponseEntity<Product> responseEntity;
        restTemplate.delete("http://localhost:8080/delProduct/" + id);
        System.out.println("deleteProduct");
    }


    /*-------------------------------------------basic functions---------------------------------------------------*/

    //get a list of positions
    @RequestMapping(value="/getPositions", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Position> getAllPositions(){
        return positionRepository.findAll();
    }


    //create a new position
    @RequestMapping(value = "/newPosition", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addPosition(@RequestBody Position position) throws Exception{
        Position o = new Position(position.getId(), position.getName(), position.getPassword(), position.getRole());
        positionRepository.save(o);
    }

    //get the feature of a position
    @RequestMapping(value = "/getPosition/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Position getPosition(@PathVariable(value = "id") Long id){
        Optional<Position> v = positionRepository.findById(id);
        return v.get();
    }

    //delete a position http://localhost:8181/delPosition/1
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/delPosition/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deletePosition(@PathVariable(value = "id") Long id) throws Exception{
        positionRepository.deleteById(id);
        System.out.println("deletePosition");
    }


    //update a position http://localhost:8181/updPosition
    @RequestMapping(value = "/updPosition", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void updatePosition(@RequestBody Position position){
        Position o = new Position(position.getId(), position.getName(), position.getPassword(), position.getRole());
        positionRepository.save(o);
        System.out.println("updatePosition");
    }

}
