package integradora.structure;

public class Queue<T>
{

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T>
    {
        T data;
        Node<T> next;

        Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    public Queue()
    {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void enqueue(T data)
    {
        Node<T> newNode = new Node<>(data);
        if (tail != null)
        {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null)
        {
            head = newNode;
        }
        size++;
    }

    public T dequeue()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null)
        {
            tail = null;
        }
        size--;
        return data;
    }

    public T peek()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Queue is empty");
        }
        return head.data;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public int size()
    {
        return size;
    }
}