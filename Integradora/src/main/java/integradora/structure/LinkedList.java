package integradora.structure;

public class LinkedList<T>
{

    private Node<T> head;
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

    public LinkedList()
    {
        this.head = null;
        this.size = 0;
    }

    public void add(T data)
    {
        Node<T> newNode = new Node<>(data);
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node<T> current = head;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void addFirst(T data)
    {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public T get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        return current.data;
    }

    public boolean remove(T data)
    {
        if (head == null) return false;

        if (head.data.equals(data))
        {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null)
        {
            if (current.next.data.equals(data))
            {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public T removeFirst()
    {
        if (head == null)
        {
            throw new RuntimeException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public boolean contains(T data)
    {
        Node<T> current = head;
        while (current != null)
        {
            if (current.data.equals(data))
            {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return head == null;
    }

    public void clear()
    {
        head = null;
        size = 0;
    }

    public void print()
    {
        Node<T> current = head;
        while (current != null)
        {
            System.out.println(current.data);
            current = current.next;
        }
    }
}