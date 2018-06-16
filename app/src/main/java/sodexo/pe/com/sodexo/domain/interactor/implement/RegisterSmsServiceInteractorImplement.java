package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.interactor.RegisterSmsServiceInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.GetCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateCellInfoInterface;

/**
 * Created by RONALD on 13/10/2016.
 */

public class RegisterSmsServiceInteractorImplement implements RegisterSmsServiceInteractor {
    private IntranetRepository repository;
    public RegisterSmsServiceInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getCellInfo(final GetCellInfoInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.getCellInfo(data.getDni(), callback);
        }
    }

    @Override
    public void updateCellInfo(String cellphoneNumber, String operator, String active, UpdateCellInfoInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.updateCellInfo(data.getDni(), cellphoneNumber, operator, active, callback);
        }
    }
}
