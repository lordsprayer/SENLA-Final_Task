package com.senla.courses.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    String phone;
    @Transient
    private String passwordConfirm;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User(String name, String surname, String login, String password, String passwordConfirm, String phone) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.passwordConfirm = passwordConfirm;
    }

    public User(Integer id, String name, String surname, String login, String password, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && Objects.equals(getName(), user.getName())
                && Objects.equals(getSurname(), user.getSurname()) && getLogin().equals(user.getLogin())
                && getPassword().equals(user.getPassword()) && Objects.equals(getPhone(), user.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getLogin(), getPassword(), getPhone());
    }
}
