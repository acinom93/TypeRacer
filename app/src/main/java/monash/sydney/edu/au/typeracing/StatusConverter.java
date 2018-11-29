package monash.sydney.edu.au.typeracing;

import android.arch.persistence.room.TypeConverter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;

import java.util.List;

public class StatusConverter {

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static String JSONArrayFromIntArray(int[] values) {
        JSONArray jsonArray = new JSONArray();
        for (float value : values) {
            try {
                jsonArray.put(value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray.toString();
    }

    @TypeConverter
    public static int[] JSONArrayToIntArray(String values) {
        try {
            JSONArray jsonArray = new JSONArray(values);
            int[] floatArray = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                floatArray[i] = Integer.parseInt(jsonArray.getString(i));
            }
            return floatArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
