package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.ChangePassworWebView;
import sodexo.pe.com.sodexo.presentation.presenter.ChangePassworsPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.ChangePassworsPresenterImplement;

public class ChangePasswordWebFragment extends Fragment implements ChangePassworWebView {


    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_repeat_new_password)
    EditText etRepeatNewPassword;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private ChangePassworsPresenter presenter;
    private ProgressCustomDialog dialog;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password_web, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new ChangePassworsPresenterImplement(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @OnClick({R.id.btn_clean, R.id.btn_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clean:
                etNewPassword.setText("");
                etPassword.setText("");
                etRepeatNewPassword.setText("");
                break;
            case R.id.btn_save:
                presenter.changePassword(etPassword.getText().toString(), etNewPassword.getText().toString(), etRepeatNewPassword.getText().toString());
                break;
        }
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);;
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }
}
