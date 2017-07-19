package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.repository.WishListDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.interactor.WishListInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.WishListInteractorImplment;
import sodexo.pe.com.sodexo.domain.repository.WishListRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.WishListView;
import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;
import sodexo.pe.com.sodexo.presentation.presenter.WishListPresenter;

/**
 * Created by RONALD on 14/10/2016.
 */

public class WishListPresenterImplement implements WishListPresenter {
    private WishListView view;
    private WishListInteractor interactor;

    public WishListPresenterImplement(WishListView view) {
        WishListRepository wishListRepository = new WishListDataRepository();
        this.view = view;
        this.interactor = new WishListInteractorImplment(wishListRepository);
    }

    @Override
    public void addLocalToWishlist(CommerceEntity commerceEntity) {
        view.showLoading();
        interactor.addLocalAtWishlist(commerceEntity.getId(), new AddWishListInterface() {
            @Override
            public void onAddWishListSuccess() {
                view.hideLoading();
                view.showSuccess();
            }

            @Override
            public void onAddWishListError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void addPromoToWishlist(PromoEntity promoEntity) {
        view.showLoading();
        interactor.addPromoAtWishlist(promoEntity.getPromoId(), new AddWishListInterface() {
            @Override
            public void onAddWishListSuccess() {
                view.hideLoading();
                view.showSuccess();
            }

            @Override
            public void onAddWishListError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });

    }

    @Override
    public void getCommerceWishList() {
        view.showLoading();
        interactor.getCommerceWishList(new GetCommerceInterface() {
            @Override
            public void onGetCommerceSuccess(List<CommerceEntity> commerce) {
                view.hideLoading();
                view.showCommerces(commerce);
            }

            @Override
            public void onGetCommerceError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void getPromoWishList() {
        view.showLoading();
        interactor.getPromoWishList(new GetPromosInterface() {
            @Override
            public void onGetPromoSuccess(List<PromoEntity> promo) {
                view.hideLoading();
                view.showPromos(promo);
            }

            @Override
            public void onGetPromoError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void deleteLocalToWishlist(CommerceEntity commerceEntity) {
        view.showLoading();
        interactor.deleteLocalFromWishlist(commerceEntity, new DeleteWishListInterface(){
            @Override
            public void onDeleteWishListSuccess() {
                view.hideLoading();
                view.showSuccessDelete("Se ha eliminado el comercio del wishlist correctamente");
            }

            @Override
            public void onDeleteWishListError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void deletePromoToWishList(PromoEntity promoEntity) {
        view.showLoading();
        interactor.deletePromoFromWishlist(promoEntity, new DeleteWishListInterface(){
            @Override
            public void onDeleteWishListSuccess() {
                view.hideLoading();
                view.showSuccessDelete("Se ha eliminado la promoción del wishlist correctamente");
            }

            @Override
            public void onDeleteWishListError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
