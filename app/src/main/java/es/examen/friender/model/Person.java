package es.examen.friender.model;

import java.io.Serializable;

/**
 * Created by carlosfernandez on 01/12/14.
 */
public class Person implements Serializable {

    private int id;
    private String name;
    private String address;
    private String telephone;
    private int rating;

    public Person(int id, String name, String address, String telephone,int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.telephone = telephone;
        this.address = address;
    }

    public Person(String name, String address, String telephone,int rating) {
        this(-1,name,address,telephone,rating);
    }

    public Person() {
        this(-1,"","","",0);
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
