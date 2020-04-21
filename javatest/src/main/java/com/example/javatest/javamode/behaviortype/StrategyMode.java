package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ����ģʽ������һϵ���㷨������ÿ���㷨��װ������ʹ���ǿ����໥�滻�����㷨�ı仯����Ӱ�쵽ʹ���㷨�Ŀͻ���
 * ��Ҫ���һ���ӿڣ�Ϊһϵ��ʵ�����ṩͳһ�ķ�����
 * ���ʵ����ʵ�ָýӿڣ����һ�������ࣨ���п��ޣ����ڸ����ࣩ���ṩ������������ϵͼ���£�
 */
public class StrategyMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		 String exp = "2-8";
//		 String exp = "2+8";
		 String exp = "2*8";
	        ICalculator cal = new Multiply();
	        String result = cal.calculate(exp);
	        System.out.println(result);  
	}

}

interface ICalculator {
    public String calculate(String exp);
}

abstract class AbstractCalculator {  
    
    public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    }  
}

class Plus extends AbstractCalculator implements ICalculator {  
	  
    @Override  
    public String calculate(String exp) {
        int arrayInt[] = split(exp,"\\+");
        int result = arrayInt[0]+arrayInt[1];
        return arrayInt[0]+"+"+arrayInt[1]+"="+result;
    }  
}

class Minus extends AbstractCalculator implements ICalculator {  
	  
    @Override  
    public String calculate(String exp) {
        int arrayInt[] = split(exp,"-");
        int result = arrayInt[0]-arrayInt[1];
        return arrayInt[0]+"-"+arrayInt[1]+"="+result;
    }
  
}

class Multiply extends AbstractCalculator implements ICalculator {  
	  
    @Override  
    public String calculate(String exp) {
        int arrayInt[] = split(exp,"\\*");  
        int result = arrayInt[0]*arrayInt[1];
        return arrayInt[0]+"*"+arrayInt[1]+"="+result;
    }  
}  


