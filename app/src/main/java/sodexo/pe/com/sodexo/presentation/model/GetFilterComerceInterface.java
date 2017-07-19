package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;

/**
 * Created by Alejandra on 3/01/2017.
 */
public interface GetFilterComerceInterface {
    void onGetFilterCommerceSuccess(List<CommerceEntity> list);

    void onGetFilterCommerceError(String message);
}
