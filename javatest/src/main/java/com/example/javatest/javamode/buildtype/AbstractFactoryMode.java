package com.example.javatest.javamode.buildtype;

/**
 * @author Zengcq
 * @date 2016��12��13��
 * @version 1.0
 * @description ���󹤳�ģʽ
 * ��ʵ���ģʽ�ĺô����ǣ����������������һ�����ܣ�����ʱ��Ϣ����ֻ����һ��ʵ���࣬
 * ʵ��Sender�ӿڣ�ͬʱ��һ�������࣬ʵ��Provider�ӿڣ���OK�ˣ�
 * ����ȥ�Ķ��ֳɵĴ��롣����������չ�ԽϺã�
 */
public class AbstractFactoryMode {
	public static void main(String[] args) {
		Provider mailFactory = new MailFactory();
		mailFactory.produce().send();
		
		Provider provider = new ImageFactory();
		Sender sender = provider.produce();
		sender.send();
		
		HumanFactory humanFactory = new HumanFactory();
		Human human1 = humanFactory.getHuman("chinese");
		Human human2 = humanFactory.getHuman("america");
		human1.sayHelloWorld("�����Ȱ�");
		human2.sayHelloWorld("Chandler");
	}
}

interface Sender{
	void send();
}

class SmsSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is smssender");
	}
	
}

class MailSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is mailsender");
	}
	
}

class VideoSender implements Sender{

	@Override
	public void send() {
		// TODO Auto-generated method stub
		System.out.println("this is videosender");
	}
	
}

class ImageSender implements Sender{

    @Override
    public void send() {
        // TODO Auto-generated method stub
        System.out.println("this is imagesender");
    }
    
}

interface Provider{
	Sender produce();
}

class SmsFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new SmsSender();
	}
	
}

class MailFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new MailSender();
	}
	
}

class VideoFactory implements Provider{

	@Override
	public Sender produce() {
		// TODO Auto-generated method stub
		return new VideoSender();
	}
	
}

class ImageFactory implements Provider{

    @Override
    public Sender produce() {
        // TODO Auto-generated method stub
        return new ImageSender();
    }
    
}

interface Human{
    void sayHelloWorld(String name);
}

class Chinese implements Human{

    @Override
    public void sayHelloWorld(String name) {
        // TODO Auto-generated method stub
        String helloWorld = "��ã�" + name;
        System.out.println(helloWorld); 
    }
    
}

class America implements Human{

    @Override
    public void sayHelloWorld(String name) {
        // TODO Auto-generated method stub
        String helloWorld = "Hello��" + name;
        System.out.println(helloWorld);
    }
    
}

class HumanFactory {
     public Human getHuman(String type){
         if(type.equals("chinese")){
             return new Chinese();
         }else {
             return new America();
         }
     }
}
