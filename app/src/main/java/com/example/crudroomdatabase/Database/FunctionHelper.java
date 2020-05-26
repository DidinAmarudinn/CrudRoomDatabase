package com.example.crudroomdatabase.Database;

import java.text.DecimalFormat;

public class FunctionHelper {
    public static  String rupiahFormater(int price){
        DecimalFormat format=new DecimalFormat("#,###");
        return "Rp. "+format.format(price).replaceAll(",",".");
    }
}
