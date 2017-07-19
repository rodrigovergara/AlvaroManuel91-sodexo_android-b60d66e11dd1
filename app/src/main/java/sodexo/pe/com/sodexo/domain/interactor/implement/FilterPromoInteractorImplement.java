package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.FilterPromoInteractor;
import sodexo.pe.com.sodexo.domain.repository.PromoRepository;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetFilterPromoInterface;

/**
 * Created by Alejandra on 3/01/2017.
 */
public class FilterPromoInteractorImplement implements FilterPromoInteractor {

    private PromoRepository promoRepository;

    public FilterPromoInteractorImplement(PromoRepository promoRepository) {
        this.promoRepository = promoRepository;
    }

    @Override
    public void filterPromo(String query, GetFilterPromoInterface getFilterPromoInterface) {
        promoRepository.getFilteredPromo(query, getFilterPromoInterface);
    }
}
