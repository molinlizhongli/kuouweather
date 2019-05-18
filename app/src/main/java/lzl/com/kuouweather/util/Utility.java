package lzl.com.kuouweather.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lzl.com.kuouweather.db.City;
import lzl.com.kuouweather.db.County;
import lzl.com.kuouweather.db.Province;

public class Utility {
    /**
     * 解析从服务器获得到的省级Json数据
     */
    public static boolean handleProvinceResponse(String response) {

        if (!TextUtils.isEmpty( response )) {
            try {
                JSONArray allProvinces = new JSONArray( response );
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject( i );
                    Province province = new Province();
                    province.setProvinceName( provinceObject.getString( "name" ) );//json数据键值为name和id
                    province.setProvinceCode( provinceObject.getInt( "id" ) );
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析从服务器获得到的市级Json数据
     */
    public static boolean handleCityResponse(String response, int provinceId) {

        if (!TextUtils.isEmpty( response )) {
            try {
                JSONArray allCities = new JSONArray( response );
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject =allCities.getJSONObject( i ) ;
                    City city = new City();
                    city.setCityName( cityObject.getString( "name" ) );//json数据键值为name和id
                    city.setCityCode( cityObject.getInt( "id" ) );
                    city.setProvinceId( provinceId );
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析从服务器获得到的县级Json数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {

        if (!TextUtils.isEmpty( response )) {
            try {
                JSONArray allCounties = new JSONArray( response );
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject =allCounties.getJSONObject( i ) ;
                    County county = new County();
                    county.setCountyName( countyObject.getString( "name" ) );//json数据键值为name和id
                    county.setWeatherId( countyObject.getString( "weather_id" ) );
                    county.setCityId( cityId );
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
