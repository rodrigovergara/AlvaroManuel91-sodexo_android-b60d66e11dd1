package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;

/**
 * Created by Alejandra on 3/01/2017.
 */
public interface CommerceFView {
    void showFilterCommerce(List<CommerceEntity> commerceEntities);
    void showFilterError(String message);
}
