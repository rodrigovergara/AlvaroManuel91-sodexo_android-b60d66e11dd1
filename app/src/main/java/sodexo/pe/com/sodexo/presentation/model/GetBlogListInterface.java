package sodexo.pe.com.sodexo.presentation.model;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlogEntity;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface GetBlogListInterface {
    void onGetBlogListSuccess(List<BlogEntity> blogEntities);

    void onGetBlogListError(String error);
}
