package sodexo.pe.com.sodexo.domain.interactor.implement;

import com.google.android.gms.maps.model.LatLng;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.interactor.FilterLocalInteractor;
import sodexo.pe.com.sodexo.domain.repository.FilterLocalRepository;
import sodexo.pe.com.sodexo.presentation.model.GetFilterLocalInterface;

/**
 * Created by RONALD on 11/10/2016.
 */

public class FilterLocalInteractorImplement implements FilterLocalInteractor {
    private FilterLocalRepository filterLocalRepository;

    public FilterLocalInteractorImplement(FilterLocalRepository filterLocalRepository) {
        this.filterLocalRepository = filterLocalRepository;
    }

    @Override
    public void filterLocal(LatLng latLng, int id, GetFilterLocalInterface getFilterLocalInterface) {
        filterLocalRepository.getFilterLocal(latLng, id, getFilterLocalInterface);
    }

    @Override
    public void getCommerceByUbigeo(UbigeoEntityData ubigeoData, GetFilterLocalInterface getFilterLocalInterface) {
        filterLocalRepository.getCommerceByUbigeo(ubigeoData.getDepartment()+ubigeoData.getProvince() + ubigeoData.getDistrict(), getFilterLocalInterface);
    }

    @Override
    public void getCommerceByRouletteOptions(int distance, int categoryId, int stars, GetFilterLocalInterface getFilterLocalInterface) {
        filterLocalRepository.getCommerceByRouletteOptions(distance, categoryId, stars, getFilterLocalInterface);
    }

    @Override
    public void getCommerceByUbigeo(UbigeoEntityData ubigeoData, int typeCommerce, GetFilterLocalInterface getFilterLocalInterface) {
        filterLocalRepository.getCommerceByUbigeo(ubigeoData.getDepartment()+ubigeoData.getProvince() + ubigeoData.getDistrict(), typeCommerce, getFilterLocalInterface);

    }
}
