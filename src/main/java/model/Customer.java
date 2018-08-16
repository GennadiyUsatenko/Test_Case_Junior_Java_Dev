package model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "Customer")
@JsonPropertyOrder({ "id", "name", "age", "mobile_no" })
public class Customer {

    @Id
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long age;
    @Column(nullable = false)
    private Long mobile_no;

    public Customer(){}

    public Customer(Long id, String name, Long age, Long mobile_no){
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile_no = mobile_no;
    }

    public static Customer getForTestCase(){
        return new Customer(101l, "John", 22l, 380633221100l);
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return super.toString();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(Long mobile_no) {
        this.mobile_no = mobile_no;
    }

}
