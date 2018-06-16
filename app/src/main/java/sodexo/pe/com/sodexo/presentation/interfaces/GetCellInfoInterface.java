package sodexo.pe.com.sodexo.presentation.interfaces;

import sodexo.pe.com.sodexo.domain.entity.CellInfoEntity;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface GetCellInfoInterface {
    void onGetCellInfoSuccess(CellInfoEntity entity);

    void onGetCellInfoError(String message);
}

