package com.currency.conversion.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ksrivas on 5/31/2018.
 */
@Entity
@Data
public class UserInfo {
    @Id
    @GeneratedValue
    private String id;
    private String userName;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
}
