package android.coolweather.com.coolweather.util;

import android.coolweather.com.coolweather.bd.City;
import android.coolweather.com.coolweather.bd.Province;
import android.coolweather.com.coolweather.bd.Country;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponce(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0 ;i <allProvinces.length() ; i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponce(String response , int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i = 0 ;i <allCities.length() ; i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City() ;
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();

                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }


    /*
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountryResponce(String response , int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCountries = new JSONArray(response);
                for (int i = 0 ;i <allCountries.length() ; i++) {
                    JSONObject countryObject = allCountries.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();


                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
