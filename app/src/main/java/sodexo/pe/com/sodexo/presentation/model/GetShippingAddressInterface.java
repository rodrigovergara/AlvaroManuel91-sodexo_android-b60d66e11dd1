package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.ShippingAddressEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface GetShippingAddressInterface {
    void onSuccess(List<ShippingAddressEntity> list);

    void onError(String message) ;
}
