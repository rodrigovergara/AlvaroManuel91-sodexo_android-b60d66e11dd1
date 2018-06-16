package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by Alejandra on 3/01/2017.
 */
public interface PromoFView {
    void showFilterPromo(List<PromoEntity> commerceEntities);
    void showFilterError(String message);
}
