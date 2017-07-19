package sodexo.pe.com.sodexo.domain.repository;

import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface CommerceRepository {
    void getCommerces(GetCommerceInterface callback);
    void getFilteredCommerces(String query, GetFilterComerceInterface callback);
}
