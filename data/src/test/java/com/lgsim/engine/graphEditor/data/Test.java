package com.lgsim.engine.graphEditor.data;

import com.alibaba.fastjson.JSON;
import com.lgsim.engine.graphEditor.data.test.Student;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        //json集合
        /*String jsonString = "[{\"Age\":[\"13\"],\"name\":\"zhangSan\"},{\"Age\":[\"14\",\"15\"],\"name\":\"liSi\"}]";

        String jstr = jsonString.toLowerCase();

        JSONArray jsonArray = JSONArray.fromObject(jstr);
        //Java集合
        List<Student> list = (List<Student>) JSONArray.toCollection(jsonArray,Student.class);*/
        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student();
        list.add(s1);
        list.add(s1);
        list.add(s1);
        Test test = new Test();
        test.test1(list);
        //System.out.println(list.toString());
        System.out.println("-------------------------");

        String jsonstr = JSON.toJSONString(list);
        System.out.println(jsonstr);
    }

    public void test1(Collection<Student> list) {
        String string = list.toString();
        System.out.println(string);
        System.out.println("------22222------------");

        System.out.println(list);
    }
}
