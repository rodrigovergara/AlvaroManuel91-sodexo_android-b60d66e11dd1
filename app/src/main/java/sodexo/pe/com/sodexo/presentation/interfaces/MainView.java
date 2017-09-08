package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.data.model.UbigeoEntityData;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.presentation.fragment.intranet.PromoCommerceFragment;

/**
 * Created by RONALD on 05/10/2016.
 */

public interface MainView {
    void showLoading();

    void hideLoading();

    void openCommerce(List<CommerceEntity> commerces);

    void openPromos(List<PromoEntity> promos);

    void openCommerce(CommerceEntity commerceEntity, boolean isWishList, boolean fromMarker);

    void openMain();

    void openClub();

    void openConfiguration();

    void openLoginIntranet();

    void openIntranetOption();

    void openViewCredit();

    void openLastMovements();

    void openEditProfile();

    void openRoullete();

    void openChangePasswordCard();

    void openChangePasswordWeb();

    void openJoinService();

    void openWishtList();

    void showError(String message);

    void openPromoDetail(PromoEntity promo, boolean isWishList);

    void openMainFromRoulette(int distance, int categoryId, int stars);

    void addToWishList(PromoEntity promo);

    void deletePromoWishList(PromoEntity promo, PromoCommerceFragment pc);

    void deleteCommerceWishList(CommerceEntity commerce, PromoCommerceFragment pc);

    void deleteToWishList(String message);

    void openBlogList();

    void openQuiz();

    void openBlogDetail(BlogEntity blog);

    void showQuizDetail(QuizEntity quizEntity);

    void openBlockCard();

    void openReplaceCard();

    void openDeliveryInformation(String cardNumber);

    void openPaymentInformationSummary();
}
