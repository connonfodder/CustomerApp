package com.aadhk.customer.ui.activity;

import com.aadhk.customer.bean.Company;
import com.aadhk.customer.bean.FoodStyles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jack on 01/12/2016.
 */
public class DataProvider {

    public static List<FoodStyles> foodStyles(List<Company> companies) {
        Set<String> set = new HashSet<>();
        for(Company item : companies){
            set.addAll(item.getStyles());
        }
        List<FoodStyles> data = new ArrayList<>();
        for(String item : set){
            data.add(new FoodStyles(0, item));
        }
        return data;
    }
}
