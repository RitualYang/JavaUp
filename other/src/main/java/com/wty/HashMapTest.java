package com.wty;

import java.util.HashMap;

/**
 * TODO
 *
 * @author wty
 * @date 2020/10/28 18:44
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, Long> hashMap = new HashMap<>(16);
        String[] strs = "a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
        for (int i = 0; i < strs.length; i++) {
            hashMap.put(strs[i],155333333L);
        }
        System.out.println(hashMap.toString());
    }
}
