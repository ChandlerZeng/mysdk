package com.example.javatest.javamode.buildtype;
/**
 * @author Zengcq
 * @date 2016��12��13��
 * @version 1.0
 * @description ����ģʽ
 */
public class SingletonMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Singleton {  
	  
    /* ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ��� */  
    private static Singleton instance = null;  
  
    /* ˽�й��췽������ֹ��ʵ���� */  
    private Singleton() {  
    }  
  
    /* ��̬���̷���������ʵ�� */  
    public static Singleton getInstance() {  
        if (instance == null) {  
            instance = new Singleton();  
        }  
        return instance;  
    }  
  
    /* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */  
    public Object readResolve() {  
        return instance;  
    }  


//���������������Ҫ�󣬵��ǣ������������̰߳�ȫ�������࣬������ǰ���������̵߳Ļ����£�
//�϶��ͻ���������ˣ���ν�����������Ȼ��뵽��getInstance������synchronized�ؼ��֣����£�

static synchronized Singleton getInstance2() {  
    if (instance == null) {  
        instance = new Singleton();  
    }  
    return instance;  
}  


//���ǣ�synchronized�ؼ�����ס������������������÷����������ϻ������½�����Ϊÿ�ε���getInstance()��
//��Ҫ�Զ�����������ʵ�ϣ�ֻ���ڵ�һ�δ��������ʱ����Ҫ������֮��Ͳ���Ҫ�ˣ�
//���ԣ�����ط���Ҫ�Ľ������Ǹĳ����������
static Singleton getInstance3() {  
    if (instance == null) {  
        synchronized (Singleton.class) {
            if (instance == null) {  
                instance = new Singleton();  
            }  
        }  
    }  
    return instance;  
} 

/*
**�ƺ������֮ǰ�ᵽ�����⣬��synchronized�ؼ��ּ������ڲ���Ҳ����˵�����õ�ʱ���ǲ���Ҫ�����ģ�
*ֻ����instanceΪnull�������������ʱ�����Ҫ������������һ�������������ǣ������������
*�����п���������ģ���������������Javaָ���д�������͸�ֵ�����Ƿֿ����еģ�
*Ҳ����˵instance = new Singleton();����Ƿ�����ִ�еġ�����JVM������֤�������������Ⱥ�˳��
*Ҳ����˵�п���JVM��Ϊ�µ�Singletonʵ������ռ䣬Ȼ��ֱ�Ӹ�ֵ��instance��Ա��
*Ȼ����ȥ��ʼ�����Singletonʵ���������Ϳ��ܳ����ˣ�������A��B�����߳�Ϊ����

a>A��B�߳�ͬʱ�����˵�һ��if�ж�

b>A���Ƚ���synchronized�飬����instanceΪnull��������ִ��instance = new Singleton();

c>����JVM�ڲ����Ż����ƣ�JVM�Ȼ�����һЩ�����Singletonʵ���Ŀհ��ڴ棬
����ֵ��instance��Ա��ע���ʱJVMû�п�ʼ��ʼ�����ʵ������Ȼ��A�뿪��synchronized�顣

d>B����synchronized�飬����instance��ʱ����null��
����������뿪��synchronized�鲢��������ظ����ø÷����ĳ���

e>��ʱB�̴߳���ʹ��Singletonʵ����ȴ������û�б���ʼ�������Ǵ������ˡ�

���Գ������п��ܷ���������ʵ���������й����Ǻܸ��ӵģ���������ǾͿ��Կ�����
��������д���̻߳����µĳ�������Ѷȣ�����ս�ԡ����ǶԸó�������һ���Ż���
*/

static class SingletonFactory{           
    private static Singleton instance = new Singleton();           
}           
public static Singleton getInstance4(){           
    return SingletonFactory.instance;           
} 

/**
 * 
 * ʵ������ǣ�����ģʽʹ���ڲ�����ά��������ʵ�֣�JVM�ڲ��Ļ����ܹ���֤��һ���౻���ص�ʱ��
 * �����ļ��ع������̻߳���ġ����������ǵ�һ�ε���getInstance��ʱ��JVM�ܹ������Ǳ�֤instanceֻ������һ�Σ�
 * ���һᱣ֤�Ѹ�ֵ��instance���ڴ��ʼ����ϣ��������ǾͲ��õ�����������⡣
 * ͬʱ�÷���Ҳֻ���ڵ�һ�ε��õ�ʱ��ʹ�û�����ƣ�
 * �����ͽ���˵��������⡣����������ʱ�ܽ�һ�������ĵ���ģʽ��
 *
 */

/* ˽�й��췽������ֹ��ʵ���� */  
//private Singleton() {  
//}  

/* �˴�ʹ��һ���ڲ�����ά������ */  
private static class SingletonFactory2 {  
    private static Singleton instance = new Singleton();  
}  

/* ��ȡʵ�� */  
public static Singleton getInstance5() {  
    return SingletonFactory.instance;  
}  

/* ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ�� */  
public Object readResolve2() {  
    return getInstance();  
} 

/**
 * ��ʵ˵��������Ҳ��һ��������ڹ��캯�����׳��쳣��ʵ������Զ�ò���������Ҳ���������˵��
 * ʮ�������Ķ�����û�еģ�����ֻ�ܸ���ʵ�������ѡ�����ʺ��Լ�Ӧ�ó�����ʵ�ַ�����Ҳ��������ʵ�֣�
 * ��Ϊ����ֻ��Ҫ�ڴ������ʱ�����ͬ��������ֻҪ��������getInstance()�ֿ���
 * ����Ϊ������synchronized�ؼ��֣�Ҳ�ǿ��Եģ�
 */
/*public class SingletonTest {  
	  
    private static SingletonTest instance = null;  
  
    private SingletonTest() {  
    }  
  
    private static synchronized void syncInit() {  
        if (instance == null) {  
            instance = new SingletonTest();  
        }  
    }  
  
    public static SingletonTest getInstance() {  
        if (instance == null) {  
            syncInit();  
        }  
        return instance;  
    }*/  
 
}