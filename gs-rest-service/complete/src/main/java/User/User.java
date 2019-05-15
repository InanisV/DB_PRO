package User;

public class User {
    private UserType userType;
    private String signInName;
    private String pwd;
    private String phone;
    private String emailAddress;

    public User(){}

    public User(UserType type, String loginInName, String pwd){
        this.userType=type;
        this.signInName=loginInName;
        this.pwd=pwd;
    }

    //sign out should be in admin controller
    //todo
    public void alterInfo(){

    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLoginInName() {
        return signInName;
    }

    public void setLoginInName(String loginInName) {
        this.signInName = loginInName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
