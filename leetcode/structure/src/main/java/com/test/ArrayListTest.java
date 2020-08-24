package com.test;

import com.base.ArrayList;
import org.junit.Test;

/**
 * @author WTY
 * @Date 2020/6/6 21:03
 */
public class ArrayListTest {


    @Test
    public void add(){
        ArrayList arrayList = new ArrayList(10);
        // arrayList.get(100);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        arrayList.add(123);
        System.out.println(arrayList);
        arrayList.remove(3);
        System.out.println(arrayList);

    }

    @Test
    public void Clear(){
        ArrayList arrayList = new ArrayList(10);
        for (int i = 0; i < arrayList.size();i++){

        }
        ArrayList arrayList1 = new ArrayList(10);
        long oldFor = System.currentTimeMillis();
        arrayList.clearFor();
        long nowFor = System.currentTimeMillis();
        System.out.println(nowFor - oldFor);
        long oldNew = System.currentTimeMillis();
        arrayList1.clearNew();
        long nowNew = System.currentTimeMillis();
        System.out.println(nowNew - oldNew);

    }
}
