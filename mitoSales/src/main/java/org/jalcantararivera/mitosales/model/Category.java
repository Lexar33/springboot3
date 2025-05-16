package org.jalcantararivera.mitosales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity //(name="categoryEntity")
//@Table(schema = "sistemas")
//@Table(name = "tbl_category")
@Table
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCategory;
    //@Column(name="category_name",nullable = false,length = 50)
    //@Column(nullable = false,length = 50,updatable = false)
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String description;
    @Column(nullable = false)
    private boolean enabled;

    public Category(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }
}

