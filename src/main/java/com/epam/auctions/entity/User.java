package com.epam.auctions.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class User extends Entity<Integer> {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private UserStatus userStatus;
    private UserRole userRole;
    private List<Integer> lotNumbers;
    // TODO: lots
}

