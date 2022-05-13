package org.nikita.chiken_bell.entity;

public class Customer {

    private String name;

    private String phone;

    private String adress;

    public Customer(String name, String phone){
        isEmptyPhone(phone);
        this.name = name;
        this.phone = phone;
    }

    public Customer(String name, String phone, String adress){
        isEmptyPhone(phone);
        this.name = name;
        this.phone = phone;
        this.adress = adress;
    }

    private void isEmptyPhone(String phone){
        if (phone.isBlank()){
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
