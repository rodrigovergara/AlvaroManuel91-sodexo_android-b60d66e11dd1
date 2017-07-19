package sodexo.pe.com.sodexo.data.mapper;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.FilterLocalEntityData;
import sodexo.pe.com.sodexo.domain.entity.LocalFilterEntity;

/**
 * Created by RONALD on 11/10/2016.
 */

public class FilterLocalDataMapper {
    public List<LocalFilterEntity> trasnformToLocalFilterEntity(List<FilterLocalEntityData> data) {
        List<LocalFilterEntity> list = new ArrayList<>();
        for (FilterLocalEntityData filter : data) {
            LocalFilterEntity local = new LocalFilterEntity();
            local.setLongitude(filter.getLongitude());
            local.setLatitude(filter.getLatitude());
            local.setName(filter.getName());
            local.setBanner(filter.getBanner2());
            local.setLogo(filter.getLogoRec());
            local.setAddress(filter.getAddress());
            local.setAtention(filter.getAtention());
            local.setPhoneNumber(filter.getPhone());
            list.add(local);
        }
        return list;
    }
}
