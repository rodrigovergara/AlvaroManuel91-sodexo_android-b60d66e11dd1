package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlogDetailFragment extends Fragment {

    public static final String BLOG = "blog";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.sumilla)
    TextView sumilla;
    @BindView(R.id.description)
    TextView description;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.business)
    TextView business;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.webview)
    WebView webview;
    private BlogEntity blogEntity;
    private MainView view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            blogEntity = (BlogEntity) getArguments().getParcelable(BLOG);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            view = (MainView) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (blogEntity != null) {
            title.setText(blogEntity.getTitle());
            sumilla.setText(blogEntity.getSumilla());
            description.setText(blogEntity.getMessage());
            date.setText(blogEntity.getRegisterDate());
            Glide.with(getActivity()).load(blogEntity.getImage()).into(imageView);
            business.setText(blogEntity.isCompanyBlog() ? "Empresarial" : "No empresarial");


            webview.loadUrl(blogEntity.getUrl());
            webview.getSettings().setJavaScriptEnabled(true);
            webview.setWebViewClient(new WebViewClient());
            /*webview.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView view, int progress) {
                    // Activities and WebViews measure progress with different scales.
                    // The progress meter will automatically disappear when we reach 100%
                    BlogDetailFragment.this.getActivity().setProgress(progress * 1000);
                }
            });*/
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        view.openBlogList();
    }
}
