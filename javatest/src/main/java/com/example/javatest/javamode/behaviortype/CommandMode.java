package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description
 * 命令模式很好理解，举个例子，司令员下令让士兵去干件事情，从整个事情的角度来考虑，司令员的作用是，发出口令，
 * 口令经过传递，传到了士兵耳朵里，士兵去执行。这个过程好在，三者相互解耦，
 * 任何一方都不用去依赖其他人，只需要做好自己的事儿就行，司令员要的是结果，不会去关注到底士兵是怎么实现的。
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

/*这个很哈理解，命令模式的目的就是达到命令的发出者和执行者之间解耦，实现请求和执行分开，
 * 熟悉Struts的同学应该知道，Struts其实就是一种将请求和呈现分离的技术，其中必然涉及命令模式的思想！
 * 其实每个设计模式都是很重要的一种思想，看上去很熟，
其实是因为我们在学到的东西中都有涉及，尽管有时我们并不知道，其实在Java本身的设计之中处处都有体现，
像AWT、JDBC、集合类、IO管道或者是Web框架，里面设计模式无处不在。因为我们篇幅有限，很难讲每一个设计模式都讲的很详细，
不过我会尽我所能，
尽量在有限的空间和篇幅内，把意思写清楚了，更好让大家明白。
 */
