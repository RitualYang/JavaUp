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
        HashMap<Object, Integer> hashMap = new HashMap<>(16);
        String[] strs = "a,b,c,d,e,f,g,h,i,g,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
        for (int i = 0; i < strs.length; i++) {
            OneHash oneHash = new OneHash();
            oneHash.setKey(strs[i]);
            hashMap.put(oneHash,oneHash.hashCode());
        }
        System.out.println(hashMap.toString());
    }
}

class OneHash {
    private static int i = 0;
    String key;

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return (int) i++ * 64 + 1;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
