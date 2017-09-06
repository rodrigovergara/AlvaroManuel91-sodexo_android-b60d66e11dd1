package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.interactor.ReplaceCardInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardDetailInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by asahel on 8/30/17.
 */

public class ReplaceCardInteractorImplement implements ReplaceCardInteractor {

    private IntranetRepository intranetRepository;

    public ReplaceCardInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }


    @Override
    public void getReplacementCardNumbers(GetCardsInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getReplacementCardNumbers(data.getDni(), callback);
        }
    }

    @Override
    public void getCardDetail(CardEntity cardEntity, GetCardDetailInterface getCardDetailInterface) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getCardDetail(data.getDni(), cardEntity.getCardNumber(), getCardDetailInterface);
        }
    }

    @Override
    public void getBlockingReasons(GetBlockingReasonsInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            intranetRepository.getBlockingReasons(callback);
        }
    }
}
