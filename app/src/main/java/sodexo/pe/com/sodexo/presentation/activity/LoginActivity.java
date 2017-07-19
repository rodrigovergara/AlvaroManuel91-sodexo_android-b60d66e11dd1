package sodexo.pe.com.sodexo.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.tismart.tsmviews.utils.AlertUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.dialog.RegisterWithCardDialog;
import sodexo.pe.com.sodexo.presentation.dialog.RegisterWithoutCardDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.LoginView;
import sodexo.pe.com.sodexo.presentation.presenter.LoginPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.LoginPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;
    private LoginPresenter presenter;
    private ProgressCustomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenterImplement(this);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

    @OnClick(R.id.btn_register)
    public void register() {
        AlertUtils.showMessageAcceptNegative(this, null, "¿Tienes alguna tarjeta?", "SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RegisterWithCardDialog dialog = new RegisterWithCardDialog();
                dialog.show(LoginActivity.this.getSupportFragmentManager(), RegisterWithCardDialog.class.getName());
                dialogInterface.dismiss();
            }
        }, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RegisterWithoutCardDialog dialog = new RegisterWithoutCardDialog();
                dialog.show(LoginActivity.this.getSupportFragmentManager(), RegisterWithoutCardDialog.class.getName());
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    public void showMessageError(String error) {
        AlertUtil.showMessageAccept(this, "", error, "Aceptar", null);
    }

    @Override
    public void navigateToIntranetOptions() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog = new ProgressCustomDialog();
        }
        dialog.show(getSupportFragmentManager(), ProgressCustomDialog.class.getName());

    }

    @Override
    public void hideLoading() {
        if (dialog != null) {
            dialog.dismissAllowingStateLoss();
        }
    }
}
