package integradora.structure;

public class Stack<T>
{

    private Node<T> top;
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

    public Stack()
    {
        this.top  = null;
        this.size = 0;
    }

    public void push(T data)
    {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public T pop()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public T peek()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    public int size()
    {
        return size;
    }
}