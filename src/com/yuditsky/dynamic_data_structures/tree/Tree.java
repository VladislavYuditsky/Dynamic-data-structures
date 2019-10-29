package com.yuditsky.dynamic_data_structures.tree;

import java.util.Iterator;

public class Tree<T> implements Iterable<T> {

    class Node<Type> {
        Type data;
        Node left;
        Node right;

        Node(Type data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(Type data) {
            this.data = data;
        }

        Node() {
            super();
        }
    }

    private Node<T> root;

    public void add(T data) {
        root = add(root, data);
    }

    private Node<T> add(Node<T> node, T data) {
        if (node == null) {
            node = new Node<>(data);
            return node;
        } else if (((Comparable) data).compareTo((Comparable) node.data) < 0) {
            node.left = add(node.left, data);
            return node;
        } else if (((Comparable) data).compareTo((Comparable) node.data) > 0) {
            node.right = add(node.right, data);
            return node;
        }
        return null;
    }

    public String showString() {
        return showString(root);
    }

    private String showString(Node<T> node) {
        StringBuilder result = new StringBuilder();
        if (node != null) {
            result.append(node.data + " ");
            if (node.left != null) {
                result.append(showString(node.left));
            }
            if (node.right != null) {
                result.append(showString(node.right));
            }
        }
        return result.toString();
    }

    public void delete(T data) {
        Node<T> deleteOne = find(root, data);
        if (deleteOne == null) {
            return;
        }
        if (deleteOne == root) {
            Node<T> temp = root.left;
            root = root.right;
            Node<T> temp1 = root;
            while (temp1.left != null) {
                temp1 = temp1.left;
            }
            temp1.left = temp;
            return;
        }
        Node<T> elementBeforeDelete = findBefore(root, data);
        Node<T> leftDelete = deleteOne.left;
        Node<T> rightDelete = deleteOne.right;
        if (((Comparable) elementBeforeDelete.data).compareTo(deleteOne.data) > 0) {
            if (rightDelete == null) {
                elementBeforeDelete.left = leftDelete;
                return;
            }
            elementBeforeDelete.left = rightDelete;
            while (rightDelete.left != null) {
                rightDelete = rightDelete.left;
            }
            rightDelete.left = leftDelete;
        } else {
            if (leftDelete == null) {
                elementBeforeDelete.right = rightDelete;
                return;
            }
            elementBeforeDelete.right = leftDelete;
            while (leftDelete.right != null) {
                leftDelete = leftDelete.right;
            }
            leftDelete.right = rightDelete;
        }
    }

    private Node<T> find(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        if (data.equals(node.data)) {
            return node;
        } else if (((Comparable) data).compareTo(node.data) < 0) {
            node = find(node.left, data);
            return node;
        } else {
            node = find(node.right, data);
            return node;
        }
    }

    private Node<T> findBefore(Node<T> node, T inf) {
        if (node == null) {
            return null;
        }
        if (node.left != null && inf.equals(node.left.data)) {
            return node;
        }
        if (node.right != null && inf.equals(node.right.data)) {
            return node;
        }
        if (((Comparable) inf).compareTo(node.data) < 0) {
            node = findBefore(node.left, inf);
            return node;
        } else {
            node = findBefore(node.right, inf);
            return node;
        }
    }

    public boolean find(T inf) {
        return find(root, inf) != null;
    }

    private class TreeIterator implements Iterator<T> {

        private Node<T> element;

        public TreeIterator() {
            element = root;
            if (element == null) {
                return;
            }

            while (element.left != null) {
                element = element.left;
            }
        }

        @Override
        public boolean hasNext() {
            return element != null;
        }

        @Override
        public T next() {
            Node<T> temp = element;

            if (element.right != null) {
                element = element.right;
                while (element.left != null) {
                    element = element.left;
                }
                return temp.data;
            }
            while (true) {
                if (findBefore(root, element.data) == null) {
                    element = null;
                    return temp.data;
                }
                if (findBefore(root, element.data).left == element) {
                    element = findBefore(root, element.data);
                    return temp.data;
                }
                element = findBefore(root, element.data);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }
}
