package com.example.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ����˼�壬װ��ģʽ���Ǹ�һ����������һЩ�µĹ��ܣ������Ƕ�̬�ģ�
 * Ҫ��װ�ζ���ͱ�װ�ζ���ʵ��ͬһ���ӿڣ�װ�ζ�����б�װ�ζ����ʵ��
 */
public class DecoratorMode implements Sourceable2{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Sourceable2 source = new DecoratorMode();  
	        Sourceable2 obj = new Decorator(source);  
	        obj.method();  
	}

	@Override
	public void method() {
		// TODO Auto-generated method stub
		  System.out.println("the original method!");  
	}

}

interface Sourceable2 {  
    public void method();  
}

class Decorator implements Sourceable2 {  
	  
    private Sourceable2 source;  
      
    public Decorator(Sourceable2 source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method() {  
        System.out.println("before decorator!");  
        source.method();  
        System.out.println("after decorator!");  
    }  
}  