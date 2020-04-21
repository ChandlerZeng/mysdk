package com.example.javatest.javaprinciple;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zengcq
 * @date 2016��12��7��
 * @version 1.0
 * @description �����û�ԭ�� �����û�ԭ��Liskov Substitution Principle�������LSP 
 * ���壺 Functions that use pointers or references to base classes must be able to use objects 
 * of derived classes without knowing it.
 * �������û���ĵط������ܹ�͸����ʹ�����������
 * Ҳ����˵��ֻҪ������ֵĵط�������ܹ����֣������滻Ϊ���಻������κδ�����쳣��
 * ���Ƿ�������������ֵĵط����滻Ϊ����Ϳ��ܳ��������ˡ�
 */
public class LiskSubPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tutu tutu = new Tutu();
		tutu.setViewPoint(new Lijiang());
		tutu.setViewPoint(new Zhangjiajie());
		tutu.travelTo();
		invoke();
	}
	public static void invoke()  
    {  
        //������ڵĵط��������Ӧ���ܹ�����  
        Father f = new Father();  
        Son s = new Son();  
        HashMap map = new HashMap();  
        f.say(map);  
        s.say(map);  
    }  

}

//һ�����������ȫʵ�ָ���ķ���
abstract class ViewPoint {  
    //ȥ��������  
    public abstract void where();  
}

//������������ʵ�����������
class Lijiang extends ViewPoint {  
	   
    @Override 
    public void where() {  
        System.out.println("��ӭ��������...");  
    }  
   
}

class Zhangjiajie extends ViewPoint {  
	   
    @Override 
    public void where() {  
        System.out.println("��ӭ�����żҽ�...");  
    }  
   
}

//������ͿͿ�����������������������ݲ�������ʱͿͿҪȥ�����ξ��㻹�ǳ����
class Tutu {  
    //����Ҫ���εľ���  
    private ViewPoint viewpoint;  
    //ͿͿҪȥ�ľ���  
    public void setViewPoint(ViewPoint viewpoint)  
    {  
        this.viewpoint = viewpoint;  
    }  
       
    public void travelTo()  
    {  
        System.out.println("ͿͿҪȥ������");  
        viewpoint.where();  
    }  
} 

//��������������Լ�������
//Ҳ����˵����������ϣ����Զ��������ķ���������
//�������ǻ���ʵ�ָ���ķ���ʱ����������Ա��Ŵ�
//�����ܹ����ڵĵط���������ܴ��ڣ����Ҳ�������н���б䶯����֮���С�
//���࣬say()����Ĳ�����HashMap���ͣ���Map���͵������͡�����Ϊ����ķ�ΧӦ�ñȸ����

class Father {  
    public Collection say(HashMap map)  
    {  
        System.out.println("���౻ִ��...");  
        return map.values();  
    }  
} 

//���࣬say()����Ĳ��������Map���ͣ�Map��Χ��HashMap���ʹ󣬷���LSPԭ��
//ע�������say���Ǹ�д�����say����Ϊ�������Ͳ�ͬ���������ء�

class Son extends Father {  
    //���������������  
    public Collection say(Map map)  
    {  
        System.out.println("���౻ִ��...");  
        return map.values();  
    }  
} 

//�ġ���д����ʵ�ָ���ķ���ʱ���������Ա���С
//��ʵ����������ƣ�Ҳ���Ǹ����ܳ��ֵĵط�����Ϳ��Գ��֣������滻Ϊ���಻������κδ�������쳣��
//ʹ����Ҳ����֪���Ǹ��໹�����ࡣ���Ƿ������Ͳ����ˣ���������ֵĵط�������δ�ؾ���Ӧ�����Ͼ�����ķ�ΧҪ>=����ķ�Χ��




