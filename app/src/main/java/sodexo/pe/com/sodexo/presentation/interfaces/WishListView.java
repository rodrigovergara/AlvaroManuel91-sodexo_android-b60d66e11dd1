package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by RONALD on 14/10/2016.
 */

public interface WishListView {
    void showLoading();

    void hideLoading();

    void showSuccess();

    void showError(String message);

    void showCommerces(List<CommerceEntity> commerce);

    void showPromos(List<PromoEntity> promo);

    void showSuccessDelete(String message);
}
