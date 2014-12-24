package com.opservice.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.JSONObjectDeserializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zhangfan on 2014/12/16.
 */
public class MainTest {
    public static void main(String[] args) throws Exception{
        /*JSONObject jsonObject = JSON.parseObject("{'name':'zhangfan'}");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);
        JSONArray jsonArray1 = JSONArray.parseArray("[{'destination':'曼谷','gtime':'2014-12-15','deaddress':'泰国曼谷'}]");
        System.out.println(jsonArray1.get(0));*/
       /* Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+20);
        System.out.println(calendar);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(new Date()));*/
        String timestr = "2014-11-06";
        DateFormat dateFormat = DateFormat.getDateInstance();
        Date date =  dateFormat.parse(timestr);
        System.out.println(date);
    }
}
