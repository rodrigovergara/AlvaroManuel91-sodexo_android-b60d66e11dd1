package sodexo.pe.com.sodexo.data.mapper;

import java.util.ArrayList;
import java.util.List;

import sodexo.pe.com.sodexo.data.model.PlaceResultData;
import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;

/**
 * Created by RONALD on 18/08/2016.
 */
public class GoogleDataMapper {
    public GoogleDataMapper() {
    }

    public static List<PlaceEntity> transformPlaceResultDataToPlaceEntity(List<PlaceResultData> resultData) {
        List<PlaceEntity> placeEntities = new ArrayList<>();
        for (PlaceResultData placeResultData : resultData) {
            PlaceEntity placeEntity = new PlaceEntity();
            placeEntity.setAddress(placeResultData.getFormatAddress());
            placeEntity.setLatitude(placeResultData.getGeometry().getLocationType().getLatitude());
            placeEntity.setLongitude(placeResultData.getGeometry().getLocationType().getLongitude());
//            placeEntity.setReference(placeResultData.get);
            placeEntities.add(placeEntity);
        }
        return placeEntities;
    }
}
