package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ����һ��ģ�巽��ģʽ������ָ��һ���������У���һ�����������ٶ���1...n�������������ǳ���ģ�Ҳ������ʵ�ʵķ�����
 * ����һ���࣬�̳иó����࣬��д���󷽷���ͨ�����ó����࣬ʵ�ֶ�����ĵ���
 * ������AbstractCalculator���ж���һ��������calculate��calculate()����spilt()�ȣ�
 * Plus��Minus�ֱ�̳�AbstractCalculator�࣬
 * ͨ����AbstractCalculator�ĵ���ʵ�ֶ�����ĵ���
 */
public class TemplateMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp = "8+8";  
        AbstractCalculator2 cal = new Plus2();  
        int result = cal.calculate(exp, "\\+");  
        int result2 = cal.calculate(2, 5);  
        System.out.println(result);  
        System.out.println(result2);  
	}

}

abstract class AbstractCalculator2 {  
    
    /*��������ʵ�ֶԱ������������ĵ���*/  
    public final int calculate(String exp,String opt){  
        int array[] = split(exp,opt);  
        return calculate(array[0],array[1]);  
    }  
      
    /*��������д�ķ���*/  
    abstract public int calculate(int num1,int num2);  
      
    public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    }  
}

class Plus2 extends AbstractCalculator2 {  
	  
    @Override  
    public int calculate(int num1,int num2) {  
        return num1 + num2;  
    }  
}  
/*
 * �Ҹ��������С�����ִ�й��̣����Ƚ�exp��"\\+"��������
 * ����AbstractCalculator�����calculate(String,String)������
 * ��calculate(String,String)�����ͬ���split()��֮���ٵ���calculate(int ,int)������������������뵽�����У�
 * ִ����return num1 + num2��
 * ��ֵ���ص�AbstractCalculator�࣬����result����ӡ������������֤�����ǿ�ͷ��˼·��
 */

