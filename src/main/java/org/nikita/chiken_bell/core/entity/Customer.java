package org.nikita.chiken_bell.core.entity;

import org.nikita.chiken_bell.core.exception.PhoneEmptyException;
import org.nikita.chiken_bell.core.exception.PhoneIncorrectException;

public class Customer {

    private String name;

    private String phone;

    private String adress;


    public Customer(String name, String phone){
        phoneEmptyAndCorrectCheck(phone);
        this.name = name;
        this.phone = phone;
    }

    public Customer(String name, String phone, String adress){
        this(name, phone);
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    private void phoneEmptyAndCorrectCheck(String phone){
        if (phone.isBlank()){
            throw new PhoneEmptyException();
        }
        if(!phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}")){
            throw new PhoneIncorrectException();
        }
    }
}
