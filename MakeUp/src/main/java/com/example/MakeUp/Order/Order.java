package com.example.MakeUp.Order;

import com.example.MakeUp.Person.Person;
import com.example.MakeUp.ProductInOrder.ProductInOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "OrderTable")
public class Order {

    private Long id;

    private Date date;
    private Person person;
    private List<ProductInOrder> productInOrders = new ArrayList<ProductInOrder>();
    private String status;

    public Order(){super();}
    public Order(Long id, Date date, Person person, String status){
        super();
        this.id = id;
        this.date = date;
        this.person = person;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId(){return id;}

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        setPerson(person, true);
    }

    public void setPerson(Person person, boolean add) {
        this.person = person;
        if (person != null && add) {
            person.addOrder(this, false);
        }
    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    @JsonIgnore
    public List<ProductInOrder> getProductInOrders() {
        return productInOrders;
    }

    public void setProductInOrders(List<ProductInOrder> productInOrders) {
        this.productInOrders = productInOrders;
    }

    public void addProductInOrder(ProductInOrder productInOrder){addProductInOrder(productInOrder, true);}

    public void addProductInOrder(ProductInOrder productInOrder, boolean set) {
        if (productInOrder != null) {
            if(getProductInOrders().contains(productInOrder)) {
                getProductInOrders().set(getProductInOrders().indexOf(productInOrder), productInOrder);
            }
            else {
                getProductInOrders().add(productInOrder);
            }
            if (set) {
                productInOrder.setOrder(this, false);
            }
        }
    }

    public void removeProductInOrder(ProductInOrder productInOrder) {
        getProductInOrders().remove(productInOrder);
        productInOrder.setOrder(null);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(date, order.date) &&
                Objects.equals(person, order.person) &&
                Objects.equals(productInOrders, order.productInOrders) &&
                Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, person, productInOrders, status);
    }

    @Override
    public String toString() {
        return "Order [" +
                "id=" + id +
                ", date=" + date +
                ", customer=" + person +
                ", productInOrders=" + productInOrders +
                ", status='" + status + '\'' +
                ']';
    }
}
