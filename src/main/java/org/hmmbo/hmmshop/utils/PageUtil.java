package org.hmmbo.hmmshop.utils;


import java.util.ArrayList;
import java.util.List;

public class PageUtil {
    public static <T> List<T> getpageitems(List<T> items, int page , int spaces){
        int upperbound = page * spaces;
        int lowerbound = upperbound - spaces;

        List<T> newItems = new ArrayList<>();
        for(int i = lowerbound;i<upperbound;i++){
            try {
                newItems.add(items.get(i));
            }catch (IndexOutOfBoundsException x){
                break;
            }
        }
        return newItems;
    }
    public static boolean isPageValid(List<?> items,int page, int spaces){
        if(page <= 0){return false;}

        int upperbound= page * spaces;
        int lowerbound= upperbound - spaces;

        return items.size() > lowerbound;
    }
}
