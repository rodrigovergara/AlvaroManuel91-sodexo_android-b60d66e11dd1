package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import com.google.gson.Gson;

import sodexo.pe.com.sodexo.data.model.LoginEntityData;
import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.interactor.LastMovementsInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.GetAllMovements;
import sodexo.pe.com.sodexo.presentation.model.GetAllTypeCards;

public class LastMovementsInteractorImplement implements LastMovementsInteractor {
    private IntranetRepository intranetRepository;

    public LastMovementsInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }


    @Override
    public void getAllCardsType(GetAllTypeCards callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getCardsType(data.getDni(), callback);
        }
    }

    @Override
    public void getMovements(CardTypeEntity typeEntity, GetAllMovements callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            String json = PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null);
            LoginEntityData data = new Gson().fromJson(json, LoginEntityData.class);
            intranetRepository.getLastMovement(data.getDni(), typeEntity.getCardId(), callback);
        }
    }
}
