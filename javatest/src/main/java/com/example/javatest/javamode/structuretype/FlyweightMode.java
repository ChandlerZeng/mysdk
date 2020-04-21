package com.example.javatest.javamode.structuretype;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description
 * ��Ԫģʽ����ҪĿ����ʵ�ֶ���Ĺ���������أ���ϵͳ�ж�����ʱ����Լ����ڴ�Ŀ�����
 * ͨ���빤��ģʽһ��ʹ�á�
 * FlyWeightFactory���𴴽��͹�����Ԫ��Ԫ����һ���ͻ�������ʱ��������Ҫ��鵱ǰ��������Ƿ��з��������Ķ���
 * ����У��ͷ����Ѿ����ڵĶ������û�У��򴴽�һ���¶���FlyWeight�ǳ��ࡣһ�ᵽ����أ�
 * ���Ǻ��������뵽Java�����JDBC���ӳأ�����ÿ�����ӵ��ص㣬���ǲ����ܽ�����������������һЩ������
 * ������һЩ���е����ԣ��������ݿ����ӳ���˵��url��driverClassName��username��password��dbname��
 * ��Щ���Զ���ÿ��������˵����һ���ģ����Ծ��ʺ�����Ԫģʽ��������һ�������࣬����������������Ϊ�ڲ����ݣ�
 * ��������Ϊ�ⲿ���ݣ��ڷ�������ʱ�����������������������ͽ�ʡ�˿ռ䣬������ʵ����������
 */
public class FlyweightMode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class ConnectionPool {  
    
    private Vector<Connection> pool;  
      
    /*��������*/  
    private String url = "jdbc:mysql://localhost:3306/test";  
    private String username = "root";  
    private String password = "root";  
    private String driverClassName = "com.mysql.jdbc.Driver";  
  
    private int poolSize = 100;  
    private static ConnectionPool instance = null;  
    Connection conn = null;  
  
    /*���췽������һЩ��ʼ������*/  
    private ConnectionPool() {  
        pool = new Vector<Connection>(poolSize);  
  
        for (int i = 0; i < poolSize; i++) {  
            try {  
                Class.forName(driverClassName);  
                conn = DriverManager.getConnection(url, username, password);  
                pool.add(conn);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /* �������ӵ����ӳ� */  
    public synchronized void release() {  
        pool.add(conn);  
    }  
  
    /* �������ӳ��е�һ�����ݿ����� */  
    public synchronized Connection getConnection() {  
        if (pool.size() > 0) {  
            Connection conn = pool.get(0);  
            pool.remove(conn);  
            return conn;  
        } else {  
            return null;  
        }  
    }  
}  

//ͨ�����ӳصĹ���ʵ�������ݿ����ӵĹ�������Ҫÿһ�ζ����´������ӣ�
//��ʡ�����ݿ����´����Ŀ�����������ϵͳ�����ܣ�
