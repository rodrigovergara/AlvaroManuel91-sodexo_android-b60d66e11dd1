package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.FilterCommerceInteractor;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;

/**
 * Created by Alejandra on 3/01/2017.
 */
public class FilterCommerceInteractorImplement implements FilterCommerceInteractor {

    private CommerceRepository commerceRepository;

    public FilterCommerceInteractorImplement(CommerceRepository commerceRepository) {
        this.commerceRepository = commerceRepository;
    }

    @Override
    public void filterCommerce(String query, GetFilterComerceInterface getFilterComerceInterface) {
        commerceRepository.getFilteredCommerces(query,getFilterComerceInterface);
    }
}
