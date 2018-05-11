package com.zt.pojo;

public class Admini {
    private String id;

    private String name;

    private String password;

<<<<<<< HEAD
    private int status;
=======
    private Byte status;
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

<<<<<<< HEAD
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
=======
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
>>>>>>> bf14b1afce697f4148e462c054988775fd4219a8
        this.status = status;
    }
}