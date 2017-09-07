package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import sodexo.pe.com.sodexo.domain.interactor.BlockCardInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;

/**
 * Created by asahel on 8/16/17.
 */

public class BlockCardInteractorImplement implements BlockCardInteractor {
    private IntranetRepository intranetRepository;

    public BlockCardInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void blockCard(String cardNumber, String reasonId, BaseParentInterface baseParentInterface) {
        intranetRepository.blockCard(cardNumber, reasonId, baseParentInterface);
    }

    @Override
    public void getBlockingReasons(GetBlockingReasonsInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            intranetRepository.getBlockingReasons(callback);
        }
    }
}
