package sodexo.pe.com.sodexo.presentation.interfaces;

import java.util.List;

import sodexo.pe.com.sodexo.domain.entity.BlogEntity;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public interface BlogListView {
    void showLoading();

    void hideLoading();

    void showError(String error);

    void showBlogList(List<BlogEntity> blogEntities);

    void showBlogDetail(BlogEntity blog);
}
