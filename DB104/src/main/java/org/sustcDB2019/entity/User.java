package org.sustcDB2019.entity;

public class User {
    private Integer id;

    private String password;

    private String userName;

    private String phoneNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(){}

    public User(int id,String password,String userName,String phoneNumber){
        this.id=id;
        this.password=password;
        this.userName=userName;
        this.phoneNumber=phoneNumber;
    }
    public User(String password,String userName,String phoneNumber){
        this.password=password;
        this.userName=userName;
        this.phoneNumber=phoneNumber;
    }

    public User(Customer otherUser){
        this.phoneNumber=otherUser.getPhoneNumber();
        this.userName=otherUser.getUserName();
        this.password=otherUser.getPassword();
        this.id=otherUser.getId();
    }
    public User(Manager otherUser){
        this.phoneNumber=otherUser.getPhoneNumber();
        this.userName=otherUser.getUserName();
        this.password=otherUser.getPassword();
        this.id=otherUser.getId();
    }
    public User(Deliverer otherUser){
        this.phoneNumber=otherUser.getPhoneNumber();
        this.userName=otherUser.getUserName();
        this.password=otherUser.getPassword();
        this.id=otherUser.getId();
    }
    public User(Cashier otherUser){
        this.phoneNumber=otherUser.getPhoneNumber();
        this.userName=otherUser.getUserName();
        this.password=otherUser.getPassword();
        this.id=otherUser.getId();
    }
}