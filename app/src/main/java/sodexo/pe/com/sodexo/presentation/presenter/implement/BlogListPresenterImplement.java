package sodexo.pe.com.sodexo.presentation.presenter.implement;

import java.util.List;

import sodexo.pe.com.sodexo.data.mapper.IntranetDataMapper;
import sodexo.pe.com.sodexo.data.repository.IntranetDataRepository;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.domain.interactor.BlogListInteractor;
import sodexo.pe.com.sodexo.domain.interactor.implement.BlogListInteractorImplement;
import sodexo.pe.com.sodexo.presentation.interfaces.BlogListView;
import sodexo.pe.com.sodexo.presentation.model.GetBlogListInterface;
import sodexo.pe.com.sodexo.presentation.presenter.BlogListPresenter;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class BlogListPresenterImplement implements BlogListPresenter {
    private BlogListView view;
    private BlogListInteractor interactor;
    public BlogListPresenterImplement(BlogListView view) {
        this.view = view;
        this.interactor = new BlogListInteractorImplement(new IntranetDataRepository(new IntranetDataMapper()));
    }

    @Override
    public void getBlogList() {
        view.showLoading();
        interactor.getBlogList(new GetBlogListInterface() {
            @Override
            public void onGetBlogListSuccess(List<BlogEntity> blogEntities) {
                view.hideLoading();
                view.showBlogList(blogEntities);
            }

            @Override
            public void onGetBlogListError(String error) {
                view.hideLoading();
                view.showError(error);
            }
        });
    }
}
