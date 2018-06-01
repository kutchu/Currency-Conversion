package com.currency.conversion.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by ksrivas on 5/31/2018.
 */
@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "emailId"))
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public UserInfo() {
    }

    public UserInfo(String firstName, String lastName, String emailId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
    }

    public UserInfo(String firstName, String lastName, String emailId, String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", emailId='" + this.getEmailId() + '\'' +
                ", password='" + "*********" + '\'' +
                ", roles=" + this.getRoles() +
                '}';
    }

}
