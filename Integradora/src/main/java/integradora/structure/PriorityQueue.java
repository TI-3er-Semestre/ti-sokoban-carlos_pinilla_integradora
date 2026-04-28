package integradora.structure;

public class PriorityQueue<T extends Comparable<T>>
{

    private Object[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public PriorityQueue()
    {
        this.heap = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public void enqueue(T item)
    {
        ensureCapacity();
        heap[size] = item;
        bubbleUp(size);
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new RuntimeException("PriorityQueue is empty");
        }
        T min = (T) heap[0];
        size--;
        heap[0] = heap[size];
        heap[size] = null;
        bubbleDown(0);
        return min;
    }

    @SuppressWarnings("unchecked")
    public T peek()
    {
        if (isEmpty())
        {
            throw new RuntimeException("PriorityQueue is empty");
        }
        return (T) heap[0];
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public int size()
    {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void bubbleUp(int i)
    {
        while (i > 0)
        {
            int parent = (i - 1) / 2;
            if (((T) heap[i]).compareTo((T) heap[parent]) < 0)
            {
                swap(i, parent);
                i = parent;
            }
            else
            {
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void bubbleDown(int i)
    {
        while (true)
        {
            int left    = 2 * i + 1;
            int right   = 2 * i + 2;
            int smallest = i;

            if (left < size && ((T) heap[left]).compareTo((T) heap[smallest]) < 0)
            {
                smallest = left;
            }
            if (right < size && ((T) heap[right]).compareTo((T) heap[smallest]) < 0)
            {
                smallest = right;
            }
            if (smallest == i)
            {
                break;
            }
            swap(i, smallest);
            i = smallest;
        }
    }

    private void swap(int a, int b)
    {
        Object tmp = heap[a];
        heap[a]    = heap[b];
        heap[b]    = tmp;
    }

    private void ensureCapacity()
    {
        if (size == heap.length)
        {
            Object[] bigger = new Object[heap.length * 2];
            System.arraycopy(heap, 0, bigger, 0, heap.length);
            heap = bigger;
        }
    }
}