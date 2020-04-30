package com.example.MakeUp.ProductInOrder;

import com.example.MakeUp.Order.Order;
import com.example.MakeUp.Product.Product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ProductInOrder {

    private Long id;

    private Product product;
    private Order order;
    private int number;
    private int price;

    public ProductInOrder(){super();}
    public ProductInOrder(Long id, Product product, Order order, int number){
        super();
        this.id = id;
        this.product = product;
        this.order = order;
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        setProduct(product, true);
    }

    public void setProduct(Product product, boolean add) {
        this.product = product;
        if (product != null && add) {
            product.addProductInOrder(this, false);
        }
        this.price = number * product.getPrice();
    }

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        setOrder(order, true);
    }

    public void setOrder(Order order, boolean add) {
        this.order = order;
        if (order != null && add) {
            order.addProductInOrder(this, false);
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrder that = (ProductInOrder) o;
        return number == that.number &&
                price == that.price &&
                Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, order, number, price);
    }

    @Override
    public String toString() {
        return "ProductInOrder [" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", number=" + number +
                ", price=" + price +
                ']';
    }
}
