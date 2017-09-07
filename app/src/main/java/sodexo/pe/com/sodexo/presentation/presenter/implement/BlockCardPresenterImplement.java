package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.UserEntity;
import sodexo.pe.com.sodexo.domain.interactor.BlockCardInteractor;
import sodexo.pe.com.sodexo.domain.interactor.EditProfileInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.BlockCardInteractorImplement;
import sodexo.pe.com.sodexo.domain.interactor.implement.EditProfileInteractorImplement;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.interfaces.BaseParentView;
import sodexo.pe.com.sodexo.presentation.interfaces.BlockCardView;
import sodexo.pe.com.sodexo.presentation.interfaces.EditProfileView;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;
import sodexo.pe.com.sodexo.presentation.model.GetBlockingReasonsInterface;
import sodexo.pe.com.sodexo.presentation.model.GetUserInfoInterface;
import sodexo.pe.com.sodexo.presentation.model.UpdateUserInfoOnterface;
import sodexo.pe.com.sodexo.presentation.presenter.BlockCardPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.EditProfilePresenter;

/**
 * Created by asahel on 16/08/2017.
 */

public class BlockCardPresenterImplement implements BlockCardPresenter {
    private BlockCardView view;
    private BlockCardInteractor blockCardInteractor;

    public BlockCardPresenterImplement(BlockCardView view) {
        IntranetRepository intranetRepository = new IntranetDataRepository(new IntranetDataMapper());
        this.view = view;
        this.blockCardInteractor = new BlockCardInteractorImplement(intranetRepository);
    }

    @Override
    public void blockCard(String cardNumber, String reasonId) {
        view.showLoading();
        blockCardInteractor.blockCard(cardNumber,reasonId, new BaseParentInterface() {
            @Override
            public void onSuccess(String message) {
                view.hideLoading();
                view.onBlockCardSuccess(message);
            }

            @Override
            public void onError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }

    @Override
    public void getBlockingReasons() {
        view.showLoading();
        blockCardInteractor.getBlockingReasons(new GetBlockingReasonsInterface() {
            @Override
            public void onGetBlockingReasonsSuccess(List<BlockingReasonEntity> list) {
                view.hideLoading();
                view.populateReasonsSpinner(list);
            }

            @Override
            public void onGetBlockingReasonsError(String message) {
                view.hideLoading();
                view.showError(message);
            }
        });
    }
}
