package com.example.trackingforgym.data;

public class HashSet <T>{
    private  LinkedList1[] harray;


    public HashSet(int size) {
        harray = new LinkedList1[size];
        for(int i=0; i<harray.length; i++)
            harray[i] = new LinkedList1();
    }

    public boolean containsKey(int key){
        if(harray[modHash(key)].searchKey(key)){
            System.out.println("existe");
            return true;
        }else{
            System.out.println("no existe");
            return false;
        }

    }
    public T getValue(int key){
        T value=(T)harray[modHash(key)].searchValue(key);
        System.out.println("el value de "+key+" es "+value);
        return value;

    }
    public void add(int key, T value) {
        if(harray[modHash(key)].insert(key,value))
            System.out.println("key, " + key + " y value "+value+", insetado");
        else
            System.out.println("no insertado");
    }
    public void remove(int key) {
        if(!harray[modHash(key)].delete(key))
            System.out.print("key, " + key + ", no eliminado");
        else
            System.out.println("key, " + key + " eliminado");
    }
    public void printHash() {
        for(int i=0; i<harray.length; i++) {
            System.out.print("harray[" + i + "] ");
            harray[i].print();
            System.out.println();
        }
    }
    private int modHash(int key) {
        return key % harray.length;
    }

    class LinkedList1<T>{
        Node<T> head;
        Node<T> anterior;
        Node<T> position;

        public LinkedList1() {
            head = null;
        }
        public boolean delete(int item) {
            boolean deleted=false;

            if (!empty()){
                if(searchKey(item)) {
                    if (anterior==null){
                        head = position;
                        head = head.getNext();
                    }else{
                        anterior.setNext(position.getNext()) ;
                    }


                    deleted = true;
                }

            }


            else{
                System.out.println("List is Empty");
            }

            return deleted;
        }
        public boolean empty() {
            return head==null;
        }
        public boolean insert(int key,T value) {
            boolean inserted;
            Node<T> ptr,prev;
            inserted = false;
            ptr = head;
            prev = null;
            if (searchKey(key)){
                while(ptr.getKey()!=key ) {
                    prev = ptr;
                    ptr = ptr.getNext();
                }
                ptr.setData(value);
                inserted = true;
            }else{
                while(ptr != null ) {
                    prev = ptr;
                    ptr = ptr.getNext();
                }
                if (ptr == null ) {
                    inserted = true;
                    Node<T> newp = new Node<T>();
                    newp.setKey(key);
                    newp.setData(value);
                    newp.setNext(ptr);
                    if(prev == null)
                        head = newp;
                    else
                        prev.setNext(newp);
                }

            }
            return inserted;
        }


        public T searchValue(int item) {
            anterior = null;
            position = head;
            while(position != null){
                if(position.getKey() == item){
                    return (T)position.getData();
                }
                else{
                    anterior = position;
                    position= position.getNext();


                }
            }
            return (T) "no tiene valor";
        }
        public boolean searchKey(int item) {
            position = head;
            anterior = null;
            while(position != null){
                if(position.getKey() == item){
                    return true;
                }
                else{
                    anterior = position;
                    position= position.getNext();
                }

            }
            return false;
        }
        public void print(){

            Node ref = head;
            while(ref != null) {
                System.out.print(" key "+ref.getKey()+" value "+ref.getData());
                ref = ref.getNext();
            }
        }


    }

    class Node<T> {
        private T data;
        private int key;
        private Node<T> next;
        public Node(){
            data=null;
            next=null;
        }
        public Node(T data){
            next = null;
            this.data = data;
        }
        public Node(T data, int key) {
            this.data = data;
            this.key = key;
            next = null;
        }
        public T getData() {
            return data;
        }
        public void setData(T data) {
            this.data = data;
        }
        public void setKey(int key){
            this.key = key;
        }
        public int getKey() {
            return key;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }

    }


}
