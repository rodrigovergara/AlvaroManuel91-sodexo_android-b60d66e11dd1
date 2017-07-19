package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.SodexoApplication;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.LoginView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.LoginPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.LoginPresenterImplement;

public class LoginIntranetFragment extends Fragment implements LoginView {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    private MainView mainView;
    private LoginPresenter presenter;
    private ProgressCustomDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intranet, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new LoginPresenterImplement(this);
        if (PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getBoolean(SodexoApplication.SAVE, false)) {
            etEmail.setText(PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString("usuario", ""));
            etPassword.setText(PreferenceManager.getDefaultSharedPreferences(SodexoApplication.context).getString("password", ""));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @OnClick(R.id.btn_download_coupon_promo)
    public void login() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @Override
    public void showMessageError(String error) {
        AlertUtil.showMessageAccept(getContext(), "", error, "Aceptar", null);
    }

    @Override
    public void navigateToIntranetOptions() {
        mainView.openIntranetOption();
    }

    @Override
    public void showLoading() {
        if (dialog != null) {
            dialog.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismissAllowingStateLoss();
        }
    }
}
