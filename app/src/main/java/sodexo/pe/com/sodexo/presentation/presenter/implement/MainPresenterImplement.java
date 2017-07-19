package sodexo.pe.com.sodexo.presentation.presenter.implement;

import android.preference.PreferenceManager;
import android.util.Log;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.CommerceDataMapper;
import sodexo.pe.com.sodexo.data.mapper.PromoDataMapper;
import sodexo.pe.com.sodexo.data.repository.CommerceDataRepository;
import sodexo.pe.com.sodexo.data.repository.PromoDataRepository;
import sodexo.pe.com.sodexo.data.repository.WishListDataRepository;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;
import sodexo.pe.com.sodexo.domain.interactor.MainInteractor;
import sodexo.pe.com.sodexo.domain.interactor.WishListInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.MainInteractorImplement;
import sodexo.pe.com.sodexo.domain.interactor.implement.WishListInteractorImplment;
import sodexo.pe.com.sodexo.domain.repository.CommerceRepository;
import sodexo.pe.com.sodexo.domain.repository.PromoRepository;
import sodexo.pe.com.sodexo.domain.repository.WishListRepository;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.model.AddWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.DeleteWishListInterface;
import sodexo.pe.com.sodexo.presentation.model.GetCommerceInterface;
import sodexo.pe.com.sodexo.presentation.model.GetPromosInterface;
import sodexo.pe.com.sodexo.presentation.presenter.MainPresenter;


public class MainPresenterImplement implements MainPresenter {
    private MainInteractor interactor;
    private WishListInteractor wishListInteractor;
    private MainView mainView;

    public MainPresenterImplement(MainView mainView) {
        PromoRepository promoRepository = new PromoDataRepository(new PromoDataMapper());
        CommerceRepository commerceRepository = new CommerceDataRepository(new CommerceDataMapper());
        WishListRepository wishListRepository = new WishListDataRepository();
        this.interactor = new MainInteractorImplement(promoRepository, commerceRepository);
        this.wishListInteractor = new WishListInteractorImplment(wishListRepository);
        this.mainView = mainView;
    }

    @Override
    public void showPromo() {
        mainView.showLoading();
        interactor.getAllPromos(new GetPromosInterface() {
            @Override
            public void onGetPromoSuccess(List<PromoEntity> promos) {
                Log.d("promo_key","el primer key es " + promos.get(0).getPromoId());
                mainView.hideLoading();
                mainView.openPromos(promos);
            }

            @Override
            public void onGetPromoError(String message) {
                mainView.hideLoading();
                mainView.showError(message);

            }
        });
    }

    @Override
    public void showCommerces() {
        mainView.showLoading();
        interactor.getAllCommerces(new GetCommerceInterface() {
            @Override
            public void onGetCommerceSuccess(List<CommerceEntity> commerces) {
                Log.d("commerce_id", "the first id is " + commerces.get(0).getId());
                mainView.hideLoading();
                mainView.openCommerce(commerces);
            }

            @Override
            public void onGetCommerceError(String message) {
                mainView.hideLoading();
                mainView.showError(message);
            }
        });
    }

    @Override
    public void showIntranet() {
        Log.v("INTRANET","HICE CLICK A INTRANET");
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString(SodexoApplication.USER_DATA, null) == null) {
            Log.v("INTRANET","ES NULL");
            mainView.openLoginIntranet();
        } else {
            Log.v("INTRANET","NO ES NULL");
            mainView.openIntranetOption();
        }
    }

    @Override
    public void addToWishList(PromoEntity promo) {
        mainView.showLoading();
        wishListInteractor.addPromoAtWishlist(promo.getPromoId(), new AddWishListInterface() {
            @Override
            public void onAddWishListSuccess() {
                mainView.hideLoading();
                mainView.showError("Se ha agreado su promoción al Whislist correctamente");
            }

            @Override
            public void onAddWishListError(String message) {
                mainView.hideLoading();
                mainView.showError(message);
            }
        });
    }

    @Override
    public void deletePromoToWishList(PromoEntity promo) {
        mainView.showLoading();
        wishListInteractor.deletePromoFromWishlist(promo, new DeleteWishListInterface() {
            @Override
            public void onDeleteWishListSuccess() {
                mainView.hideLoading();
                mainView.deleteToWishList("Se ha eliminado tu promoción wishlist correctamente.");
            }

            @Override
            public void onDeleteWishListError(String message) {
                mainView.hideLoading();
                mainView.showError(message);
            }
        });
    }

    @Override
    public void deleteCommerceToWishList(CommerceEntity commerce) {
        mainView.showLoading();
        wishListInteractor.deleteLocalFromWishlist(commerce, new DeleteWishListInterface() {
            @Override
            public void onDeleteWishListSuccess() {
                mainView.hideLoading();
                mainView.deleteToWishList("Se ha eliminado el comercio del wishlist correctamente.");
            }

            @Override
            public void onDeleteWishListError(String message) {
                mainView.hideLoading();
                mainView.showError(message);
            }
        });
    }
}
