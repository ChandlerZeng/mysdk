package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��15��
 * @version 1.0
 * @description
 * ����˼����ǣ��������״̬�ı�ʱ��ͬʱ�ı�����Ϊ���ܺ���⣡����QQ��˵��
 * �м���״̬�����ߡ�����æµ�ȣ�ÿ��״̬��Ӧ��ͬ�Ĳ�����������ĺ���Ҳ�ܿ������״̬��
 * ���ԣ�״̬ģʽ�����㣺1������ͨ���ı�״̬����ò�ͬ����Ϊ��2����ĺ�����ͬʱ������ı仯��
 */
public class StateMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		State state = new State();
		state.setValue("state1");
		StatePerformance statePerformance = new StatePerformance(state);
		statePerformance.method();
		state.setValue("state2");
		statePerformance.method();
	}

}

class State {  
    
    private String value;  
      
    public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    }  
  
    public void method1(){  
        System.out.println("execute the first opt!");  
    }  
      
    public void method2(){  
        System.out.println("execute the second opt!");  
    }  
}

class StatePerformance{
	private State state;
	public StatePerformance(State state) {
		// TODO Auto-generated constructor stub
		this.state = state;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	
	public void method(){
		if(state.getValue().equals("state1")){
			state.method1();
		}else {
			state.method2();
		}
	}
}

//����������ԣ�״̬ģʽ���ճ��������õ�ͦ��ģ�����������վ��ʱ��
//������ʱϣ�����ݶ����ĳһ���ԣ��������ǵ�һЩ���ܣ�����˵�򵥵�Ȩ�޿��Ƶȡ�