package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 07/10/2016.
 */

public interface PromoDataStore {
    void getAllPromos(RepositoryCallback repositoryCallback);

    void getFilteredPromos(String query, RepositoryCallback repositoryCallback);
}
