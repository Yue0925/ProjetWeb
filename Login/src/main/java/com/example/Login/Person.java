package com.example.Login;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person {
    private Long id;

    private String name;
    private int age;
    private String gender;
    private String adresse;
    private String phoneNumer;
    private String password;

    private List<Order> orders = new ArrayList<Order>();

    public Person(){super();}
    public Person(Long id, String name, int age, String gender, String adresse, String phoneNumer, String password){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.adresse = adresse;
        this.phoneNumer = phoneNumer;
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // {CascadeType.PERSIST, CascadeType.MERGE} fetch=FetchType.EAGER,
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy = "person", orphanRemoval = true)
    @JsonIgnore
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){ addOrder(order, true);}

    public void addOrder(Order order, boolean set) {
        if (order != null) {
            if(getOrders().contains(order)) {
                getOrders().set(getOrders().indexOf(order), order);
            }
            else {
                getOrders().add(order);
            }
            if (set) {
                order.setPerson(this, false);
            }
        }
    }

    public void removeOrder(Order order) {
        getOrders().remove(order);
        order.setPerson(null);
    }

    public String getPhoneNumer() {
        return phoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumer = phoneNumer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(adresse, person.adresse) &&
                Objects.equals(phoneNumer, person.phoneNumer) &&
                Objects.equals(password, person.password) &&
                Objects.equals(orders, person.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, gender, adresse, phoneNumer, password, orders);
    }

    @Override
    public String toString() {
        return "Person [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", adresse='" + adresse + '\'' +
                ", phoneNumer='" + phoneNumer + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                ']';
    }
}
