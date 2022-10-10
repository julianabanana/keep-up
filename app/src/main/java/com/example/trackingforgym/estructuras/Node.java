package com.example.trackingforgym.estructuras;

public class Node {
    private String data;
    private Node next;
    public Node(){
        this("");
    }
    public Node(String data) {
        this.data = data;
        next = null;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public Node getNext() {
        return next;
    }
    public void setNext(Node next) {
        this.next = next;
    }

}
