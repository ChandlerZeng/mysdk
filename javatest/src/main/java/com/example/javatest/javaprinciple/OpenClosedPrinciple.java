package com.example.javatest.javaprinciple;

import java.util.ArrayList;

/**
 * @author Zengcq
 * @date 2016��12��7��
 * @version 1.0
 * @description
 * ����ԭ��Open Closed Principle��
����ԭ��ĺ����ǣ�����չ���ţ����޸Ĺرա�
�׻���˼�������Ǹı�һ�����ʱ��������չ�������ܣ���Ӧ��ͨ����չ�ķ�ʽ���ﵽ����ĸı䣬
����Ӧ���޸�ԭ�д�����ʵ�ֱ仯��
����ԭ������ǰ5��ԭ���һ�������ܽᣬǰ�����ǿ���ԭ���һЩ����ʵ�֣��������ʹ�ÿ���ԭ��
��ʵ�е��飬��Ϊ��û��һ���̶���ģʽ���������ձ�֤������߳���ĸ����ԡ���ά���Ե�Ҫ��
Ҫʹ����һԭ����Ҫ���������˼�롰����չ���ţ����޸Ĺرա���������������ԭ����ݾ�����������Ŀ��
�����������˼��������������Ҫ�����������鼮��
������ʹ�ÿ���ԭ���һ����ʾ��������Щ��׼ȷ�������������˼����ᾫ�񣩡�
����һ���ӿڣ�Ѱ����Ů
 */
public class OpenClosedPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Street street = new Street();
		System.out.println("----------��Ů������----------");  
        for(IForeigner girl:street.girls)  
        {  
            System.out.println("����:"+girl.getName()+" ����:"+girl.getAge()+" ������"+girl.getCountry()+
                    "  ����:"+girl.getFace()+"  ���:"+girl.getFigure());  
        }  
	}

}

interface IFindGirl {  
    //����  
    public int getAge();  
    //����  
    public String getName();  
    //����  
    public String getFace();  
    //���  
    public String getFigure();  
}

class FindGirl implements IFindGirl {  
    private String name;  
    private int age;  
    private String face;  
    private String figure;  
       
    public FindGirl(String name, int age, String face, String figure)  
    {  
        this.name = name;  
        this.age = age;  
        this.face = face;  
        this.figure = figure;  
    }  
   
    @Override 
    public int getAge() {  
        return age;  
    }  
   
    @Override 
    public String getFace() {  
        return face;  
    }  
   
    @Override 
    public String getFigure() {  
        return figure;  
    }  
   
    @Override 
    public String getName() {  
        return name;  
    }  
       
   
}

class Street {  
//     final static ArrayList<IFindGirl> girls = new ArrayList<IFindGirl>();  
	final static ArrayList<IForeigner> girls = new ArrayList<IForeigner>();  
    //��̬��ʼ����  
    static 
    {  
//        girls.add(new FindGirl("�ź���",23,"�ɰ���","165cm/47kg"));  
//        girls.add(new FindGirl("��ԲԲ",33,"ʱ����","165cm/48kg"));  
//        girls.add(new FindGirl("������",19,"�崿��","168cm/47kg"));  
    	girls.add(new ForeignerGirl("�ź���",23,"�¹�","�ɰ���","165cm/47kg"));  
        girls.add(new ForeignerGirl("��ԲԲ",33,"�й�","ʱ����","165cm/48kg"));  
        girls.add(new ForeignerGirl("������",19,"�й�","�崿��","168cm/47kg"));  
        girls.add(new ForeignerGirl("Avirl",28,"����","�Ը���","160cm/45kg"));
    }  
}

//�������������ֳ�һ�������Ů�����Ļ�����������һ��������������ͨ���޸Ľӿڡ��޸�ʵ���ࡢͨ����չ��ʵ�֡�
//����޸Ľӿڣ�Ҳ����ζ���޸�ʵ���࣬��������Ŀ�ı䶯̫���ˣ����Բ��Ƽ�
//����޸�ʵ���࣬�������ܽ�����⣬����������Щǣǿ�������Ҫ�����䶯��ʱ����Ե��߼�����
//���ԣ�ͨ����չ��ʵ������򵥵ķ�ʽ
//�����չ��
//���Զ���һ��IForeigner�ӿڼ̳���IFindGirl����IForeigner�ӿ�����ӹ�������getCountry()��
//Ȼ��ʵ������ӿڼ��ɣ�Ȼ���ֻ��Ҫ�ڳ�����������΢�޸ľͿ�����

interface IForeigner extends IFindGirl {  
    //����  
    public String getCountry();  
}

class ForeignerGirl implements IForeigner {  
    private String name;  
    private int age;  
    private String country;  
    private String face;  
    private String figure;  
       
    public ForeignerGirl(String name, int age, String country, String face, String figure)  
    {  
        this.name = name;  
        this.age = age;  
        this.country = country;  
        this.face =face;  
        this.figure = figure;  
    }  
    @Override 
    public String getCountry() {  
        // TODO Auto-generated method stub  
        return country;  
    }  
   
    @Override 
    public int getAge() {  
        // TODO Auto-generated method stub  
        return age;  
    }  
   
    @Override 
    public String getFace() {  
        // TODO Auto-generated method stub  
        return face;  
    }  
   
    @Override 
    public String getFigure() {  
        // TODO Auto-generated method stub  
        return figure;  
    }  
   
    @Override 
    public String getName() {  
        // TODO Auto-generated method stub  
        return name;  
    }  
   
} 