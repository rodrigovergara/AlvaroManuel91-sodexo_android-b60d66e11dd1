package sodexo.pe.com.sodexo.presentation.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.interfaces.RegisterWithCardView;
import sodexo.pe.com.sodexo.presentation.presenter.RegisterPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.RegisterPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class RegisterWithCardDialog extends DialogFragment implements RegisterWithCardView {

    @BindView(R.id.et_dni)
    EditText etDni;
    @BindView(R.id.et_card)
    EditText etCard;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.et_email)
    EditText etEmail;

    private RegisterPresenter presenter;
    private ProgressCustomDialog progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterPresenterImplement(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_register_with_card, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return dialog;
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        presenter.registerWithCard(etDni.getText().toString(),
                etCard.getText().toString(),
                etPassword.getText().toString(),
                etConfirmPassword.getText().toString(),
                etEmail.getText().toString());

    }

    @Override
    public void showLoading() {
        if (progress == null) {
            progress = new ProgressCustomDialog();
        }
        progress.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
    }

    @Override
    public void hideLoading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtil.showMessageAccept(getActivity(), null, error, "ACEPTAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        close();
                        dialogInterface.dismiss();
                    }
                };
            }
        });
    }

    @Override
    public void showSuccessRegister() {
        AlertUtil.showMessageAccept(getActivity(), null, "Se ha creado su cuenta correctamente", "ACEPTAR", null);
    }

    private void close(){
        dismissAllowingStateLoss();
    }
}
