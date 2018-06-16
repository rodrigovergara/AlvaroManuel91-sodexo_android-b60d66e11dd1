package sodexo.pe.com.sodexo.presentation.interfaces;

import sodexo.pe.com.sodexo.domain.entity.UserEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface EditProfileView {
    void showloading();

    void hideLoading();

    void showError(String error);

    void showdata(UserEntity user);
}
