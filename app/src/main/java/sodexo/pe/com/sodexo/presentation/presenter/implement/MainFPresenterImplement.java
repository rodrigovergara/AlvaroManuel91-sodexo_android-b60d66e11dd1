package sodexo.pe.com.sodexo.presentation.presenter.implement;

import android.os.Bundle;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.mapper.FilterLocalDataMapper;
import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.data.repository.FilterLocalDatRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;
import sodexo.pe.com.sodexo.domain.entity.OptionEntity;
import sodexo.pe.com.sodexo.domain.interactor.FilterLocalInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.FilterLocalInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.FilterLocalRepository;
import sodexo.pe.com.sodexo.presentation.fragment.menu.CommerceFragment;
import sodexo.pe.com.sodexo.presentation.interfaces.MainFView;
import sodexo.pe.com.sodexo.presentation.model.GetFilterLocalInterface;
import sodexo.pe.com.sodexo.presentation.presenter.MainFPresenter;

/**
 * Created by RONALD on 11/10/2016.
 */

public class MainFPresenterImplement implements MainFPresenter {
    private MainFView view;
    private FilterLocalInteractor interactor;

    public MainFPresenterImplement(MainFView view) {
        this.view = view;
        FilterLocalRepository filterLocalRepository = new FilterLocalDatRepository(new FilterLocalDataMapper());
        this.interactor = new FilterLocalInteractorImplement(filterLocalRepository);
    }

    @Override
    public void populateFilterOption() {
        List<OptionEntity> list = new ArrayList<>();
        list.add(new OptionEntity(1, R.drawable.boton_entretenimiento));
        list.add(new OptionEntity(2, R.drawable.boton_viajes));
        list.add(new OptionEntity(3, R.drawable.boton_restaurantes));
        list.add(new OptionEntity(4, R.drawable.boton_salud));
        list.add(new OptionEntity(5, R.drawable.boton_compras));
        list.add(new OptionEntity(6, R.drawable.boton_saludable));
        view.populateOptions(list);
    }

    @Override
    public void showFilterLocal(LatLng latLng, int id) {
        view.showLoading();
        final int typeCommerce = id;
        interactor.filterLocal(latLng, typeCommerce, new GetFilterLocalInterface() {
            @Override
            public void onGetFilterLocalSuccess(List<LocalFilterEntity> list) {
                view.hideLoading();
                if (list.size() > 0) {
                    view.showLocalFilterInMap(list);

                } else {
                    view.showMessageError(typeCommerce > 0 ? "No se encontraron comercios cercanos del tipo indicado" : "No se encontraron comercios cercanos a la dirección indicada");
                }
            }

            @Override
            public void onGetFilterLocalError(String message) {
                view.hideLoading();
                view.showMessageError(message);
            }
        });
    }

    @Override
    public void showCommercesFilter(List<LocalFilterEntity> list) {
        Bundle bundle = new Bundle();
        List<CommerceEntity> commerceEntities = new ArrayList<>();
        for (LocalFilterEntity localFilterEntity : list) {
            CommerceEntity entity = new CommerceEntity();
            entity.setName(localFilterEntity.getName());
            entity.setLatitude(localFilterEntity.getLatitude());
            entity.setLogo(localFilterEntity.getLogo());
            entity.setLongitude(localFilterEntity.getLongitude());
            entity.setBanner(localFilterEntity.getBanner());
            commerceEntities.add(entity);
        }
        bundle.putParcelableArrayList(CommerceFragment.COMMERCES, (ArrayList<? extends Parcelable>) commerceEntities);
        view.showCommerces(bundle);
    }

    @Override
    public void getCommercesByUbigeo(UbigeoEntityData ubigeoData) {
        view.showLoading();
        interactor.getCommerceByUbigeo(ubigeoData, new GetFilterLocalInterface() {
            @Override
            public void onGetFilterLocalSuccess(List<LocalFilterEntity> list) {
                view.hideLoading();
                if (list.size() > 0) {
                    view.showLocalFilterInMap(list);

                } else {
                    view.showMessageError("No se encontraron comercios con el ubigeo indicado");
                }
            }

            @Override
            public void onGetFilterLocalError(String message) {
                view.hideLoading();
                view.showMessageError(message);
            }
        });
    }

    @Override
    public void getCommercesByRouletteOptions(int distance, int categoryId, int stars) {
        view.showLoading();
        interactor.getCommerceByRouletteOptions(distance, categoryId, stars, new GetFilterLocalInterface() {
            @Override
            public void onGetFilterLocalSuccess(List<LocalFilterEntity> list) {
                view.hideLoading();
                if (list.size() <= 0) {
                    view.showMessageError("No hay comercios cercanos con los parámetros indicados");
                } else {
                    view.showLocalFilterInMap(list);
                }
            }

            @Override
            public void onGetFilterLocalError(String message) {
                view.hideLoading();
                view.showMessageError(message);
            }
        });
    }

    @Override
    public void showFilterLocalByUbigeo(UbigeoEntityData ubigeo, int id) {
        view.showLoading();
        final int typeCommerce = id;
        try{
            interactor.getCommerceByUbigeo(ubigeo, id, new GetFilterLocalInterface() {
                @Override
                public void onGetFilterLocalSuccess(List<LocalFilterEntity> list) {
                    view.hideLoading();
                    if (list.size() > 0) {
                        view.showLocalFilterInMap(list);

                    } else {
                        view.showMessageError(typeCommerce > 0 ? "No se encontraron comercios cercanos del tipo indicado" : "No se encontraron comercios cercanos a la dirección indicada");
                    }
                }

                @Override
                public void onGetFilterLocalError(String message) {
                    view.hideLoading();
                    view.showMessageError(message);
                }
            });
        }
        catch (NullPointerException e){
            e.printStackTrace();
            view.hideLoading();
            view.showMessageError("Debes buscar una dirección para obtener la lista de comercios.");
        }

    }
}
