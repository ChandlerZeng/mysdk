package com.example.javatest.javamode.behaviortype;
/**
 * @author Zengcq
 * @date 2016��12��15��
 * @version 1.0
 * @description
 * ��ҪĿ���Ǳ���һ�������ĳ��״̬���Ա����ʵ���ʱ��ָ����󣬸��˾��ýб���ģʽ������Щ��
 * ͨ�׵Ľ��£�������ԭʼ��A��A���и������ԣ�A���Ծ�����Ҫ���ݵ����ԣ�
 * ����¼��B�������洢A��һЩ�ڲ�״̬��
 * ��C�أ�����һ�������洢����¼�ģ���ֻ�ܴ洢�������޸ĵȲ�����
 */
public class MementoMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Original original = new Original("before","before");
		String restore = original.getValue();
		Storage storage = new Storage(original.createMemento());
		

        System.out.println("��ʼ��״̬Ϊ��" + original.getValue());  
        
		// �޸�ԭʼ���״̬  
        original.setValue("after");
        System.out.println("�޸ĺ��״̬Ϊ��" + original.getValue());  
        
        // �ظ�ԭʼ���״̬  
        original.restoreMemento(storage.getMemento());
        System.out.println("�ָ����״̬Ϊ��" + original.getValue());  
        System.out.println("�ָ����״̬Ϊ��" + restore);  
        
        Memento memento = new Memento("before", "before");
        Storage storage2 = new Storage(memento);
        System.out.println("��ʼ��״̬Ϊ��" + memento.getValue()+memento.getKey());
        memento.setKey("after");
        memento.setValue("after");
        System.out.println("�޸ĺ��״̬Ϊ��" + memento.getValue()+memento.getKey());
        System.out.println("�ָ����״̬Ϊ��" + storage2.getMemento().getKey()+storage2.getMemento().getValue());  
        
	}

}

class Original {
    
    private String value;  
    private String key;  
      
    public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    }  
  
    public Original(String value,String key) {  
        this.value = value;  
        this.key = key;
    }  
  
    public Memento createMemento(){  
        return new Memento(value,key);  
    }  
      
    public void restoreMemento(Memento memento){  
        this.value = memento.getValue();  
    }  
}

class Memento{
	private String value;
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Memento(String value,String key){
		this.value = value;
		this.key = key;
	}
	
	public String getValue() {  
        return value;  
    }  
  
    public void setValue(String value) {  
        this.value = value;  
    } 
}

class Storage{
	private Memento memento;

	public Storage(Memento memento){
		this.memento = memento;
	}
	
	public Memento getMemento() {
		return memento;
	}

	public void setMemento(Memento memento) {
		this.memento = memento;
	}
	
	
}