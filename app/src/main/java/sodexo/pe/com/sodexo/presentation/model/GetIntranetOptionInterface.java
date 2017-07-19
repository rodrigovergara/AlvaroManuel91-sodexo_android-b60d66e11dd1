package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;

/**
 * Created by ronaldvelasquez on 9/12/16.
 */

public interface GetIntranetOptionInterface {
    void onGetIntranetOptionSuccess(List<OptionIntranetEntity> list);

    void onGetIntranetOptionError(String error);
}
