package com.example.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ��ʵÿ��ģʽ���ƾͱ����˸�ģʽ�����ã�����ģʽ���Ƕ�һ���������������ԭ�������һЩ������
 * �����������ⷿ�ӵ�ʱ���ȥ���н飬Ϊʲô�أ���Ϊ��Ըõ������ݵ���Ϣ���յĲ���ȫ�棬
 * ϣ����һ������Ϥ����ȥ���������˴��Ĵ�����������˼�����������е�ʱ����˾��
 * ������Ҫ����ʦ����Ϊ��ʦ�ڷ��ɷ�����ר�������������ǽ��в�����������ǵ��뷨��
 */
public class ProxyMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sourceable2 sourceable2 = new Proxy();
		sourceable2.method();
	}

}

class Proxy implements Sourceable2 {  
	  
//    private Source source;
    public Proxy(){
        super();
//        this.source = new Source();
    }
    @Override
    public void method() {
        before();
//        source.method1();
        atfer();
    }
    private void atfer() {  
        System.out.println("after proxy!");  
    }  
    private void before() {  
        System.out.println("before proxy!");  
    }  
}  
