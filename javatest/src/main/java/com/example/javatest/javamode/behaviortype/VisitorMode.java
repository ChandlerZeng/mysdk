package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��15��
 * @version 1.0
 * @description
 * ������ģʽ�����ݽṹ�������ڽṹ�ϵĲ�������ϣ�
 * ʹ�ò������Ͽ�������ɵ��ݻ���������ģʽ���������ݽṹ����ȶ��㷨���ױ仯��ϵͳ��
 * ��Ϊ������ģʽʹ���㷨�������ӱ�����ס���ϵͳ���ݽṹ�������ڱ仯���������µ����ݶ������ӽ�����
 * ���ʺ�ʹ�÷�����ģʽ��������ģʽ���ŵ������Ӳ��������ף���Ϊ���Ӳ�����ζ�������µķ����ߡ�
 * ������ģʽ���й���Ϊ���е�һ�������߶����У���ı䲻Ӱ��ϵͳ���ݽṹ��
 * ��ȱ����������µ����ݽṹ�����ѡ����� From �ٿ�

����˵��������ģʽ����һ�ַ���������ݽṹ����Ϊ�ķ�����ͨ�����ַ��룬
�ɴﵽΪһ���������߶�̬����µĲ������������������޸ĵ�Ч����
 */
public class VisitorMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Subjects subject = new MySubjects();
		Visitor visitor = new MyVisitor();
		Visitor visitor2 = new MyVisitor2();
		visitor.visit(subject);
		subject.accept(visitor2);
		subject.accept(visitor);
	}

}

interface Visitor{
	void visit(Subjects subject);
}

interface Subjects{
	void accept(Visitor visitor);
	String getSubject();
}

class MyVisitor implements Visitor{

	@Override
	public void visit(Subjects subject) {
		// TODO Auto-generated method stub
		System.out.println("this is myvisitor! "+"/n"+subject.getSubject());
	}
	
}

class MyVisitor2 implements Visitor{

	@Override
	public void visit(Subjects subject) {
		// TODO Auto-generated method stub
		System.out.println("this is myvisitor2! "+"/n"+subject.getSubject());
	}
	
}

class MySubjects implements Subjects{

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}

	@Override
	public String getSubject() {
		// TODO Auto-generated method stub
		return "hello visitor!";
	}
	
}

//��ģʽ���ó��������������Ϊһ�����е��������¹��ܣ����ò����Ǽ������飺
//1���¹��ܻ᲻�������й��ܳ��ּ��������⣿2���Ժ�᲻������Ҫ��ӣ�3������಻�����޸Ĵ�����ô�죿
//�����Щ���⣬��õĽ����������ʹ�÷�����ģʽ��������ģʽ���������ݽṹ����ȶ���ϵͳ�������ݽṹ���㷨���
