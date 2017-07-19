package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.OperatorEntity;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface RegisterSmsServicePresenter {
    void getCellInfo();

    void updateCellInfo(String cellNumber, OperatorEntity operatorEntity, String registerSms);
}
