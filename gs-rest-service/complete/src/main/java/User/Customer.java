package User;
import DAO;

import javax.jws.soap.SOAPBinding;

public class Customer extends User{
    private String firstName;
    private String surname;
    private String customerAddress;
    //DAO should be add

    public Customer(UserType type, String loginInName, String pwd, String firstName, String surname){
        super(type, loginInName, pwd);
        this.firstName=firstName;
        this.surname=surname;
    }

    public void
}
