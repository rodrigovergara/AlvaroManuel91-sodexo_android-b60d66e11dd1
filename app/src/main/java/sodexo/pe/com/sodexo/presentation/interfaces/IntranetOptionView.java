package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;

/**
 * Created by ronaldvelasquez on 9/12/16.
 */

public interface IntranetOptionView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showOptions(List<OptionIntranetEntity> list);

}
