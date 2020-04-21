package com.example.javatest.javamode.behaviortype;


/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ����˼�壬������ģʽ����˳����ʾۼ��еĶ���һ����˵�������зǳ�����������Լ�����Ƚ���Ϥ�Ļ���
 * ��Ȿģʽ��ʮ�����ɡ�
 * ��仰����������˼��һ����Ҫ�����Ķ��󣬼��ۼ����󣬶��ǵ������������ڶԾۼ�������б������ʡ�
 */
public class IteratorMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Collection collection = new MyCollection();
		Iterators iterators = collection.iterator();
		
		while(iterators.hasNext()){
			System.out.println(iterators.next());
		}
		while(iterators.hasPrevious()){
			System.out.println(iterators.previous());	
		}
		while(iterators.hasNext()){
			System.out.println(iterators.next());
		}
		while(iterators.hasPrevious()){
			System.out.println(iterators.previous());	
		}
		
		System.out.println(collection.size());
		System.out.println(collection.get(2));
		System.out.println(iterators.first());
		System.out.println(iterators.last());

	}

}

interface Collection {  
    
    public Iterators iterator();  
      
    /*ȡ�ü���Ԫ��*/  
    public Object get(int i);  
      
    /*ȡ�ü��ϴ�С*/  
    public int size();  
}

interface Iterators {  
    //ǰ��  
    public Object previous();  
      
    //����  
    public Object next();  
    public boolean hasNext();  
    public boolean hasPrevious();  
      
    //ȡ�õ�һ��Ԫ��  
    public Object first();  
    
  //ȡ�����һ��Ԫ��  
    public Object last();  
}

class MyCollection implements Collection {  
	  
    public String string[] = {"A","B","C","D","E"};  
    @Override  
    public Iterators iterator() {  
        return new MyIterator(this);  
    }  
  
    @Override  
    public Object get(int i) {  
        return string[i];  
    }  
  
    @Override  
    public int size() {  
        return string.length;  
    }  
}

class MyIterator implements Iterators {  
	  
    private Collection collection;  
    private int pos = -1;  
      
    public MyIterator(Collection collection){  
        this.collection = collection;  
    }  
      
    @Override  
    public Object previous() {  
        if(pos > 0){  
            pos--;  
        }  
        return collection.get(pos);  
    }  
  
    @Override  
    public Object next() {  
        if(pos<collection.size()-1){  
            pos++;  
        }  
        return collection.get(pos);  
    }  
  
    @Override  
    public boolean hasNext() {  
        if(pos<collection.size()-1){  
            return true;  
        }else{  
            return false;  
        }  
    }  
    
    @Override  
    public boolean hasPrevious() {  
        if(pos>0){  
            return true;  
        }else{  
            return false;  
        }  
    }
  
    @Override  
    public Object first() {  
        pos = 0;  
        return collection.get(pos);  
    }

	@Override
	public Object last() {
		// TODO Auto-generated method stub
		return collection.get(collection.size()-1);
	}  
  
}  

/*
 * �˴�����ò��ģ����һ��������Ĺ��̣��о��ǲ��Ǻ�ˬ����ʵJDK�и�����Ҳ������Щ�����Ķ�������һЩ���ģʽ��
 * �ټ�һЩ�Ż��ŵ�һ��ģ�ֻҪ���ǰ���Щ����ѧ���ˣ�
 * ���պ��ˣ�����Ҳ����д���Լ��ļ����࣬������ܣ�
 */

