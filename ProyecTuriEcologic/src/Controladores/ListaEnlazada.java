/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.MDestinoTuristico;

/**
 *
 * @author Santi
 */
public class ListaEnlazada {
    public Node head = null;
    public Node tail = null;
    public int size = 0;

    public static class Node{
        public MDestinoTuristico data = null;
        public Node prev = null;
        public Node next = null;

        Node(MDestinoTuristico item) {
            this.data = item;
        }
        
        public Node getPrev(){
            return this.prev;
        }
    }
    
    public int size(){
        return this.size;
    }

    public int add(MDestinoTuristico item) {
        Node newNode = new Node(item);
        if (tail == null) {
            this.head = newNode;
            this.tail = newNode;
            return ++this.size;
        }

        newNode.prev = tail;
        this.tail.next = newNode;
        this.tail = newNode;
        return ++this.size;
    }
    
    public MDestinoTuristico get(int index){
        if (index < this.size / 2){
            Node node = this.head;
            for (int i = 0; i < index; i++){
                node = node.next;
            }
            return node.data;
        }else{
            Node node = this.tail;
            for (int i = this.size - 1; i > index; i--){
                node = node.prev;
            }
            return node.data;
        }
    }

}
