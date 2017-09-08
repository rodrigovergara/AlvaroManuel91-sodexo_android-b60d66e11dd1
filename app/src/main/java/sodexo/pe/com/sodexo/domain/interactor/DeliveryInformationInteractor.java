package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.GetShippingAddressInterface;

/**
 * Created by asahel on 9/7/17.
 */

public interface DeliveryInformationInteractor {

    void getShippingAddress(String cardNumber, String deliveryId, GetShippingAddressInterface callback);
}
