package Models;

import java.sql.Date;

/**
 * Table Model -- Brief statement as to file purpose
 * CSIS 212 -- B032023
 * Citations as needed
 */
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date hireDate;
    private String userName;
    private String password;
    private String email;
    private boolean isManager;
    public Employee(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date hireDate, String userName, String password, String email, Integer managerId, String phoneNumber, Boolean isManager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hireDate = hireDate;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.managerId = managerId;
        this.phoneNumber = phoneNumber;
        this.isManager = isManager;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer managerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public java.sql.Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String phoneNumber;

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean isManager) {
        this.isManager = isManager;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
