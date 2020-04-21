package com.example.javatest.javamode.behaviortype;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * �������ģʽ���ڵĽ��������ĸ�ģʽ�����������֮��Ĺ�ϵ�����漰���̳У�ѧ��ʱ��Ӧ�� �ǵù��ɣ�
 * �ǵñ����ʼ���Ǹ�ͼ���۲���ģʽ�ܺ���⣬�������ʼ����ĺ�RSS���ģ����������һЩ���ͻ�wikiʱ��
 * �����ῴ��RSSͼ�꣬�������˼�ǣ����㶩���˸����£���������и��£��ἰʱ֪ͨ�㡣��ʵ��
 * ��������һ�仰����һ������仯ʱ�����������ö���Ķ��󶼻��յ�֪ͨ���������ű仯������֮����һ��һ�Զ�Ĺ�ϵ��
 */
public class ObserverMode {
    public static void main(String[] args){
    	Subject subject = new MySubject();
    	subject.add(new Observer1());
    	subject.add(new Observer2());
    	subject.add(new Observer3());
    	subject.operations();
    }
}

/*�ҽ�������Щ������ã�MySubject��������ǵ�������Observer1��Observer2��������MySubject�Ķ���
��MySubject�仯ʱ��Observer1��Observer2��Ȼ�仯��AbstractSubject���ж�������Ҫ��صĶ����б�
���Զ�������޸ģ����ӻ�ɾ������ض����ҵ�MySubject�仯ʱ������֪ͨ���б��ڴ��ڵĶ���*/

interface Observer{
	void update();
}

class Observer3 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer3 has received update!");
	}
	
}

class Observer1 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer1 has received update!");
	}
	
}

class Observer2 implements Observer{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("Observer2 has received update!");
	}
	
}

interface Subject{
	//���ӹ۲���
	void add(Observer observer);
	//ɾ���۲���
	void delete(Observer observer);
	//֪ͨ�۲���
	void notifyObservers();
	//�������
	void operations();
}

abstract class AbstractSubject implements Subject{
	private Vector<Observer> vector = new Vector<Observer>();

	@Override
	public void add(Observer observer) {
		// TODO Auto-generated method stub
		vector.add(observer);
	}

	@Override
	public void delete(Observer observer) {
		// TODO Auto-generated method stub
		vector.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		Enumeration<Observer> enumo = vector.elements();
		while(enumo.hasMoreElements()){
			enumo.nextElement().update();
		}
	}
		
	
}

class MySubject extends AbstractSubject{

	@Override
	public void operations() {
		// TODO Auto-generated method stub
		System.out.println("update self!");  
        notifyObservers();  

	}
	
}