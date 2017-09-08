package sodexo.pe.com.sodexo.data.mapper;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.BlockingReasonEntityData;
import sodexo.pe.com.sodexo.data.model.BlogEntityData;
import sodexo.pe.com.sodexo.data.model.CardDetailEntityData;
import sodexo.pe.com.sodexo.data.model.CardEntityData;
import sodexo.pe.com.sodexo.data.model.CardTypeEntitiyData;
import sodexo.pe.com.sodexo.data.model.CellInfoEntityData;
import sodexo.pe.com.sodexo.data.model.IntranetOptionEntityData;
import sodexo.pe.com.sodexo.data.model.LastMovementsResponse;
import sodexo.pe.com.sodexo.data.model.QuizEntityData;
import sodexo.pe.com.sodexo.data.model.ShippingAddressData;
import sodexo.pe.com.sodexo.data.model.UserEntityData;
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.entity.CellInfoEntity;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;
import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.domain.entity.ShippingAddressEntity;
import sodexo.pe.com.sodexo.domain.entity.UserEntity;

/**
 * Created by RONALD on 12/10/2016.
 */

public class IntranetDataMapper {
    public List<CardEntity> trasnformToCardsEntity(List<CardEntityData> object) {
        List<CardEntity> cardEntities = new ArrayList<>();
        for (CardEntityData cardData : object) {
            CardEntity entity = new CardEntity();
            entity.setCardCode(cardData.getCardCode());
            entity.setCardNumber(cardData.getCardNumber());
            cardEntities.add(entity);
        }
        return cardEntities;
    }

    public CardDetailEntity trasnformToCardsDetailEntity(CardDetailEntityData cardDetail) {
        CardDetailEntity cardDetailEntity = new CardDetailEntity();
        cardDetailEntity.setDate(cardDetail.getDate());
        cardDetailEntity.setMessage(cardDetail.getMessage());
        cardDetailEntity.setTotal(cardDetail.getTotal());
        return cardDetailEntity;
    }

    public List<CardTypeEntity> trasnformToCardTypeEntity(List<CardTypeEntitiyData> object) {
        List<CardTypeEntity> cardEntities = new ArrayList<>();
        for (CardTypeEntitiyData cardData : object) {
            CardTypeEntity entity = new CardTypeEntity();
            entity.setCardId(cardData.getId());
            entity.setCardDescription(cardData.getDescription());
            cardEntities.add(entity);
        }
        return cardEntities;
    }

    public List<MovementEntity> trasnformToMovementEntity(LastMovementsResponse lastMovementsResponse) {
        List<MovementEntity> movementEntities = new ArrayList<>();
        String message = lastMovementsResponse.getMessage();
        for (LastMovementsResponse.MovementEntityData data : lastMovementsResponse.getData()) {
            MovementEntity entity = new MovementEntity();
            entity.setMessage(message);
            entity.setDate(data.getDate());
            entity.setCardNumber(data.getCardNumber());
            entity.setMount(data.getMount());
            entity.setOperation(data.getOperation());
            movementEntities.add(entity);
        }
        return movementEntities;
    }

    public UserEntity transformToUserEntity(UserEntityData data) {
        UserEntity userEntity = new UserEntity();
        userEntity.setAddress(data.getAddress());
        userEntity.setBusinessName(data.getBusinessName());
        userEntity.setCargo(data.getCargo());
        userEntity.setCellPhone(data.getCellPhone());
        userEntity.setDepartment(data.getDepartment());
        userEntity.setDistrict(data.getDistrict());
        userEntity.setDni(data.getDni());
        userEntity.setEmail(data.getEmail());
        userEntity.setName(data.getName());
        userEntity.setLastName(data.getLastName());
        userEntity.setMiddleName(data.getMiddleName());
        userEntity.setPhone(data.getPhone());
        userEntity.setSex(data.getSex());
        return userEntity;
    }

    public CellInfoEntity transformToCellInfoEntity(CellInfoEntityData object) {
        CellInfoEntity cellInfoEntity = new CellInfoEntity();
        cellInfoEntity.setCelular(object.getCelular());
        cellInfoEntity.setOperator(object.getOperator());
        cellInfoEntity.setRegisterSms(object.getRegisterSms());
        return cellInfoEntity;
    }

    public List<OptionIntranetEntity> transformToOptionEntity(List<IntranetOptionEntityData> object) {
        List<OptionIntranetEntity> list = new ArrayList<>();
        for (IntranetOptionEntityData option : object) {
            OptionIntranetEntity entity = new OptionIntranetEntity();
            entity.setId(option.getOptionCode());
            entity.setTitle(option.getTitle());
            entity.setImageBackground(option.getBackground());
            entity.setUrlImage(option.getImage());
            list.add(entity);
        }
        return list;
    }

    public List<BlogEntity> transformToBlogEntity(List<BlogEntityData> object) {
        List<BlogEntity> list = new ArrayList<>();

        for (BlogEntityData blog : object) {
            BlogEntity entity = new BlogEntity();
            entity.setId(blog.getId());
            entity.setTitle(blog.getTitle());
            entity.setUrl(blog.getUrl());
            entity.setImage(blog.getImage());
            entity.setCompanyBlog(blog.isCompanyBlog());
            entity.setMessage(blog.getMessage());
            entity.setRegisterDate(blog.getRegisterDate());
            entity.setSumilla(blog.getSumilla());
            list.add(entity);
        }
        return list;
    }

    public List<QuizEntity> transformToQuizEntity(List<QuizEntityData> object) {
        List<QuizEntity> list = new ArrayList<>();
        for (QuizEntityData quiz : object) {
            QuizEntity entity = new QuizEntity();
            entity.setId(quiz.getId());
            entity.setTitle(quiz.getTitle());
            entity.setRegisterDate(quiz.getRegisterDate());
            entity.setCompanyQuiz(quiz.isCompanyQuiz());
            entity.setQuizResolved(quiz.isQuizResolved());
            list.add(entity);
        }
        return list;
    }

    public List<BlockingReasonEntity> trasnformToBlockingReasonsEntity(List<BlockingReasonEntityData> object) {
        List<BlockingReasonEntity> blockingReasonEntities = new ArrayList<>();
        for (BlockingReasonEntityData blockingReasonEntityData : object) {
            blockingReasonEntities.add(new BlockingReasonEntity(blockingReasonEntityData.getId(), blockingReasonEntityData.getDescription()));
        }
        return blockingReasonEntities;
    }

    public List<ShippingAddressEntity> trasnformToShippingAddressEntity(List<ShippingAddressData> object) {
        List<ShippingAddressEntity> shippingAddressEntities = new ArrayList<>();
        for (ShippingAddressData shippingAddressData : object) {
            shippingAddressEntities.add(new ShippingAddressEntity(shippingAddressData.getAddress(), shippingAddressData.getDepartment(), shippingAddressData.getDepartmentId(), shippingAddressData.getProvince(), shippingAddressData.getProvinceId(), shippingAddressData.getDistrict(), shippingAddressData.getDistrictId()));
        }
        return shippingAddressEntities;
    }
}
