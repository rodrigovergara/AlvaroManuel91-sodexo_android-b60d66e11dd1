package sodexo.pe.com.sodexo.presentation.presenter;

/**
 * Created by RONALD on 13/10/2016.
 */

public interface EditProfilePresenter {
    void getUserInfo();

    void updateInfo(String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex);
}
