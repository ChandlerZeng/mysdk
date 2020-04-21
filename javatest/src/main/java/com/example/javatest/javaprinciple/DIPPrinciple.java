package com.example.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016��12��7��
 * @version 1.0
 * @description ��������ԭ��Dependence Inversion Principle�������DIP ���� High level
 *              modules should depend upon low level modules. Both should depend
 *              upon abstractions. Abstractions should not depend upon details.
 *              Details should depend upon abstractions. ��
 *              1���߲�ģ�鲻Ӧ�������Ͳ�ģ�飬���߶�Ӧ�������ڳ��󣨳������ӿڣ� 
 *              2�����󣨳������ӿڣ���Ӧ��������ϸ�ڣ�����ʵ���ࣩ
 *              3��ϸ�ڣ�����ʵ���ࣩӦ���������� ���󣺼��������ӿڣ������ǲ��ܹ�ʵ�����ġ�
 *              ϸ�ڣ��������ʵ���࣬ʵ�ֽӿڻ��߼̳г��������������࣬���߿���ͨ���ؼ���newֱ�ӱ�ʵ������
 *              ����������ԭ��ı�����ʿ����ͨ������
 *              ���������ӿڣ�ʹ�������ģ���ʵ�ֱ˴˶��������໥Ӱ�죬ʵ��ģ��������ϡ�
 *              �������ԭ��Ҳ��6�����ԭ����������ʵ�ֵ���
 *              �����û��ʵ�����ԭ����ôҲ����ζ�ſ���ԭ�򣨶���չ���������޸Ĺرգ�Ҳ�޷�ʵ�֡�
 */
public class DIPPrinciple {

	public static void main(String[] args) {
		Tutus tutu = new Tutus();  
        IFood food = new Noodles();  
        IFood food2 = new Rice();  
        tutu.cook(food);  
        tutu.cook(food2);  

	}

}

//�������������ַ�ʽ��ʵ��
//1��ͨ�����캯��������������
//�����ڹ��캯���е���Ҫ���ݵĲ����ǳ������ӿڵķ�ʽʵ�֡�
//2��ͨ��setter����������������
//�����������õ�setXXX�����еĲ���Ϊ�������ӿڣ���ʵ�ִ�����������
//3���ӿ�����ʵ����������

/*class Tutu {  
    //ͿͿ�Ǹ�Ů����������  
    public void cook(Noodles noodles)  
    {  
        noodles.eat();  
    }  
} 

class Noodles {  
    //������  
    public void eat()  
    {  
        System.out.println("ͿͿ������...");  
    }  
} */

class Tutus {  
    //ͿͿ�Ǹ�Ů����������  
    public void cook(IFood food)  
    {  
        food.eat();  
    }  
}

interface IFood{
	void eat();
}

class Noodles implements IFood{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		 System.out.println("ͿͿ������...");  
	}
	
}

class Rice implements IFood{

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		 System.out.println("ͿͿ���׷�...");  
	}
	
}

