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
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shop)) return false;
        Shop shop = (Shop) o;
        return getId().equals(shop.getId()) && Objects.equals(getName(), shop.getName())
                && Objects.equals(getAddress(), shop.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress());
    }
}
