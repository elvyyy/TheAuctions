package com.epam.auctions.entity;

import com.epam.auctions.annotation.Email;
import com.epam.auctions.annotation.Name;
import com.epam.auctions.annotation.Password;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Objects;


public class User extends Entity<Integer> {
    private String username;
    @Email
    private String email;

    private String password;
    @Name
    private String firstName;
    @Name
    private String middleName;
    @Name
    private String lastName;
    private UserStatus userStatus;
    private UserRole userRole;
    private List<Integer> lotNumbers;
    // TODO: lots
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public List<Integer> getLotNumbers() {
        return lotNumbers;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(lastName, user.lastName) && userStatus == user.userStatus && userRole == user.userRole && Objects.equals(lotNumbers, user.lotNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, password, firstName, middleName, lastName, userStatus, userRole, lotNumbers);
    }

    public void setLotNumbers(List<Integer> lotNumbers) {
        this.lotNumbers = lotNumbers;
    }
}

