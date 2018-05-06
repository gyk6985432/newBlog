package com.yorker.service;

import com.yorker.dao.FindArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gyk on 2018/5/6.
 */
@Component
public class RequestDateService {
    @Autowired
    private FindArticle dao;
    public Map<String, List<String>> getDate(){
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        List<Date> results = dao.findAllCreatedDate();
        for (Date date : results){
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            if (!map.containsKey(year)){
                List<String> months = new ArrayList<String>();
                months.add(month);
                map.put(year, months);
            }else {
                List<String> months = map.get(year);
                if (!months.contains(month)){
                    months.add(month);
                }
                map.put(year, months);
            }
        }
        return map;
    }
}
