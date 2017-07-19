package sodexo.pe.com.sodexo.presentation.presenter;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ChangePasswordCardPresenter {
    void changePassword(CardEntity cardEntity, String password, String newPassword, String repeatNewPassword);
    void getNumberCards();
}
