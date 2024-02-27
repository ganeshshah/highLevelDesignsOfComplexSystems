package com.design.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class City extends BaseModel {
    private String name;

//    1 : M
//    1  :  1
    @OneToMany(fetch = FetchType.EAGER)
    private List<Theatre> theatres;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Theatre> getTheatres() {
        return theatres;
    }

    public void setTheatres(List<Theatre> theatres) {
        this.theatres = theatres;
    }
}
