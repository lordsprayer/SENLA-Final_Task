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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private Set<ShopProduct> shopProducts;

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Shop(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
