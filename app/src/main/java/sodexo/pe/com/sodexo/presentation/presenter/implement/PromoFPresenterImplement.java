package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.CommerceDataMapper;
import sodexo.pe.com.sodexo.data.mapper.PromoDataMapper;
import sodexo.pe.com.sodexo.data.repository.CommerceDataRepository;
import sodexo.pe.com.sodexo.data.repository.PromoDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.interactor.FilterCommerceInteractor;
import sodexo.pe.com.sodexo.domain.interactor.FilterPromoInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.FilterCommerceInteractorImplement;
import sodexo.pe.com.sodexo.domain.interactor.implement.FilterPromoInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.domain.repository.PromoRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.CommerceFView;
import sodexo.pe.com.sodexo.presentation.interfaces.PromoFView;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetFilterPromoInterface;
import sodexo.pe.com.sodexo.presentation.presenter.CommerceFPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.PromoFPresenter;

/**
 * Created by Alejandra on 3/01/2017.
 */
public class PromoFPresenterImplement implements PromoFPresenter {

    PromoFView view;
    FilterPromoInteractor interactor;

    public PromoFPresenterImplement(PromoFView view) {
        this.view = view;
        PromoRepository promoRepository = new PromoDataRepository(new PromoDataMapper());
        interactor = new FilterPromoInteractorImplement(promoRepository);
    }

    @Override
    public void showFilterPromo(String query) {
        interactor.filterPromo(query, new GetFilterPromoInterface() {
            @Override
            public void onGetFilterPromoSuccess(List<PromoEntity> list) {
                view.showFilterPromo(list);
            }

            @Override
            public void onGetFilterPromoError(String message) {
                view.showFilterError(message);
            }
        });
    }
}
