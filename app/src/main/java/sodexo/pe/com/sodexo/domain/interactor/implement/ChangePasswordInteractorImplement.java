package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.ChangePasswordInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.ChangePasswordInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public class ChangePasswordInteractorImplement implements ChangePasswordInteractor {
    private IntranetRepository repository;
    public ChangePasswordInteractorImplement(IntranetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void changePasswordWeb(String password, String newPassword, ChangePasswordInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.changePasswordWeb(data.getDni(), password, newPassword, callback);
        }

    }

    @Override
    public void getCards(GetCardsInterface getCardsInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.getCards(data.getDni(), getCardsInterface);
        }
    }

    @Override
    public void changePasswordCard(CardEntity cardEntity, String password, String newPassword, String repeatPassword, ChangePasswordInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            repository.changePasswordCard(data.getDni(), cardEntity.getCardNumber(), password, newPassword, repeatPassword, callback);
        }
    }
}
