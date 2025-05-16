package org.jalcantararivera.mitosales.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProduct;

    @ManyToOne //FK
    @JoinColumn(name="id_category", nullable = false,foreignKey = @ForeignKey(name="FK_PRODUCT_CATEGORY"))
    private Category category;

    // FROM Product p where p.category.name= 'COMPUTER' ; // JPQL Java Persistence Query Language

    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 50,nullable = false)
    private String description;
    @Column(columnDefinition = "decimal(6,2)",nullable = false)
    private double price;
    @Column(nullable = false)
    private boolean enabled;

}
