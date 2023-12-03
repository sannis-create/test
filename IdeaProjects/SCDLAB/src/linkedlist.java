//import java.util.*;
//class node<T>
//{
//    T data;
//    node<T> next;
//
//    node<T> getNext()
//    {
//        return next;
//    }
//
//    node(T data)
//    {
//        this.data = data;
//        this.next = null;
//    }
//}
//
//public class linkedlist<T>
//{
//    private node<T> head;
//    private node<T> tail;
//
//    public linkedlist()
//    {
//        this.head = null;
//    }
//
//    public boolean isEmpty()
//    {
//        return head == null;
//    }
//
//    public void Insert_At_Begining(T data1)
//    {
//        node<T> new_node = new node<>(data1);
//        new_node.next = head;
//        head = new_node;
//    }
//
//    public void InsertAtEnd(T data1)
//    {
//        node<T> new_node = new node<>(data1);
//        if (head == null)
//        {
//            head = new_node;
//            return;
//        }
//
//        node<T> current = head;
//        while (current.next != null)
//        {
//            current = current.next;
//        }
//        current.next = new_node;
//    }
//
//    public void delete(T data1)
//    {
//        if (head == null) {
//            return;
//        }
//
//        if (head.data.equals(data1))
//        {
//            head = head.next;
//            return;
//        }
//
//        node<T> current = head;
//        while (current.next != null)
//        {
//            if (current.next.data.equals(data1))
//            {
//                current.next = current.next.next;
//                return;
//            }
//            current = current.next;
//        }
//    }
//
//    public void print()
//    {
//        node<T> current = head;
//        while (current != null)
//        {
//            System.out.print(current.data + ",");
//            current = current.next;
//        }
//        System.out.println();
//    }
//
//    public static void main(String[] args)
//    {
//        linkedlist<Integer> ll = new linkedlist<>();
//
//        ll.Insert_At_Begining(1);
//        ll.InsertAtEnd(3);
//        ll.Insert_At_Begining(0);
//        ll.InsertAtEnd(5);
//
//        System.out.print("Original List: ");
//        ll.print();
//
//        ll.delete(1);  // delete by value
//        System.out.print("List after deleting 1: ");
//        ll.print();
//    }
//}