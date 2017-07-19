package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface GetGeoGoogleInterface {
    void onGetGeoGoogleSuccess(List<PlaceEntity> promo);

    void onGetGeoGoogleError(String message);
}
