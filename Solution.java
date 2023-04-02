import java.util.Scanner;

class Node{
    Object data;
    Node next;
    Node prev;
    Node(Object data){
        this.data = data;
    }
}

class CircularLinkedList {
    Node head;
    Node tail;
    Node pointer;
    int size = 0;

    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            head.prev = tail;
            tail.next = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            head.prev = tail;
            tail.next = head;
        }
        size++;
    }

    public void sisip(Object data, int index) {
        Node newNode = new Node(data);
        if (index < 0){
            index = index%size;
            index = size + index;
        }
        if (index >= size){
            index = index%size;
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        if (head == curr){
            head = newNode;
        }
        if (tail == curr){
            tail = newNode;
        }
        newNode.prev = curr.prev;
        newNode.next = curr;
        curr.prev.next = newNode;
        curr.prev = newNode;


    }

    public Object remove(int index){
        Node curr = head;
        if (index < 0){
            index = index%size;
            index = index + size;
        }
        if (index >= size){
            index = index%size;
        }
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        if(pointer == curr){
            pointer = pointer.next;
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        return curr.data;
    }
    public void removeAt(int index){
        Node curr = head;
        if (index < 0){
            index = index%size;
            index = index + size;
        }
        if (index >= size){
            index = index%size;
        }
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        if(curr == pointer){
            pointer = pointer.next;
        }if (curr == head){
            head = head.next;
            head.prev = tail;
            tail.next = head;
        }else if (curr == tail){
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        }else {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
        size--;
    }

    public void move(int par1, int par2){
        if(par1 == par2) {
            return;
        }
        if (size == 0){
            return;
        }
        Object data = remove(par1);
        sisip(data,par2);


    }

    public void print(String command){
        if (command.equals("PREV")){
            if (pointer == null){
                pointer = tail;
                pointer = pointer.next;
                System.out.println("Sedang diputar: " + pointer.data);
            } else{
                pointer = pointer.prev;
                System.out.println("Sedang diputar: " + pointer.data);
            }
        }else if (command.equals("NEXT")) {
            if (pointer == null){
                pointer = tail;
            }
            pointer = pointer.next;
            System.out.println("Sedang diputar: " + pointer.data);
        }
    }

    public void play(int index){
        Node curr = head;
        if (index >= size){
            index = index%size;
        }
        if (index < 0){
            index = index%size;
            index = index + size;
        }
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        System.out.println("Sedang diputar: " + curr.data);
        pointer = curr;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CircularLinkedList songs = new CircularLinkedList();
        while (true){
            String[] command = scan.nextLine().split(" ");
            if(command[0].equals("INSERT")){
                songs.add(command[1]);
            } else if (command[0].equals("REMOVE")) {
                songs.removeAt(Integer.parseInt(command[1]));
            } else if (command[0].equals("MOVE")) {
                songs.move(Integer.parseInt(command[1]),Integer.parseInt(command[2]));
            } else if (command[0].equals("PLAYAT")) {
                songs.play(Integer.parseInt(command[1]));
            } else if (command[0].equals("NEXT")) {
                songs.print(command[0]);
            } else if (command[0].equals("PREV")) {
                songs.print(command[0]);
            } else if (command[0].equals("EXIT")) {
                break;
            }
        }
    }
}
