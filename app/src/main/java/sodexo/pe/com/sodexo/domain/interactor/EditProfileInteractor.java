package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.presentation.model.GetUserInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface EditProfileInteractor {
    void getUserInfo(GetUserInfoInterface callback);

    void updateUserInfo(String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex, UpdateUserInfoOnterface updateUserInfoOnterface);
}
