package sodexo.pe.com.sodexo.presentation.presenter.implement;

import android.util.Log;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.CommerceDataMapper;
import sodexo.pe.com.sodexo.data.repository.CommerceDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.interactor.FilterCommerceInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.FilterCommerceInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.CommerceFView;
import sodexo.pe.com.sodexo.presentation.model.GetFilterComerceInterface;
import sodexo.pe.com.sodexo.presentation.presenter.CommerceFPresenter;

/**
 * Created by Alejandra on 3/01/2017.
 */
public class CommerceFPresenterImplement implements CommerceFPresenter {

    CommerceFView view;
    FilterCommerceInteractor interactor;

    public CommerceFPresenterImplement(CommerceFView view) {
        this.view = view;
        CommerceRepository commerceRepository = new CommerceDataRepository(new CommerceDataMapper());
        interactor = new FilterCommerceInteractorImplement(commerceRepository);
    }

    @Override
    public void showFilterCommerce(String query) {
        interactor.filterCommerce(query, new GetFilterComerceInterface() {
            @Override
            public void onGetFilterCommerceSuccess(List<CommerceEntity> list) {
                view.showFilterCommerce(list);
            }

            @Override
            public void onGetFilterCommerceError(String message) {
                view.showFilterError(message);
            }
        });
    }
}
