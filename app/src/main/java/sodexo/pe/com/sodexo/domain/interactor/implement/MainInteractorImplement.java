package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.MainInteractor;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.domain.repository.PromoRepository;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;

public class MainInteractorImplement implements MainInteractor {
    private final PromoRepository promoRepository;
    private final CommerceRepository commerceRepository;

    public MainInteractorImplement(PromoRepository promoRepository, CommerceRepository commerceRepository) {
        this.promoRepository = promoRepository;
        this.commerceRepository = commerceRepository;
    }

    @Override
    public void getAllPromos(GetPromosInterface callback) {
        promoRepository.getAllPromos(callback);
    }

    @Override
    public void getAllCommerces(GetCommerceInterface callback) {
        commerceRepository.getCommerces(callback);
    }
}
