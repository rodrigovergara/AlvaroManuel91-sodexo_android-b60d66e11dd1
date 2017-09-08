package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.ShippingAddressEntity;
import sodexo.pe.com.sodexo.domain.interactor.DeliveryInformationInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.DeliveryInformationInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.DeliveryInformationView;
import sodexo.pe.com.sodexo.presentation.model.GetShippingAddressInterface;
import sodexo.pe.com.sodexo.presentation.presenter.DeliveryInformationPresenter;

/**
 * Created by asahel on 9/7/17.
 */

public class DeliveryInformationPresenterImplement implements DeliveryInformationPresenter {

    private DeliveryInformationView deliveryInformationView;
    private DeliveryInformationInteractor deliveryInformationInteractor;

    public DeliveryInformationPresenterImplement(DeliveryInformationView deliveryInformationView) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.deliveryInformationView = deliveryInformationView;
        this.deliveryInformationInteractor = new DeliveryInformationInteractorImplement(intranetRepository);
    }

    @Override
    public void getShippingAddress(String cardNumber, String deliveryId) {
        deliveryInformationView.showLoading();
        deliveryInformationInteractor.getShippingAddress(cardNumber, deliveryId, new GetShippingAddressInterface() {
            @Override
            public void onSuccess(List<ShippingAddressEntity> list) {
                deliveryInformationView.hideLoading();
                if(list != null && !list.isEmpty())
                    deliveryInformationView.onGetShippingAddressResults(list.get(0));
                else
                    deliveryInformationView.showError("Ocurrió un error al obtener la direccion. Vuelva a intentarlo más tarde.");
            }

            @Override
            public void onError(String message) {
                deliveryInformationView.hideLoading();
                deliveryInformationView.showError(message);
            }
        });
    }
}
