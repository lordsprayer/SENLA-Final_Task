package com.senla.courses.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ShopProduct> shopProducts;

    public Product(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Product(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) && Objects.equals(getName(), product.getName())
                && Objects.equals(getCategory(), product.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCategory());
    }
}
