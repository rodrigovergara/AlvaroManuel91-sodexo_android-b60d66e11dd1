package sodexo.pe.com.sodexo.data.mapper;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.CommerceEntityData;
import sodexo.pe.com.sodexo.domain.entity.CommerceEntity;

/**
 * Created by RONALD on 10/10/2016.
 */

public class CommerceDataMapper {
    public List<CommerceEntity> transformToCommerceEntityList(List<CommerceEntityData> data) {
        List<CommerceEntity> list = new ArrayList<>();
        for (CommerceEntityData commerceEntitydata : data) {
            CommerceEntity commerceEntity = new CommerceEntity();
            commerceEntity.setId(commerceEntitydata.getAsociateId());
            commerceEntity.setName(commerceEntitydata.getName());
            commerceEntity.setBanner(commerceEntitydata.getBanner());
            commerceEntity.setBusinessName(commerceEntitydata.getBusinessName());
            commerceEntity.setLatitude(commerceEntitydata.getLatitude());
            commerceEntity.setLongitude(commerceEntitydata.getLongitude());
            commerceEntity.setLogo(commerceEntitydata.getLogo());
            commerceEntity.setPhoneNumber(commerceEntitydata.getPhoneNumber());
            commerceEntity.setAddress(commerceEntitydata.getAddress());
            commerceEntity.setAtention(commerceEntitydata.getAtention());
            list.add(commerceEntity);
        }
        return list;
    }
}
