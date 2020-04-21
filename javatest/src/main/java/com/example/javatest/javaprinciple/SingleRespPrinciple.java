package com.example.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016��12��7��
 * @version 1.0
 * @description ���ģʽ6��ԭ�򣺵�һְ��ԭ��
 *              ��ʱ�򣬿�����Ա��ƽӿڵ�ʱ�����Щ���⣬�����û������Ժ��û�����Ϊ������һ���ӿ���������
 *              ��������ҵ������ҵ���߼���������һ��
 *              �����������������ӿ�������ְ�𣬽ӿ�ְ����ȷ������SRP�Ķ����Υ���˽ӿڵĵ�һְ��ԭ���ˡ� 
 *              ��һְ��ԭ��Single
 *              Responsibility Principle�������SRP�� ���壺 There should never be more
 *              than one reason for a class to change. Ӧ�����ҽ���һ��ԭ��������ı����
 */
public class SingleRespPrinciple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItutuBO tutuBo = new TutuBO();
		ItutuBL tutuBl = new TutuBL();
		tutuBo.setShengao(1.7);
		tutuBo.setTizhong(60);
		tutuBo.toString();
		tutuBl.chiFan(true);
		tutuBl.shangWang(true);
	}

}

interface Itutu {  
    //���  
    void setShengao(double height);  
    double getShengao();  
    //����  
    void setTizhong(double weight);  
    double getTizhong();  
    //�Է�  
    boolean chiFan(boolean hungry);  
    //����  
    boolean shangWang(boolean silly);  
} 

/*��������Ӿʹ���������⣬��ߡ���������ҵ�������֮��Ӧ�ķ�����Ҫ�����û������ԡ�
 * ���Է�����������Ӧ��ҵ���߼�����Ҫ�����û�����Ϊ��������ͻ����һ�ֲ�֪������ӿڵ�������ʲô�ĸо���
 * ְ������������ά����ʱ��Ҳ����ɸ��ָ��������⡣
����취����һְ��ԭ�򣬽�����ӿڷֽ������ְ��ͬ�Ľӿڼ���
ItutuBO.java������tutu��ͿͿ�������Ǹ�������������*/

interface ItutuBO {  
    //���  
    void setShengao(double height);  
    double getShengao();  
    //����  
    void setTizhong(double weight);  
    double getTizhong();  
}

interface ItutuBL {  
    //�Է�  
    boolean chiFan(boolean hungry);  
    //����  
    boolean shangWang(boolean silly);  
}

class TutuBO implements ItutuBO {  
    private double height;  
    private double weight;  
    @Override 
    public double getShengao() {         
        return height;  
    }  
   
    @Override 
    public double getTizhong() {  
        return weight;  
    }  
   
    @Override 
    public void setShengao(double height) {  
        this.height = height;  
    }  
   
    @Override 
    public void setTizhong(double weight) {  
        this.weight = weight;  
    }  
    
    public String toString(){
    	System.out.println("ͿͿ��ߣ�"+height+"�ף�"+"ͿͿ���أ�"+weight+"kg��");
    	return "ͿͿ��ߣ�"+height+"�ף�"+"ͿͿ���أ�"+weight+"kg��";
    }
   
}

class TutuBL implements ItutuBL {  
	   
    @Override 
    public boolean chiFan(boolean hungry) {  
        if(hungry)  
        {  
            System.out.println("ȥ�Ի��...");  
            return true;  
        }  
        return false;  
    }  
   
    @Override 
    public boolean shangWang(boolean silly) {  
        if(silly)  
        {  
            System.out.println("�����İ����ϻ���...");  
            return true;  
        }  
        return false;  
    }  
   
} 

//�����������ˣ�����Ҫ�޸��û����Ե�ʱ��ֻ��Ҫ��ItutuBO����ӿ����޸ģ�ֻ��Ӱ�쵽TutuBO����࣬����Ӱ�������ࡣ
//��ô��һְ��ԭ�����������أ�
//������ĸ����ԣ�ʵ��ʲô����ְ���������Ķ���
//��߿ɶ���
//��߿�ά����
//���ͱ������ķ��գ���ϵͳ��չ�Ժ�ά���Ժ��а���
//���ǡ�ʹ�õ�һְ��ԭ����һ�����⣬��ְ��û��һ����ȷ�Ļ��ֱ�׼��
//�����ְ�𻮷ֵ�̫ϸ�Ļ��ᵼ�½ӿں�ʵ�����������������������˸��Ӷȣ������˴���Ŀ�ά���ԡ�
//����ʹ�����ְ���ʱ��Ҫ����������������������ǽӿ�һ��Ҫ���õ�һְ��ԭ��
//ʵ���������Ͼ�����������һְ��ԭ�������һ��ԭ������һ����ı仯��

