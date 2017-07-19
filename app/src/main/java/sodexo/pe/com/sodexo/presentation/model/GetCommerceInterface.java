package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface GetCommerceInterface {
    void onGetCommerceSuccess(List<CommerceEntity> commerce);

    void onGetCommerceError(String message);
}
