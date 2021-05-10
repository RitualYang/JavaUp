package com.wty;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author wty
 * @date 2021/4/25 14:43
 */
public class JsonTest {

    public static void main(String[] args) {
        B b = new B();
        b.setValue("123");
        b.setName("name");
        b.setRoomsIds(Arrays.asList("123","456","789"));
        String s = JSON.toJSONString(b);
        System.out.println(s);
        Map map = JSON.parseObject(s, Map.class);
        System.out.println(map.toString());


        A a = JSON.parseObject(s, A.class);
        System.out.println(a.toString());
    }
}
@Data
class A {
    private String name;
    private List<String> roomsIds;
}
@Data
class B extends A {
    private String value;

}
