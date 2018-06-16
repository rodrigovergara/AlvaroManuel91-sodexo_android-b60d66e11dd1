package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.interfaces.GetCellInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateCellInfoInterface;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface RegisterSmsServiceInteractor {
    void getCellInfo(GetCellInfoInterface callback);
    void updateCellInfo(String cellphoneNumber, String operator, String active, UpdateCellInfoInterface callback);
}
