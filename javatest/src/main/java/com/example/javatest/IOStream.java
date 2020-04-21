package com.example.javatest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;

/**
 * @author Zengcq
 * @date 2017��1��21��
 * @version 1.0
 * @description
 */
public class IOStream {
    public static void main(String[] args){
//        try {
//            System.out.print("�����ַ�: ");
//            System.out.println("�����ַ�ʮ���Ʊ�ʾ: " +
//                                    System.in.read());
            fileIOStream();
            bufferedIOStream("D:/��ý��/���ý��/20170120/1.jpeg","D:/testjava/abc4.txt");
            bufferedIOStream("D:/��ý��/���ý��/20170120/1.jpeg","D:/testjava/1.jpeg");
            bufferedIOStream("C:\\Users\\Administrator\\Desktop\\contacts2.db","D:/testjava/contacts2.db");
            bufferedIOStream("C:\\Users\\Administrator\\Desktop\\contacts2.db","D:/testjava/contacts2.txt");
            InOutStream("hahhahah��������2B��");
//            bufferReaderWriterTest();
            bufferedReaderWriterStream("���ү��45545f54454547777��ɵ��");
            arrayCopyTest();
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }                       
    }
    
    private static void InOutStream(String content){
       try {
        OutputStream outputStream = new FileOutputStream("D:/testjava/abc3.txt");
        outputStream.write(content.getBytes());
        System.out.println("�����ļ�content��" +content.length() + "�ֽ�");
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }
    
    private static void fileIOStream(){
        try {
            byte[] buffer = new byte[1024];
            // ��Դ�ļ�
            FileInputStream fileInputStream =
                new FileInputStream(new File("D:/testjava/abc.txt"));
            // Ŀ���ļ�
            FileOutputStream fileOutputStream =
                new FileOutputStream(new File("D:/testjava/abc1.txt"));
            // available()��ȡ��δ��ȡ�����ݳ���
            System.out.println("�����ļ���" +
                    fileInputStream.available() + "�ֽ�");
          
            while(true) {
                if(fileInputStream.available() < 1024) {
                    // ʣ������ݱ�1024�ֽ���
                    // һλһλ������д��Ŀ���ļ�
                    System.out.println("fileInputStream.available1()"+fileInputStream.available());
                    int remain = -1;
                    while((remain = fileInputStream.read())
                                           != -1) {
                        fileOutputStream.write(remain);
                    }
                    System.out.println("fileInputStream.available2()"+fileInputStream.available());
                    break;
                }
                else {
                    // ����Դ�ļ���ȡ������������
                    fileInputStream.read(buffer);
                    // ����������д��Ŀ���ļ�
                    fileOutputStream.write(buffer);
                    System.out.println("fileInputStream.available3()"+fileInputStream.available());
                }
            }
            // �ر���
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("�������");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(
                      "using: java FileStreamDemo src des");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    
    }
    
    private static void bufferedIOStream(String copyFrom, String copyTo){
        try {
            byte[] data = new byte[1024];
            File srcFile = new File(copyFrom);
            File desFile = new File(copyTo);
            BufferedInputStream bufferedInputStream =
                new BufferedInputStream(
                         new FileInputStream(srcFile));
            BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(
                         new FileOutputStream(desFile));
            System.out.println("�����ļ���" +
                             srcFile.length() + "�ֽ�");
            while(bufferedInputStream.read(data) != -1) {
                bufferedOutputStream.write(data);
                System.out.println("bufferedInputStream.available()"+bufferedInputStream.available());
            }
          
            // ���������е�����ȫ��д��
            bufferedOutputStream.flush();
            // �ر���
            bufferedInputStream.close();
            bufferedOutputStream.close();
            System.out.println("�������");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "using: java UseFileStream src des");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void bufferedReaderWriterStream(String content){
        char[] buffer = new char[1024];        
        BufferedReader reader = null;
        BufferedWriter writer = null;
        int len = -1;
        try {
            reader = new BufferedReader(new StringReader(content));
            writer = new BufferedWriter(new FileWriter("D:/testjava/abc6.txt",true));
//            String r = reader.readLine();
//            writer.write(r);
            while((len = reader.read(buffer,0,10))!=-1){
                writer.write(buffer,0,len);
                writer.newLine();
            }
            reader.close();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static void bufferReaderWriterTest(){
        try  
        {  
            //����System.in������  
            //System.in��λ��������ͨ��InputStreamReader����ת��Ϊ�ַ���  
            System.out.println("�������ַ����д洢(���롰quit���˳��洢)��"); 
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));  
            //����FileWriter  
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("D:/testjava/abc5.txt"));  
  
            String input = null;  
            int count = 0;
  
            //ÿ��һ�н���һ��д�붯��  
            while(!(input = bufReader.readLine()).equals("quit"))  
            {  
                bufWriter.write(input);  
                //newLine()����д�������ϵͳ�����Ļ����ַ�����ִ�л�����ʱ��OS��������������ֻ����ַ�  
                bufWriter.newLine(); 
                count += input.length();
            }  
            bufReader.close();  
            bufWriter.close();  
            System.out.println("�Ѵ洢��"+count+"�ֽ�"); 
        }  
        catch(ArrayIndexOutOfBoundsException e)  
        {  
            System.out.println("û��ָ���ļ�");  
        }  
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }  
    }
    
    private static void arrayCopyTest(){
        byte[] data1="hello world".getBytes();
        byte[] data2 = "2000".getBytes();
        
        byte[] data3 = new byte[data1.length+data2.length];
        
        System.arraycopy(data1,0,data3,0,data1.length);
        System.out.println(new String(data3));
        //1.Ҫ�������Ƶ�ԭʼ����
        //2.ԭʼ���ݵĶ�ȡλ��(��ԭʼ�����ĸ�λ�ÿ�ʼ����)
        //3.���Ҫ������ԭʼ���ݵ�Ŀ�ĵ�
        //4.��ʼ��ŵ�λ��()
        //5.Ҫ��ȡ��ԭʼ���ݳ���(�����೤)
        System.arraycopy(data2,0,data3,data1.length,data2.length);
        System.out.println(new String(data3));
        
        System.out.println(data2.length);
        byte[] head = new byte[data2.length];
        //����data3�ĺ�4λ��head��
        System.arraycopy(data3,data3.length-4,head,0,head.length);
        System.out.println(new String(head));
    }
    
    public static void fileCopy(String infile , String outfile){
        try {
            byte[] data = new byte[1024];
            File srcFile = new File(infile);
            File desFile = new File(outfile);
            BufferedInputStream bufferedInputStream =
                new BufferedInputStream(
                         new FileInputStream(srcFile));
            BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(
                         new FileOutputStream(desFile));
            System.out.println("�����ļ���" +
                             srcFile.length() + "�ֽ�");
            while(bufferedInputStream.read(data) != -1) {
                bufferedOutputStream.write(data);
                System.out.println("bufferedInputStream.available()"+bufferedInputStream.available());
            }
          
            // ���������е�����ȫ��д��
            bufferedOutputStream.flush();
            // �ر���
            bufferedInputStream.close();
            bufferedOutputStream.close();
            System.out.println("�������");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(
                    "using: java UseFileStream src des");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
