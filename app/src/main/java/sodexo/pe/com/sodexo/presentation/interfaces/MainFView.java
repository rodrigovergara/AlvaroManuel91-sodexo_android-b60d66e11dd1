package sodexo.pe.com.sodexo.presentation.interfaces;

import android.os.Bundle;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;
import sodexo.pe.com.sodexo.domain.entity.OptionEntity;

/**
 * Created by RONALD on 11/10/2016.
 */

public interface MainFView {
    void populateOptions(List<OptionEntity> list);

    void showMessageError(String message);

    void showLocalFilterInMap(List<LocalFilterEntity> list);

    void showLoading();

    void hideLoading();

    void showCommerces(Bundle bundle);
}
