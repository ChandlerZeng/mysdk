package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ����ģʽ�ܺ���⣬�ٸ����ӣ�˾��Ա������ʿ��ȥ�ɼ����飬����������ĽǶ������ǣ�˾��Ա�������ǣ��������
 * ��������ݣ�������ʿ�������ʿ��ȥִ�С�������̺��ڣ������໥���
 * �κ�һ��������ȥ���������ˣ�ֻ��Ҫ�����Լ����¶����У�˾��ԱҪ���ǽ��������ȥ��ע����ʿ������ôʵ�ֵġ�
 */
public class CommandMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Receiver receiver = new Receiver();
		Command command = new MyCommand(receiver);
		command.exe();
		Invoker invoker = new Invoker(command);
		invoker.action();
	}

}

interface Command {
    public void exe();  
}

class MyCommand implements Command {  
	  
    private Receiver receiver;  
      
    MyCommand(Receiver receiver) {  
        this.receiver = receiver;  
    }  
  
    @Override  
    public void exe() {  
        receiver.action();  
    }  
}

class Receiver {  
    public void action(){  
        System.out.println("command received!");  
    }  
}

class Invoker {  
    
    private Command command;  
      
    public Invoker(Command command) {  
        this.command = command;  
    }  
  
    public void action(){  
        command.exe();  
    }  
}  

/*����ܹ���⣬����ģʽ��Ŀ�ľ��Ǵﵽ����ķ����ߺ�ִ����֮����ʵ�������ִ�зֿ���
 * ��ϤStruts��ͬѧӦ��֪����Struts��ʵ����һ�ֽ�����ͳ��ַ���ļ��������б�Ȼ�漰����ģʽ��˼�룡
 * ��ʵÿ�����ģʽ���Ǻ���Ҫ��һ��˼�룬����ȥ���죬
��ʵ����Ϊ������ѧ���Ķ����ж����漰��������ʱ���ǲ���֪������ʵ��Java��������֮�д����������֣�
��AWT��JDBC�������ࡢIO�ܵ�������Web��ܣ��������ģʽ�޴����ڡ���Ϊ����ƪ�����ޣ����ѽ�ÿһ�����ģʽ�����ĺ���ϸ��
�����һᾡ�����ܣ�
���������޵Ŀռ��ƪ���ڣ�����˼д����ˣ������ô�����ס�
 */
