package sodexo.pe.com.sodexo.data.mapper;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.PromoEntityData;
import sodexo.pe.com.sodexo.domain.entity.PromoEntity;

/**
 * Created by RONALD on 09/10/2016.
 */

public class PromoDataMapper {
    public List<PromoEntity> transformToPromoEntityList(List<PromoEntityData> data){
        List<PromoEntity> promos = new ArrayList<>();
        for (PromoEntityData promoEntityData : data) {
            PromoEntity promoEntity = new PromoEntity();
            promoEntity.setName(promoEntityData.getName());
            promoEntity.setDescription(promoEntityData.getDescription());
            promoEntity.setImageLogo(promoEntityData.getImageLogo());
            promoEntity.setPhoto(promoEntityData.getPhoto());
            promoEntity.setPromoId(promoEntityData.getPromoId());
            promoEntity.setCuponUrl(promoEntityData.getCuponUrl());
            promoEntity.setAddress(promoEntityData.getAddress());
            promoEntity.setAtention(promoEntityData.getAtention());
            promoEntity.setPhoneNumber(promoEntityData.getPhoneNumber());
            promoEntity.setTerms(promoEntityData.getTerms());
            promos.add(promoEntity);
        }
        return promos;
    }
}
