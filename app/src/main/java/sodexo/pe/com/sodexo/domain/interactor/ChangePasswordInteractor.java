package sodexo.pe.com.sodexo.domain.interactor;

import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.model.ChangePasswordInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCardsInterface;

/**
 * Created by RONALD on 12/10/2016.
 */

public interface ChangePasswordInteractor {
    void changePasswordWeb(String password, String newPassword, ChangePasswordInterface callback);

    void getCards(GetCardsInterface getCardsInterface);

    void changePasswordCard(CardEntity cardEntity, String password, String newPassword, String repeatPassword, ChangePasswordInterface callback);
}
