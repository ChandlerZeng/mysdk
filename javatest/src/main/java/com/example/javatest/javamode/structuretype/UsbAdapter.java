package com.example.javatest.javamode.structuretype;
/**
 * @author Zengcq
 * @date 2016年12月14日
 * @version 1.0
 * @description 
 * 适配器模式将某个类的接口转换成客户端期望的另一个接口表示，目的是消除由于接口不匹配所造成的类的兼容性问题。
 * 主要分为三类：类的适配器模式、对象的适配器模式、接口的适配器模式。
 * 类的适配器模式:核心思想就是：有一个Source类，拥有一个方法，待适配，目标接口时Targetable，
 * 通过Adapter类，将Source的功能扩展到Targetable里
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

    //类适配器
    class SdClassAdapter extends Sdhandler implements Usb {

        @Override
        public void useUsb() {
            super.useSd();
        }
    }

    //对象适配器
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

