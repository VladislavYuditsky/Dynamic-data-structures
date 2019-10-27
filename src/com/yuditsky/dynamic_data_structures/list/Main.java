package com.yuditsky.dynamic_data_structures.list;

public class Main {

    public static void main(String argv[]) {

        BidirectionalList<Integer> bidirectionalList = new BidirectionalList<>();

        bidirectionalList.addLast(2);
        bidirectionalList.addLast(3);
        bidirectionalList.addLast(4);
        bidirectionalList.addLast(5);
        bidirectionalList.addFirst(1);

        System.out.println("BidirectionalList: " + bidirectionalList.getStringOfList());
        System.out.println();

        int element = 2;
        int index = 2;
        bidirectionalList.add(element, index);
        System.out.println("Add element " + element + " at index " + index);
        System.out.println(bidirectionalList.getStringOfList());

        System.out.println();
        index = 3;
        bidirectionalList.delete(index);
        System.out.println("Delete element at index " + index);
        System.out.println(bidirectionalList.getStringOfList());

        System.out.println();
        index = 7;
        bidirectionalList.delete(index);
        System.out.println("Delete element at index " + index);
        System.out.println(bidirectionalList.getStringOfList());

        index = -2;
        System.out.println();
        System.out.println("Get element at index " + index);
        System.out.println(bidirectionalList.get(index));

        int data = 0;
        index = 3;
        bidirectionalList.set(index, data);
        System.out.println();
        System.out.println("Set data " + data + " at index " + index);
        System.out.println(bidirectionalList.getStringOfList());

        System.out.println();
        System.out.println("Iterator:");
        for (Integer integer : bidirectionalList) {
            System.out.print(integer + " ");
        }

    }
}
