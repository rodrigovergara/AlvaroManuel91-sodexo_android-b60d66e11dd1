package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetFilterPromoInterface;

/**
 * Created by Alejandra on 3/01/2017.
 */
public interface FilterPromoInteractor {

    void filterPromo(String query, GetFilterPromoInterface getFilterPromoInterface);


}
