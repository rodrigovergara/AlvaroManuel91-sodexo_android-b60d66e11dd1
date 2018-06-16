package sodexo.pe.com.sodexo.presentation.model;

import sodexo.pe.com.sodexo.domain.entity.UserEntity;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface GetUserInfoInterface {
    void onGetUserInfoSuccess(UserEntity user);

    void onGetUserInfoError(String error);
}
