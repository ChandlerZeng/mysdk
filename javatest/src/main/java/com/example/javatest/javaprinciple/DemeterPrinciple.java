package com.example.javatest.javaprinciple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengcq
 * @date 2016��11��23��
 * @version 1.0 
 * ���ģʽ6��ԭ�򣺵����ط���
 * �����ط���Law ofemeter�� ���壺һ������Ӧ�ö����������˽����� �����ط���ĺ��Ĺ���������������ϣ�ֻ����������Ժ���ĸ����Բſ�����ߡ�
 * ����һ��ı��������ڣ������ڵķ����ǲ�Ӧ�ø�������˽Ӵ��ģ���Ȼ�������̽�׵ġ�����ļ��������࣬����ķ��˾������ڲ�����Ϣ��
 * ����������������൱�ڵ����ط����ִ����
 */
public class DemeterPrinciple {
	public static void main(String[] args) {

		TeacherManage tm = new TeacherManage();

		tm.printAllTeacher();

		StudentManage sm = new StudentManage();

		sm.printAllStudent();
		
		Family family = new Family();  
        family.visitPrisoner(new Prisoners()); 

	}
}

class Teacher {

	private String id;

	public void setId(String id)

	{

		this.id = id;

	}

	public String getId()

	{

		return id;

	}

}

class Student {

	private String id;

	public void setId(String id)

	{

		this.id = id;

	}

	public String getId()

	{

		return id;

	}

}

class StudentManage {

	public List<Student> getAllStudent()

	{
		List<Student> list = new ArrayList<Student>();

		for (int i = 0; i < 100; i++)

		{
			Student student = new Student();

			student.setId("ѧ��ѧ����" + i);

			list.add(student);

		}

		return list;

	}

	public void printAllStudent()

	{

		List<Student> list1 = this.getAllStudent();

		for (Student s : list1)

		{

			System.out.println(s.getId());

		}

	}

}

class TeacherManage

{
	public List<Teacher> getAllTeacher()

	{

		List<Teacher> list = new ArrayList<Teacher>();

		for (int i = 0; i < 100; i++)

		{
			Teacher teacher = new Teacher();

			teacher.setId("��ʦ���" + i);

			list.add(teacher);

		}

		return list;

	}

	public void printAllTeacher()

	{

		List<Teacher> list2 = this.getAllTeacher();

		for (Teacher t : list2)

		{

			System.out.println(t.getId());

		}

	}

}

class Family {  
    //����̽������  
    public void visitPrisoner(Prisoners prisoners)  
    {  
        System.out.print("����˵��");  
        prisoners.helpEachOther();  
    }  
}

class Prisoners {  
    private Inmates inmates = new Inmates();  
    public Inmates helpEachOther()  
    {  
        System.out.println("���˺�����֮��Ӧ�û������...");  
        System.out.print("����˵��");  
        inmates.weAreFriend();  
        return inmates;  
    }  
       
}

class Inmates {  
    public void weAreFriend()  
    {  
        System.out.println("����������...");  
    }  
} 

//���ϻ�������һЩ����Ӧ�õ����ط����ע�����
//�� ����Ļ����ϣ�Ӧ�ô���������ϵ��ࣻ
//�� ����Ľṹ����ϣ�ÿһ���඼Ӧ���������ͳ�Ա�ķ���Ȩ�ޣ�
//�� ���������ϣ�ֻҪ�п��ܣ�һ����Ӧ����Ƴɲ����ࣻ
//�� �ڶ�������������ϣ�һ��������������������Ӧ��������ͣ�
//�� ����������ķ���Ȩ�ޣ�
//�� ����ʹ�����л����ܣ�
//�� ��Ҫ��¶���Ա����Ӧ���ṩ��Ӧ�ķ�����(����)��

