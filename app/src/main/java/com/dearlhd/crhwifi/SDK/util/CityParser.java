package com.dearlhd.crhwifi.SDK.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dearlhd on 2017/12/19.
 */
public class CityParser {
    final static String CITY_LIST = "cities.txt";

    public static List<String> getCities (Context context) {
        List<String> cities = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open(CITY_LIST);
            byte[] buffer = new byte[1024];
            int len = is.read(buffer);
            String str = new String(buffer, 0, len);
            String[] citiesArray = str.split(",");
            for (int i = 0; i < citiesArray.length; i++) {
                cities.add(citiesArray[i]);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
