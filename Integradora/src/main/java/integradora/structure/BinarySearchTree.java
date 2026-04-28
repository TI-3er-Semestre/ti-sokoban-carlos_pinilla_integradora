package integradora.structure;

public class BinarySearchTree<T extends Comparable<T>>
{

    private Node<T> root;

    private static class Node<T>
    {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data)
        {
            this.data  = data;
            this.left  = null;
            this.right = null;
        }
    }

    public BinarySearchTree()
    {
        this.root = null;
    }

    public void insert(T data)
    {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> node, T data)
    {
        if (node == null)
        {
            return new Node<>(data);
        }

        if (data.compareTo(node.data) < 0)
        {
            node.left = insertRecursive(node.left, data);
        }
        else if (data.compareTo(node.data) > 0)
        {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

    public T search(T data)
    {
        Node<T> result = searchRecursive(root, data);
        return result != null ? result.data : null;
    }

    private Node<T> searchRecursive(Node<T> node, T data)
    {
        if (node == null)
        {
            return null;
        }

        if (data.compareTo(node.data) == 0)
        {
            return node;
        }
        else if (data.compareTo(node.data) < 0)
        {
            return searchRecursive(node.left, data);
        }
        else
        {
            return searchRecursive(node.right, data);
        }
    }

    public void delete(T data)
    {
        root = deleteRecursive(root, data);
    }

    private Node<T> deleteRecursive(Node<T> node, T data)
    {
        if (node == null)
        {
            return null;
        }

        if (data.compareTo(node.data) < 0)
        {
            node.left = deleteRecursive(node.left, data);
        }
        else if (data.compareTo(node.data) > 0)
        {
            node.right = deleteRecursive(node.right, data);
        }
        else
        {
            if (node.left == null)
            {
                return node.right;
            }
            else if (node.right == null)
            {
                return node.left;
            }

            T minValue = findMin(node.right);
            node.data  = minValue;
            node.right = deleteRecursive(node.right, minValue);
        }

        return node;
    }

    public T findMin(Node<T> node)
    {
        T min = node.data;
        while (node.left != null)
        {
            node = node.left;
            min  = node.data;
        }
        return min;
    }

    public T findMax()
    {
        if (root == null) return null;
        Node<T> node = root;
        while (node.right != null)
        {
            node = node.right;
        }
        return node.data;
    }

    public void inOrder()
    {
        inOrderRecursive(root);
    }

    private void inOrderRecursive(Node<T> node)
    {
        if (node != null)
        {
            inOrderRecursive(node.left);
            System.out.println(node.data);
            inOrderRecursive(node.right);
        }
    }

    public void preOrder()
    {
        preOrderRecursive(root);
    }

    private void preOrderRecursive(Node<T> node)
    {
        if (node != null)
        {
            System.out.println(node.data);
            preOrderRecursive(node.left);
            preOrderRecursive(node.right);
        }
    }

    public void postOrder()
    {
        postOrderRecursive(root);
    }

    private void postOrderRecursive(Node<T> node)
    {
        if (node != null)
        {
            postOrderRecursive(node.left);
            postOrderRecursive(node.right);
            System.out.println(node.data);
        }
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public int size()
    {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node<T> node)
    {
        if (node == null)
        {
            return 0;
        }
        return 1 + sizeRecursive(node.left) + sizeRecursive(node.right);
    }

    public int height()
    {
        return heightRecursive(root);
    }

    private int heightRecursive(Node<T> node)
    {
        if (node == null)
        {
            return 0;
        }
        int leftHeight  = heightRecursive(node.left);
        int rightHeight = heightRecursive(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
