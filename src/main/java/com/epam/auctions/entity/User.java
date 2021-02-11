package com.epam.auctions.entity;

import com.epam.auctions.annotation.Email;
import com.epam.auctions.annotation.Name;

import java.util.List;
import java.util.Objects;


public class User extends Entity<Integer> {
    @Email
    private String email;
    @Name
    private String firstName;
    @Name
    private String lastName;
    private List<Integer> lotNumbers;
    @Name
    private String middleName;
    private String password;
    private UserRole userRole;
    private UserStatus userStatus;
    private String username;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) && userStatus == user.userStatus && userRole == user.userRole && Objects.equals(lotNumbers, user.lotNumbers);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", middleName='").append(middleName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userRole=").append(userRole);
        sb.append(", lotNumbers=").append(lotNumbers);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, password, firstName, middleName, lastName, userStatus, userRole, lotNumbers);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Integer> getLotNumbers() {
        return lotNumbers;
    }

    public void setLotNumbers(List<Integer> lotNumbers) {
        this.lotNumbers = lotNumbers;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

