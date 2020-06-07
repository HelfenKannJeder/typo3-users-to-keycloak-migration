package de.helfenkannjeder.typo3userstokeycloakmigration.typo3;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_users")
public class Typo3User {

    @Id
    int uid;

    String username;

    String password;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    String name;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean disable;

    String email;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    boolean deleted;

    int crdate;

    int lastlogin;

    @Override
    public String toString() {
        return String.format(
                "Typo3User[id=%d, username=%s, email=%s]",
                uid, username, email);
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getCrdate() {
        return crdate;
    }

    public void setCrdate(int crdate) {
        this.crdate = crdate;
    }

    public int getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(int lastlogin) {
        this.lastlogin = lastlogin;
    }
}
