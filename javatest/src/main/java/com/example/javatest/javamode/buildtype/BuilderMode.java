package com.example.javatest.javamode.buildtype;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zengcq
 * @date 2016��12��13��
 * @version 1.0
 * @description
 * ������ģʽ�ṩ���Ǵ����������ģʽ����������ģʽ���ǽ����ֲ�Ʒ�����������й���
 * �����������϶�����ν���϶������ָĳ������в�ͬ�����ԣ�
 * ��ʵ������ģʽ����ǰ����󹤳�ģʽ������Test��������õ��ġ����ǿ�һ�´��룺
 */
public class BuilderMode {
	
	private List<Sender> list = new ArrayList<Sender>();  
	public static void main(String[] args) {  
		BuilderMode builderMode = new BuilderMode();
		builderMode.produceMailSender(10);  
    }  
    public void produceMailSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new MailSender());  
        }  
    }  
      
    public void produceSmsSender(int count){  
        for(int i=0; i<count; i++){  
            list.add(new SmsSender());  
        }  
    }  
}
