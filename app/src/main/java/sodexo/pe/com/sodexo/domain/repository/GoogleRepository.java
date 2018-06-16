package sodexo.pe.com.sodexo.domain.repository;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.PlaceEntity;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface GoogleRepository {
    List<PlaceEntity> getAllAddress(String address);
}
