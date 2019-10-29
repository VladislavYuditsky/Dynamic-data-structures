package com.yuditsky.dynamic_data_structures.tree;

public class Main {

    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>();

        tree.add(10);
        tree.add(11);
        tree.add(8);
        tree.add(7);
        tree.add(9);

        System.out.println("Tree: " + tree.showString());
/*
        tree.add(2);
        System.out.println();
        System.out.println("Add element(2): " + tree.showString());

        tree.delete(8);
        System.out.println();
        System.out.println("Delete element: " + tree.showString());

        System.out.println();
        System.out.println("Find the element(2): " + (tree.find(2)));
        System.out.println("Find the element(36): " + (tree.find(36)));

*/
        System.out.println();
        System.out.println("Iterator:");
        for (Integer integer : tree) {
            System.out.print(integer + " ");
        }
    }
}
