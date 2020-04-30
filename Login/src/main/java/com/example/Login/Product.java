package com.example.Login;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {

    private Long id;

    private String name;
    private int price;
    private String brand;
    private int feedBack;
    private int stock;

    private List<ProductInOrder> productInOrders = new ArrayList<ProductInOrder>();

    public Product(){super();}
    public Product(Long id, String name, int price, String brand, int feedBack, int stock){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.feedBack = feedBack;
        this.stock = stock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFeedBack() {
        return feedBack;
    }

    public void setFeedBack(int feedBack) {
        this.feedBack = feedBack;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product", orphanRemoval = true)
    @JsonIgnore
    public List<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public void addProductInOrder(ProductInOrder productInOrder) {addProductInOrder(productInOrder, true);}

    public void addProductInOrder(ProductInOrder productInOrder, boolean set) {
        if (productInOrder != null) {
            if(getProductInOrders().contains(productInOrder)) {
                getProductInOrders().set(getProductInOrders().indexOf(productInOrder), productInOrder);
            }
            else {
                getProductInOrders().add(productInOrder);
            }
            if (set) {
                productInOrder.setProduct(this, false);
            }
        }
    }

    public void removeProductInOrder(ProductInOrder productInOrder) {
        getProductInOrders().remove(productInOrder);
        productInOrder.setProduct(null);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                feedBack == product.feedBack &&
                stock == product.stock &&
                Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(productInOrders, product.productInOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, brand, feedBack, stock, productInOrders);
    }

    @Override
    public String toString() {
        return "Product [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", feedBack=" + feedBack +
                ", stock=" + stock +
                ", productInOrders=" + productInOrders +
                ']';
    }
}

