package sodexo.pe.com.sodexo.data.datasource.assets;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;

/**
 * Created by RONALD on 12/10/2016.
 */

public class UbigeoDataStore {
    private static String json;

    public static String loadJSONFromAsset() {
        if (json == null) {
            try {
                InputStream is = SodexoApplication.context.getAssets().open("ubigeo.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return json;
    }

    public static List<UbigeoEntityData> getAllDepartments() {
        UbigeoEntityData[] allUbigeo = new Gson().fromJson(loadJSONFromAsset(), UbigeoEntityData[].class);
        List<UbigeoEntityData> allDepartments = new ArrayList<>();
        for (UbigeoEntityData anAllUbigeo : allUbigeo) {
            if (anAllUbigeo.getDistrict().equals("00") && anAllUbigeo.getProvince().equals("00")) {
                allDepartments.add(anAllUbigeo);
            }
        }
        return allDepartments;
    }

    public static List<UbigeoEntityData> getAllProvinces(String department) {
        UbigeoEntityData[] allUbigeo = new Gson().fromJson(loadJSONFromAsset(), UbigeoEntityData[].class);
        List<UbigeoEntityData> allProvinces = new ArrayList<>();
        for (UbigeoEntityData anAllUbigeo : allUbigeo) {
            if (anAllUbigeo.getDepartment().equals(department) && (anAllUbigeo.getDistrict().equals("00") && !(anAllUbigeo.getProvince().equals("00")))) {
                allProvinces.add(anAllUbigeo);
            }
        }
        return allProvinces;
    }

    public static List<UbigeoEntityData> getAllDistricts(String department, String pronvince) {
        UbigeoEntityData[] allUbigeo = new Gson().fromJson(loadJSONFromAsset(), UbigeoEntityData[].class);
        List<UbigeoEntityData> allDistricts = new ArrayList<>();
        for (UbigeoEntityData anAllUbigeo : allUbigeo) {
            if (anAllUbigeo.getDepartment().equals(department) && anAllUbigeo.getProvince().equals(pronvince) && !(anAllUbigeo.getDistrict().equals("00"))) {
                allDistricts.add(anAllUbigeo);
            }
        }
        return allDistricts;
    }

}
