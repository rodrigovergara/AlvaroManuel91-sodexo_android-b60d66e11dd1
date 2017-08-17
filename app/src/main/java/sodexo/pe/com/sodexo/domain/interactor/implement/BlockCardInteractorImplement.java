package sodexo.pe.com.sodexo.domain.interactor.implement;

import sodexo.pe.com.sodexo.domain.interactor.BlockCardInteractor;
import sodexo.pe.com.sodexo.domain.repository.IntranetRepository;
import sodexo.pe.com.sodexo.presentation.model.BaseParentInterface;

/**
 * Created by asahel on 8/16/17.
 */

public class BlockCardInteractorImplement implements BlockCardInteractor {
    private IntranetRepository intranetRepository;

    public BlockCardInteractorImplement(IntranetRepository intranetRepository) {
        this.intranetRepository = intranetRepository;
    }

    @Override
    public void blockCard(String cardNumber, BaseParentInterface baseParentInterface) {
        intranetRepository.blockCard(cardNumber, baseParentInterface);
    }
}
