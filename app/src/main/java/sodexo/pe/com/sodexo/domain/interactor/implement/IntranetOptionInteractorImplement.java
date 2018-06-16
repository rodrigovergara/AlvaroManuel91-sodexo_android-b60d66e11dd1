package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.interactor.IntranetOptionInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetIntranetOptionInterface;

public class IntranetOptionInteractorImplement implements IntranetOptionInteractor {
    private IntranetRepository repository;

    public IntranetOptionInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getOptions(GetIntranetOptionInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            Log.d("Aca esta","djnfdjsn");
            Log.d("Segundo",json);
             repository.getOptions(data.getDni(), callback);
        }
    }
}
