package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.adapter.RVOptionIntranetAdapter;
import sodexo.pe.com.sodexo.domain.entity.OptionIntranetEntity;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.enums.IntranetOptionEnum;
import sodexo.pe.com.sodexo.presentation.interfaces.IntranetOptionView;
import sodexo.pe.com.sodexo.presentation.interfaces.LogOutInterface;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.OnClickOptions;
import sodexo.pe.com.sodexo.presentation.presenter.IntranetOptionPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.IntranetOptionPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class IntranetOptionFragment extends Fragment implements OnClickOptions, IntranetOptionView, LogOutInterface {

    @BindView(R.id.rv_intranet_options)
    RecyclerView rvIntranetOption;



    private RVOptionIntranetAdapter adapter;
    private MainView mainView;
    private IntranetOptionPresenter presenter;
    private ProgressCustomDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intranet_option, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new IntranetOptionPresenterImplement(this);
        adapter = new RVOptionIntranetAdapter(getContext(), this, this);
        Log.d("Antes","Lograra antes de");
        rvIntranetOption.setAdapter(adapter);
        rvIntranetOption.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        presenter.getIntranetOptions();



    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @Override
    public void clickListener(String id) {
        switch (IntranetOptionEnum.getEnumValue(id)) {
            case VIEW_CREDIT:
                Log.v("INTRANET-","VIEW_CREDIT");
                mainView.openViewCredit();
                break;
            case LAST_MOVEMENTS:
                Log.v("INTRANET-","LAST_MOVEMENTS");
                mainView.openLastMovements();
                break;
            case PROFILE:
                mainView.openEditProfile();
                break;
            case CHANGE_CARD_PASSWORD:
                mainView.openChangePasswordCard();
                break;
            case CHANGE_WEB_PASSWORD:
                mainView.openChangePasswordWeb();
                break;
            case SMS_ALERT:
                mainView.openJoinService();
                break;
            case WISHLIST:
                mainView.openWishtList();
                break;
            case BLOG:
                mainView.openBlogList();
                break;
            case QUIZ:
                mainView.openQuiz();
                break;
            case BLOCK_CARD:
                mainView.openBlockCard();
                break;
            case REPLACE_CARD:
                mainView.openReplaceCard();
                break;
            case MUNDIAL:
                mainView.openMundial("url");
                Log.d("llego aca","aca");
                break;
        }
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressCustomDialog();
        }
        progressDialog.show(getFragmentManager(), ProgressCustomDialog.class.getName());
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtil.showMessageAccept(getActivity(), null, error, "Aceptar", null);
    }

    @Override
    public void showOptions(List<OptionIntranetEntity> list) {

        adapter.addOptions(list);
    }

    @Override
    public void logout() {
        getActivity().finish();
    }
}
