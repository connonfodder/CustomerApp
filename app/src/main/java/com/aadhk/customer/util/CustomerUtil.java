package com.aadhk.customer.util;

import com.aadhk.customer.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 06/12/2016.
 */

public class CustomerUtil {

    public static List<Category> cloneCategoryList(List<Category> oldList) {
        List<Category> newList = new ArrayList<>();
        for (Category item : oldList) {
            newList.add(item.clone());
        }
        return newList;
    }
}
