package com.example.javatest.javaprinciple;

/**
 * @author Zengcq
 * @date 2016��12��7��
 * @version 1.0
 * @description ���ģʽ6��ԭ�򣺽ӿڸ���ԭ�� �ӿڸ���ԭ��Ҫ�������һ��ģ��Ӧ��ֻ��������Ҫ�Ľӿڣ��Ա�֤�ӿڵ�С���ࡣ
 *              ������Ҫ��֤�ӿ�Ӧ�þ���С������ƽӿڵ�ʱ��Ӧ���ýӿھ���ϸ����
 *              ��Ҫ����̫ӷ�׵Ľӿڣ�����ӿ����кܶ಻��ɵ��߼��ķ����������� ���ȿ����ӿڸ���ԭ��Ķ��壬�����ֶ��� 
 *              ��һ�֣�Clients should not be forced to depend upon interfaces that they don't
 *              use.(�ͻ��˲�Ӧ��ǿ������������Ҫ�Ľӿ�) �ڶ��֣�The dependency of one class to
 *              another one should depend on the smallest possible
 *              interface.������������ϵӦ�ý�������С�Ľӿ��ϣ�
 *              ������Ľӿڣ�ȴ������ָ����ͨ��interface�ؼ��ֶ���Ľӿڣ��ӿڷ�Ϊ2�֣� 
 *              1������ӿڣ�Object Interface�� Java��������һ���࣬ͨ��new�ؼ��ֲ�����һ��ʵ����
 *              ���Ƕ�һ�����͵��������������Ҳ��һ�ֽӿڡ����磺 Phone phone = new
 *              Phone();�������Person����ʵ��phone��һ���ӿ� 2����ӿڣ�Class Interface��
 *              ���ֽӿھ���ͨ��interface�ؼ��ֶ���Ľӿ���
 * 
 *              �ٶ�֪���ȽϺ���Ľ��ͣ�
 *              �ӿڸ���ԭ������ͻ��˲�Ӧ�ñ�ǿ��ʵ��һЩ���ǲ���ʹ�õĽӿڣ�Ӧ�ð��ֽӿ��еķ������飬
 *              Ȼ���ö���ӿڴ�����
 *              ��ÿ���ӿڷ�����һ����ģ�顣 �ӿڸ���ԭ�� ��Ӧ��ǿ�ȿͻ������������ǲ���ʹ�õĽӿڡ� ʵ��
 *              ������һ��Υ���˽ӿڸ���ԭ������ӡ�
 *              ����ʹ��Manager�����һ�������˵Ĺ����ߡ����������͵Ĺ��ˣ���ͨ�ĺ͸�Ч�ģ������ֹ��˶���Ҫ���緹
 *              ����������һ�������ˣ�����ͬ��Ϊ��˾����
 *              ���������ǲ���Ҫ���緹��һ����Robot����Ҫʵ��IWoker�ӿڣ���Ϊ����Ҫ��������һ����
 *              �������ֲ���Ҫʵ��IWorker�ӿڣ���Ϊ���ǲ���Ҫ�Է��� �����������IWorker�ͱ���Ϊ��һ������Ⱦ�˵Ľӿڡ�
 *              ������Ǳ������ڵ����
 *              ����ôRobot�ཫ����ʵ��eat()���������ǿ���дһ��������ʲôҲ����������˵��ֻ��һ���ӵ�ʱ����緹��
 *              ���������Գ�����ɲ���Ԥ�ϵĽ������������߿����ı�������ʾ�����ߵ���Ͷ���ʵ�ʵ���������
 */
public class InterfaceSegregationPrinciple {

	public static void main(String[] args) {
		 IPrettyGirl jiajia = new PrettyGirl("��˪");  
	        AMan man = new Man(jiajia);  
	        man.findGirl();  
	        IGreatBody canlaoshi = new BeautifulGirl("����ʦ");
	        AMan man2 = new Man(canlaoshi); 
	        man2.findGirl2();
	}

}

// ��ʹ�ýӿڸ���ԭ���ʱ����Ҫ��һЩ�淶��
// 1.�ӿھ���С
// �ӿھ���С��Ҫ��Ϊ�˱�֤һ���ӿ�ֻ����һ����ģ�����ҵ���߼�
// 2.�ӿڸ��ھ�
// �ӿڸ��ھ��Ƕ��ڸ߶����������⾡���ܸ��롣��һ���ӿ��ڲ��������ķ����໥֮�䶼��ĳһ����ģ����أ����������ģ�����ġ�
// 3.�ӿ���������޶ȵ�
// ���������ȫ��ѭ�ӿڸ���ԭ��Ļ��������һ�����⡣���ӿڵ�������Ȼ�Խ��ԽС������������˽ӿ�����������
//ϵͳ���Ӷ�һ���������ˣ����ⲻ����ʵ��Ŀ����Ҫ�ģ�������ʹ�����ԭ���ʱ��Ҫ���ض�����Ŀ�����ݾ�����߳����жϣ�
//����û��һ���̶��ı�׼��

//������Ů�ӿ�  
interface IPrettyGirl {  
    //�����  
    public void greatLooks();  
    //�����  
    public void greatFigure();  
    //���ʼ�  
    public void greatTemperament();  
}

//ʵ����Ů��
class PrettyGirl implements IPrettyGirl {  
    private String name;  
    //���캯������Ů����  
    public PrettyGirl(String name)  
    {  
        this.name = name;  
    }  
    //�����  
    @Override 
    public void greatFigure() {  
        System.out.println(name+":��ķǳ���");  
    }  
    //�ó���  
    @Override 
    public void greatLooks() {  
        System.out.println(name+":����ǳ���");  
    }  
    //������  
    @Override 
    public void greatTemperament() {  
        System.out.println(name+":���ʷǳ���");  
    }  
   
}

abstract class AMan {  
    protected IPrettyGirl prettyGirl;  
    protected IGreatBody iGreatBody;  
    public AMan(IPrettyGirl prettyGirl)  
    {  
        this.prettyGirl = prettyGirl;  
    }  
    
    public AMan(IGreatBody iGreatBody)  
    {  
        this.iGreatBody = iGreatBody;  
    }  
       
    //˧�翪ʼ����Ů��  
    public abstract void findGirl();  
    public abstract void findGirl2();  
}

class Man extends AMan {  
	   
    public Man(IPrettyGirl prettyGirl) {  
        super(prettyGirl);  
    }  
    public Man(IGreatBody iGreatBody) {  
        super(iGreatBody);  
    }  
   
    @Override 
    public void findGirl() {  
        System.out.println("��Ů�����----------------------");  
        super.prettyGirl.greatLooks();  
        super.prettyGirl.greatFigure();  
        super.prettyGirl.greatTemperament();  
           
    }
	@Override
	public void findGirl2() {
		// TODO Auto-generated method stub
		super.iGreatBody.greatFigure();
		super.iGreatBody.greatLooks();
	}  
   
}


//�ӿڸ���
interface IGreatBody {  
    //�ó���  
    public void greatLooks();  
    //���  
    public void greatFigure();  
}

interface IGreatTemperament {  
    //���ʺ�  
    public void greatTemperament();  
}

class BeautifulGirl implements IGreatBody{

	String nameString;
	
	public BeautifulGirl(String name){
		this.nameString = name;
	}
	@Override
	public void greatLooks() {
		// TODO Auto-generated method stub
		 System.out.println(nameString+":����ǳ���");  
	}

	@Override
	public void greatFigure() {
		// TODO Auto-generated method stub
		 System.out.println(nameString+":��ķǳ���");  
	}
	
}



