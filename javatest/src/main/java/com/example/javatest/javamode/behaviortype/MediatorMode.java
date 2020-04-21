package com.example.javatest.javamode.behaviortype;

/**
 * @author Zengcq
 * @date 2016��12��15��
 * @version 1.0
 * @description
 * �н���ģʽҲ��������������֮�����ϵģ���Ϊ�������֮����������ϵ�Ļ���
 * �����ڹ��ܵ���չ��ά������ΪֻҪ�޸�һ���������������Ķ��󶼵ý����޸ġ�
 * ���ʹ���н���ģʽ��ֻ����ĺ�Mediator��Ĺ�ϵ����������֮��Ĺ�ϵ�����Ƚ���Mediator���У�
 * ���е���spring���������á�
 * 
 * User��ͳһ�ӿڣ�User1��User2�ֱ��ǲ�ͬ�Ķ��󣬶���֮���й���������������н���ģʽ��
 * ����Ҫ�����໥�������ã��������ߵ���϶Ⱥܸߣ�Ϊ�˽��������Mediator�࣬�ṩͳһ�ӿڣ�MyMediatorΪ��ʵ���࣬
 * �������User1��User2��ʵ��������ʵ�ֶ�User1��User2�Ŀ��ơ�
 * ����User1��User2���������໥����������ֻ��Ҫ���ֺú�Mediator֮��Ĺ�ϵ���У�ʣ�µ�ȫ��MyMediator����ά����
 */
public class MediatorMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mediator mediator = new MyMeditor();
		mediator.createMediator();
		mediator.workAll();
	}

}

interface Mediator {  
    public void createMediator();  
    public void workAll();  
}

class MyMeditor implements Mediator{

	private User user1;
	private User user2;
	
	@Override
	public void createMediator() {
		// TODO Auto-generated method stub
//		user1 = new User1(this);
//		user2 = new User2(this);
		user1 = new User1();
		user2 = new User2();
	}

	@Override
	public void workAll() {
		// TODO Auto-generated method stub
		user1.work();
		user2.work();
	}
}

abstract class User{
	private Mediator meditor;

	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(Mediator meditor) {
		// TODO Auto-generated constructor stub
		this.meditor = meditor;
	}
	public Mediator getMeditor() {
		return meditor;
	}

	public void setMeditor(Mediator meditor) {
		this.meditor = meditor;
	}
	
	abstract void work();
}

class User1 extends User{

	public User1(){
		
	}
	public User1(Mediator mediator) {
		// TODO Auto-generated constructor stub
		super(mediator);
	}
	@Override
	void work() {
		// TODO Auto-generated method stub
		System.out.println("user1 executed");
	}
	
}

class User2 extends User{

	public User2() {
		// TODO Auto-generated constructor stub
	}
	public User2(Mediator mediator) {
		// TODO Auto-generated constructor stub
		super(mediator);
	}
	@Override
	void work() {
		// TODO Auto-generated method stub
		System.out.println("user2 executed");
	}
	
}
