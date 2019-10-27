package com.yuditsky.dynamic_data_structures.list;

import java.util.Iterator;

public class BidirectionalList<T> implements Iterable<T> {

    class Node<Type> {
        private Type data;
        private Node<Type> next;
        private Node<Type> prev;

        private Node(Type data, Node<Type> next, Node<Type> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node(Type data) {
            this.data = data;
        }

        private Node() {
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public void addLast(T data) {
        if (this.head == null) {
            Node<T> element = new Node<>(data);
            this.head = element;
            this.tail = element;
        } else {
            Node<T> element = new Node<>(data, null, this.tail);
            this.tail.next = element;
            this.tail = element;
        }
        length++;
    }

    public void addFirst(T data) {
        if (this.head == null && this.tail == null) {
            Node<T> element = new Node<>(data);
            this.head = element;
            this.tail = element;
        } else {
            Node<T> element = new Node<>(data, this.head, null);
            this.head.prev = element;
            this.head = element;
        }
        length++;
    }

    public void add(T data, int index) {
        Node<T> temp = head;
        if (temp == null) {
            Node<T> element = new Node<>(data);
            this.head = element;
            this.tail = element;
        } else {
            if (index <= 0) {
                addFirst(data);
                return;
            }
            while (--index > 0 && temp != null) {
                temp = temp.next;
            }
            if (temp == null) {
                addLast(data);
            } else {
                Node<T> temp2 = temp.next;
                Node<T> element = new Node<>(data, temp2, temp);
                temp.next = element;
                if (temp2 != null) {
                    temp2.prev = element;
                }
            }
        }

        length++;
    }

    public String getStringOfList() {
        StringBuilder result = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            result.append(temp.data + " ");
            temp = temp.next;
        }
        return result.toString();
    } //////////////////////////////////////////////////////////////////////

    public void deleteLast() {
        if (head != null) {
            tail = tail.prev;
            tail.next = null;
            length--;
        }
    }

    public void deleteFirst() {
        if (head != null) {
            head = head.next;
            head.prev = null;
            length--;
        }
    }

    public void delete(int index) {
        Node<T> temp = head;
        if (head != null) {
            if (index <= 0) {
                deleteFirst();
                return;
            }
            while (index-- > 0 && temp != null) {
                temp = temp.next;
            }
            if (temp == null || temp == tail) {
                deleteLast();
                return;
            } else {
                Node<T> prevTemp = temp.prev;
                Node<T> nextTemp = temp.next;
                prevTemp.next = nextTemp;
                nextTemp.prev = prevTemp;
                length--;
            }
        }
    }

    public int length() {
        return length;
    }

    private Node<T> find(int index) {
        Node<T> result = head;
        if (head == null) {
            return null;
        }
        if (index <= 0) {
            return head;
        }
        while (index-- > 0 && result != null) {
            result = result.next;
        }
        if (null == result || result == tail) {
            return tail;
        }
        return result;
    }

    public T get(int index) {
        return find(index).data;
    }

    public void set(int index, T data) {
        Node<T> temp = find(index);
        if (temp != null) {
            temp.data = data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> iterator = new Iterator<T>() {

            private Node<T> element = head;

            @Override
            public boolean hasNext() {
                return element != null;
            }

            @Override
            public T next() {
                T result = element.data;
                element = element.next;
                return result;
            }
        };
        return iterator;
    }
}