package sodexo.pe.com.sodexo.presentation.interfaces;

import sodexo.pe.com.sodexo.domain.entity.ShippingAddressEntity;

/**
 * Created by asahel on 9/7/17.
 */

public interface DeliveryInformationView extends BaseParentView {

    void onGetShippingAddressResults(ShippingAddressEntity shippingAddressEntity);

}
