package sodexo.pe.com.sodexo.domain.interactor.implement;

import android.preference.PreferenceManager;

import sodexo.pe.com.sodexo.domain.interactor.DeliveryInformationInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.model.GetShippingAddressInterface;

/**
 * Created by asahel on 9/7/17.
 */

public class DeliveryInformationInteractorImplement implements DeliveryInformationInteractor {

    private IntranetRepository intranetRepository;

    public DeliveryInformationInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void getShippingAddress(String cardNumber, String deliveryId, GetShippingAddressInterface callback) {
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) != null) {
            intranetRepository.getShippingAddress(cardNumber,deliveryId,callback);
        }
    }
}
