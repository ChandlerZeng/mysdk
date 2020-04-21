package com.example.datastructure.linklist;

public class Test {
    public static void main(String[] args){
        doubleLinkedListTest();
    }

    private static void doubleLinkedListTest(){
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addHead("A");
        linkedList.addHead("B");
        linkedList.addHead("C");
        linkedList.addTail("D");
        linkedList.addTail("E");
        linkedList.display();
        linkedList.deleteHead();
        linkedList.deleteTail();
        linkedList.display();
    }

    private static void stackSingleLinkTest(){
        StackSingleLink stackSingleLink = new StackSingleLink();
        stackSingleLink.push("A");
        stackSingleLink.push("B");
        stackSingleLink.push("C");
        stackSingleLink.push("D");
        stackSingleLink.push("E");
        stackSingleLink.display();
    }

    private static void singleLinkedListTest(SingleLinkedList singleList){
        //打印当前链表信息
        singleList.display();
        //删除C
        singleList.delete("C");
        singleList.delete("A");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
        System.out.println(singleList.find("A"));
    }
}
