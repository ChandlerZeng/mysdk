package com.example.javatest.javamode.structuretype;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ���ģʽ��ʱ�ֽв���-����ģʽ�ڴ����������νṹ������ʱ�ȽϷ���
 */
public class CompositeMode {
	TreeNode root = null;  
	  
    public CompositeMode(String name) {  
        root = new TreeNode(name);  
    }  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompositeMode tree = new CompositeMode("A");  
        TreeNode nodeB = new TreeNode("B");  
        TreeNode nodeC = new TreeNode("C");  
          
        nodeB.add(nodeC);  
        tree.root.add(nodeB);  
        System.out.println("build the tree finished!"); 
	}

}

class TreeNode {
    
    private String name;  
    private TreeNode parent;  
    private Vector<TreeNode> children = new Vector<TreeNode>();  
      
    public TreeNode(String name){  
        this.name = name;  
    }  
  
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public TreeNode getParent() {  
        return parent;  
    }  
  
    public void setParent(TreeNode parent) {  
        this.parent = parent;  
    }  
      
    //��Ӻ��ӽڵ�  
    public void add(TreeNode node){  
        children.add(node);  
    }  
      
    //ɾ�����ӽڵ�  
    public void remove(TreeNode node){  
        children.remove(node);  
    }  
      
    //ȡ�ú��ӽڵ�  
    public Enumeration<TreeNode> getChildren(){  
        return children.elements();  
    }  
}  