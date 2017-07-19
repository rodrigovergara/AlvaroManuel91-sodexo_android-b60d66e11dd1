package sodexo.pe.com.sodexo.presentation.presenter.implement;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.UserEntity;
import sodexo.pe.com.sodexo.domain.interactor.EditProfileInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.EditProfileInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.EditProfileView;
import sodexo.pe.com.sodexo.presentation.model.GetUserInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;
import sodexo.pe.com.sodexo.presentation.presenter.EditProfilePresenter;

/**
 * Created by RONALD on 13/10/2016.
 */

public class EditProfilePresenterImplement implements EditProfilePresenter {
    private EditProfileView view;
    private EditProfileInteractor interactor;

    public EditProfilePresenterImplement(EditProfileView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.interactor = new EditProfileInteractorImplement(intranetRepository);
    }

    @Override
    public void getUserInfo() {
        view.showloading();
        interactor.getUserInfo(new GetUserInfoInterface() {
            @Override
            public void onGetUserInfoSuccess(UserEntity user) {
                view.hideLoading();
                view.showdata(user);
            }

            @Override
            public void onGetUserInfoError(String error) {
                view.hideLoading();
                view.showError(error);
            }
        });
    }

    @Override
    public void updateInfo(String address, String department, String province, String district, String email, String phone, String cellPhone, String cargo, String sex) {
        view.showloading();
        interactor.updateUserInfo(address, department, province, district, email, phone, cellPhone, cargo, sex, new UpdateUserInfoOnterface(){
            @Override
            public void onUpdateSucces() {
                view.hideLoading();
                view.showError("Se han actualizado los datos correctamente");
            }

            @Override
            public void onUpdateError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
