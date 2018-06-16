package sodexo.pe.com.sodexo.data.datasource.rest.interfaces;

import sodexo.pe.com.sodexo.domain.repository.RepositoryCallback;

/**
 * Created by RONALD on 10/10/2016.
 */

public interface CommerceDataStore {
    void getAllCommerces(RepositoryCallback repositoryCallback);
    void getFilterCommerces(String query, RepositoryCallback repositoryCallback);
}
