package integradora.structure;

public class HashTable<K, V>
{

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR   = 0.75;

    private Entry<K, V>[] table;
    private int size;

    private static class Entry<K, V>
    {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value)
        {
            this.key   = key;
            this.value = value;
            this.next  = null;
        }
    }

    @SuppressWarnings("unchecked")
    public HashTable()
    {
        this.table = new Entry[DEFAULT_CAPACITY];
        this.size  = 0;
    }

    public void put(K key, V value)
    {
        resizeIfNeeded();
        int idx = index(key);
        Entry<K, V> entry = table[idx];
        while (entry != null)
        {
            if (entry.key.equals(key))
            {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[idx];
        table[idx]    = newEntry;
        size++;
    }

    public V get(K key)
    {
        Entry<K, V> entry = table[index(key)];
        while (entry != null)
        {
            if (entry.key.equals(key))
            {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(K key)
    {
        return get(key) != null;
    }

    public boolean remove(K key)
    {
        int idx  = index(key);
        Entry<K, V> prev = null;
        Entry<K, V> curr = table[idx];
        while (curr != null)
        {
            if (curr.key.equals(key))
            {
                if (prev == null)
                {
                    table[idx] = curr.next;
                }
                else
                {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    private int index(K key)
    {
        return Math.abs(key.hashCode()) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resizeIfNeeded()
    {
        if ((double) size / table.length >= LOAD_FACTOR)
        {
            Entry<K, V>[] old = table;
            table = new Entry[old.length * 2];
            size  = 0;
            for (Entry<K, V> head : old)
            {
                Entry<K, V> e = head;
                while (e != null)
                {
                    put(e.key, e.value);
                    e = e.next;
                }
            }
        }
    }
}