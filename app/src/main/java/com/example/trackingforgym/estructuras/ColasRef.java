package com.example.trackingforgym.estructuras;
public class ColasRef{
    private Node front, rear;

    public ColasRef(){
        front = null;
        rear = null;
    }
    public void Enqueue(String item){
        Node ref = new Node(item);
        if (front == null){
            front = ref;
        } else{
            rear.setNext(ref);
        }
        rear = ref;
    }
    public String Dequeue(){
        if (Empty()){
            return "vacio";
        }
        String data = front.getData();
        front = front.getNext();
        if (front == null){
            rear = null;
        }
        return data;

    }

    public void imprimir(){
        Node x = front;
        while (x != null){
            System.out.println(x.getData());
            x = x.getNext();
        }

    }
    public boolean Empty(){
        return front == null;

    }
    public boolean full(){
        return false;

    }
}
