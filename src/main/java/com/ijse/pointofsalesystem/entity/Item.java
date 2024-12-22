package com.ijse.pointofsalesystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (nullable = false)
    private String name;
    @Column (nullable = false)
    private Double price;
    @Column (nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

   @JsonIgnore
   @ManyToMany(mappedBy = "ordereditems")

   private List<Order> orders;


}
