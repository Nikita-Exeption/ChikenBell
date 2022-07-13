package org.nikita.chiken_bell.core.entity;

public enum OrderStatus{
    NEW(1),
    ACCEPTED(2),
    CANCELED(2),
    READY(3),
    IN_ROUTE(4),
    DONE(5);

    private final int capacity;

    OrderStatus(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return capacity;
    }
}
