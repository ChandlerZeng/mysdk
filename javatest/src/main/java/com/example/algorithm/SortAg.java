package com.example.algorithm;

import java.time.Clock;
import java.util.Arrays;
import java.util.Random;

public class SortAg {
    // 冒泡排序
    public static void bubbleSort(int array[]) {
        int t;
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - 1 - i; j++)
                if (array[j] > array[j + 1]) {
                    t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;
                }
    }

    // 选择排序
    public static void selectSort(int array[]) {
        int t;
        for (int i = 0; i < array.length - 1; i++){
            int min=i;
            for (int j = i + 1; j < array.length; j++){
                if (array[min] > array[j])
                    min = j;
            }
            if(min!=i){ //找到了比array[i]小的则与array[i]交换位置
                t = array[i];
                array[i] = array[min];
                array[min] = t;
            }
        }
    }

    //插入排序
    public static void insertSort(int[] arr) {
        if (arr.length >= 2) {
            for (int i = 1; i < arr.length; i++) {
                //挖出一个要用来插入的值,同时位置上留下一个可以存新的值的坑
                int x = arr[i];
                int j = i - 1;
                //在前面有一个或连续多个值比x大的时候,一直循环往前面找,将x插入到这串值前面
                while (j >= 0 && arr[j] > x) {
                    //当arr[j]比x大的时候,将j向后移一位,正好填到坑中
                    arr[j + 1] = arr[j];
                    j--;
                }
                //将x插入到最前面
                arr[j + 1] = x;
            }
        }
    }

    //希尔排序希尔排序是基于插入排序的以下两点性质而提出改进方法的：
    //插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率
    //但插入排序一般来说是低效的， 因为插入排序每次只能将数据移动一位
    public static void shellSort(int[] array) {
        int number = array.length / 2;
        int i;
        int j;
        int temp;
        while (number >= 1) {
            for (i = number; i < array.length; i++) {
                temp = array[i];
                j = i - number;
                while (j >= 0 && array[j] > temp) { //需要注意的是，这里array[j] > temp将会使数组从小到到排序。
                    array[j + number] = array[j];
                    j = j - number;
                }
                array[j + number] = temp;
            }
            number = number / 2;
        }
    }


    // 分治法快速排序
    public static void quickSort(int array[], int low, int high) {// 传入low=0，high=array.length-1;
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            pivot = array[p_pos];
            for (i = low + 1; i <= high; i++){
                if (array[i] < pivot) {
                    p_pos++;
                    t = array[p_pos];
                    array[p_pos] = array[i];
                    array[i] = t;
                }
            }
            t = array[low];
            array[low] = array[p_pos];
            array[p_pos] = t;
            // 分而治之
            quickSort(array, low, p_pos - 1);// 排序左半部分
            quickSort(array, p_pos + 1, high);// 排序右半部分
        }
    }

    // 分治法快速排序
    public static void quickSort2(int array[], int low, int high) {// 传入low=0，high=array.length-1;
        if(low>=high)return;
        int i,j,temp,x;
        i = low;
        j = high;
        temp = array[low];
        while(i<j){
            while(temp<=array[j] && i<j){
                j--;
            }
            while(temp>=array[i] && i<j){
                i++;
            }
            if(i<j){
                x = array[i];
                array[i] = array[j];
                array[j] = x;
            }
        }
        array[low] = array[i];
        array[i] = temp;
        quickSort2(array,low,i-1);
        quickSort2(array,i+1,high);
    }

    //归并排序
    public static void mergeSort(int[] a,int s,int e){
        int m = (s + e) / 2;
        if (s < e){
            mergeSort(a,s,m);
            mergeSort(a,m+1,e);
            //归并
            merge(a,s,m,e);
        }
    }

    private static void merge(int[] a, int s, int m, int e) {
        //初始化一个从起始s到终止e的一个数组
        int[] temp = new int[(e - s) + 1];
        //左起始指针
        int l = s;
        //右起始指针
        int r = m+1;
        int i = 0;
        //将s-e这段数据在逻辑上一分为二,l-m为一个左边的数组,r-e为一个右边的数组,两边都是有序的
        //从两边的第一个指针开始遍历,将其中小的那个值放在temp数组中
        while (l <= m && r <= e){
            if (a[l] < a[r]){
                temp[i++] = a[l++];
            }else{
                temp[i++] = a[r++];
            }
        }

        //将两个数组剩余的数放到temp中
        while (l <= m){
            temp[i++] = a[l++];
        }
        while (r <= e){
            temp[i++] = a[r++];
        }

        //将temp数组覆盖原数组
        for (int n = 0; n < temp.length; n++) {
            a[s+n] = temp[n];
        }

    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    public static void merge2(int []a,int[] tmp,int left,int mid,int right){
//        int []tmp=new int[a.length];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1]<=a[p2])
                tmp[k++]=a[p1++];
            else
                tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }

    public static void mergeSort2(int [] a,int[] temp,int start,int end){
        if(start<end){//当子序列中只有一个元素时结束递归
            int mid=(start+end)/2;//划分子序列
            mergeSort2(a,temp, start, mid);//对左侧子序列进行递归排序
            mergeSort2(a, temp,mid+1, end);//对右侧子序列进行递归排序
            merge2(a, temp,start, mid, end);//合并
        }
    }

    /**
     * 获取一个打乱的数组
     * @param arr
     */
    private static int[] getRandomArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Random().nextInt(arr.length);
        }
        return arr;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] arr = new int[10000000];
        int[] a =getRandomArr(arr);
        int[] b = a.clone();
        int[] c = b.clone();
        int[] d = b.clone();
        int[] e = b.clone();
        int[] f = b.clone();
        int[] g = b.clone();

        long s = Clock.systemDefaultZone().millis();
        quickSort(a, 0, a.length - 1);
        System.out.println("quickSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("a排序后：" + Arrays.toString(a));

        s = Clock.systemDefaultZone().millis();
        quickSort2(b, 0, b.length - 1);
        System.out.println("quickSort2耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("b排序后：" + Arrays.toString(b));
//        s = Clock.systemDefaultZone().millis();
//        bubbleSort(c);
//        System.out.println("bubbleSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//
//        s = Clock.systemDefaultZone().millis();
//        selectSort(d);
//        System.out.println("selectSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//
//        s = Clock.systemDefaultZone().millis();
//        insertSort(e);
//        System.out.println("insertSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("e排序后：" + Arrays.toString(e));

        s = Clock.systemDefaultZone().millis();
        shellSort(c);
        System.out.println("shellSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("c排序后：" + Arrays.toString(c));

//
        s = Clock.systemDefaultZone().millis();
        mergeSort(f, 0, f.length - 1);
        System.out.println("mergeSort耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("f排序后：" + Arrays.toString(f));

        s = Clock.systemDefaultZone().millis();
        int[] t = new int[g.length];
        mergeSort2(g, t,0, g.length - 1);
        System.out.println("mergeSort2耗时: " + (Clock.systemDefaultZone().millis() - s) + " ms");
//        System.out.println("g排序后：" + Arrays.toString(g));


//        int[] array = { 37, 47, 23, 100, 19, 56, 56, 99, 9 };
//         bubbleSort(array);
//         selectSort(array);
//         insertionSort(array);
//        quickSort2(array, 0, array.length - 1);
//        System.out.println("排序后：" + Arrays.toString(array));
    }
}
