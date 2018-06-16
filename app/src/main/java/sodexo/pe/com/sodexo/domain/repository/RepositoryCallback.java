package sodexo.pe.com.sodexo.domain.repository;

/**
 * Created by emedinaa on 16/04/16.
 */
public interface RepositoryCallback {

    void onError(Object object);

    void onSuccess(Object object);
}
