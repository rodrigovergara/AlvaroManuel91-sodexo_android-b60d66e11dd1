package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.ViewCreditInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public class ViewCreditInteractorImplement implements ViewCreditInteractor {
    private IntranetRepository intranetRepository;

    public ViewCreditInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }


    @Override
    public void getCards(GetCardsInterface calback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getCards(data.getDni(), calback);
        }
    }

    @Override
    public void getDetailCard(CardEntity cardEntity, GetCardDetailInterface getCardDetailInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getCardDetail(data.getDni(), cardEntity.getCardNumber(), getCardDetailInterface);
        }
    }
}
