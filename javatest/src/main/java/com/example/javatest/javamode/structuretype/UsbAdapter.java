package com.example.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016��12��14��
 * @version 1.0
 * @description 
 * ������ģʽ��ĳ����Ľӿ�ת���ɿͻ�����������һ���ӿڱ�ʾ��Ŀ�����������ڽӿڲ�ƥ������ɵ���ļ��������⡣
 * ��Ҫ��Ϊ���ࣺ���������ģʽ�������������ģʽ���ӿڵ�������ģʽ��
 * ���������ģʽ:����˼����ǣ���һ��Source�࣬ӵ��һ�������������䣬Ŀ��ӿ�ʱTargetable��
 * ͨ��Adapter�࣬��Source�Ĺ�����չ��Targetable��
 */
public class UsbAdapter {
    public static void main(String[] args) {
        Usb mouse = new Mouse();
        Usb sdcard = new SdClassAdapter();
        mouse.useUsb();
        sdcard.useUsb();
        Usb sdinterface = new SdInterfaceAdapter(new Sdhandler());
        sdcard.useUsb();
    }
}

    interface Usb{
        void useUsb();
    }

    class Mouse implements Usb{

        @Override
        public void useUsb() {
            System.out.println("i am mouse.i can use usb directly!hahahaha");
        }
    }

    interface Sdcard{
        void useSd();
    }

    class Sdhandler implements Sdcard{

        @Override
        public void useSd() {
            System.out.println("i am sdcard.i can not use usb directly!i need to transform. sad");

        }
    }

    //��������
    class SdClassAdapter extends Sdhandler implements Usb {

        @Override
        public void useUsb() {
            super.useSd();
        }
    }

    //����������
    class SdInterfaceAdapter implements Usb{
        Sdcard sdcard;
        SdInterfaceAdapter(Sdcard sdcard){
            this.sdcard = sdcard;
        }

        @Override
        public void useUsb() {
            sdcard.useSd();
        }
    }

