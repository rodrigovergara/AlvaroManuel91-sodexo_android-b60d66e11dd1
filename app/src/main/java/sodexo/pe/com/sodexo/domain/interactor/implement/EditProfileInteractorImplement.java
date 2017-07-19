package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.interactor.EditProfileInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetUserInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;

/**
 * Created by RONALD on 13/10/2016.
 */

public class EditProfileInteractorImplement implements EditProfileInteractor {
    private IntranetRepository intranetRepository;

    public EditProfileInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void getUserInfo(GetUserInfoInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getUserInfo(data.getDni(), data.getRuc(), callback);
        }
    }

    @Override
    public void updateUserInfo(String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, UpdateUserInfoOnterface updateUserInfoOnterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.updateInfo(data.getDni(), address, department,
                    province, district, email, phone, cellPhone, cargo, sex, updateUserInfoOnterface);
        }
    }
}
