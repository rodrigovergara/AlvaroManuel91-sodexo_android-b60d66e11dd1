package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tismart.tsmviews.utils.AlertUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.presentation.adapter.RVBlogListAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.BlogListView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.BlogListPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.BlogListPresenterImplement;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogListFragment extends Fragment implements BlogListView {

    @BindView(R.id.rv_blog)
    RecyclerView rvBlog;

    private BlogListPresenter presenter;
    private ProgressCustomDialog progress;
    private RVBlogListAdapter adapter;
    private MainView view;
    private int currentDataValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentDataValue = getArguments().getInt("data_value",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            view = (MainView) context;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RVBlogListAdapter(getActivity(), this);
        rvBlog.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvBlog.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new BlogListPresenterImplement(this);
        presenter.getBlogList();
    }

    @Override
    public void showLoading() {
        if (progress == null) {
            progress = new ProgressCustomDialog();
        }
        progress.show(getFragmentManager(), ProgressCustomDialog.class.getName());
    }

    @Override
    public void hideLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtils.showMessageAccept(getActivity(), null, error, "ACEPTAR", null);
    }

    @Override
    public void showBlogList(List<BlogEntity> blogEntities) {
        adapter.addBlogList(blogEntities);
        if(currentDataValue!=0) {
            BlogEntity targetBlog = null;
            for (BlogEntity tempBlog : blogEntities) {
                if (tempBlog.getId() == currentDataValue) {
                    targetBlog = tempBlog;
                    break;
                }
            }
            if (targetBlog != null) {
                showBlogDetail(targetBlog);
            }
            currentDataValue = 0;
        }
    }

    @Override
    public void showBlogDetail(BlogEntity blog) {
        view.openBlogDetail(blog);
    }

    @OnClick(R.id.iv_back)
    public void back() {
        view.openIntranetOption();
    }
}
