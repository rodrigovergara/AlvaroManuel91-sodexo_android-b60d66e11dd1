package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

/**
 * Created by RONALD on 07/10/2016.
 */

public interface MainInteractor {
    void getAllPromos(GetPromosInterface callback);

    void getAllCommerces(GetCommerceInterface callback);
}
