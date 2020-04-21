package com.example;

import java.util.Arrays;

/**
 * Created by 1 on 2018/1/3.
 */

public class TestOne {
    public static void printInfo(){
        System.out.println("fuck boring now");
        System.out.println("new work is so so easy for me");
        System.out.println("may be a good chance, so grab the opportunity");
        System.out.println("just fight it");
        int[] arrays = new int[100];
        for(int i=0;i<100;i++){
            int random = (int)(Math.random()*100);
            System.out.print(random+" ");
            arrays[i] = random;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        Arrays.sort(arrays);
        for(int i=0;i<100;i++){
            System.out.print(arrays[i]+" ");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void printinfo2(){
        String s = "jj\\(.*\\);ll";
        System.out.println(s);
        String s1 = s.replaceAll("\\(.*\\);", "").replace("\\","");
        String s2 = s.replaceAll("\\\\(.*\\\\);", "");
        String s3 = s.replaceAll("\\(;", "");
        String s4 = s.replaceAll("\\(.;", "");
        String s5 = s.replaceAll("\\\\", "");
        String s6 = s.replace("\\", "");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s6);
    }

    public static void printinfo3(){
        String data = "https";
        System.out.println(String.valueOf(data.getBytes().length));
    }
}
