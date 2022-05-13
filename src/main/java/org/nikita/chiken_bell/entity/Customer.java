package org.nikita.chiken_bell.entity;

public class Customer {

    private String name;

    private String phone;

    private String adress;

    public Customer(String name, String phone){
        isPhoneCheck(phone);
        this.name = name;
        this.phone = phone;
    }

    public Customer(String name, String phone, String adress){
        isPhoneCheck(phone);
        this.name = name;
        this.phone = phone;
        this.adress = adress;
    }

    private void isPhoneCheck(String phone){
        if (phone.isBlank()){
            throw new UnsupportedOperationException();
        }
        if(!phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}")){
            throw new UnsupportedOperationException();
        }
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
}
