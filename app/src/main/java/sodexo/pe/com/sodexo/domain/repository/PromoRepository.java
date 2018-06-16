package sodexo.pe.com.sodexo.domain.repository;

import sodexo.pe.com.sodexo.presentation.model.GetFilterPromoInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 09/10/2016.
 */

public interface PromoRepository {
    void getAllPromos(GetPromosInterface callback);

    void getFilteredPromo(String query, GetFilterPromoInterface getFilterPromoInterface);
}
