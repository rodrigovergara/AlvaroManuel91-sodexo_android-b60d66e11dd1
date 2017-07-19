package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CellInfoEntity;
import sodexo.pe.com.sodexo.domain.entity.OperatorEntity;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.util.AlertUtil;
import sodexo.pe.com.sodexo.presentation.adapter.OperatorAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.RegisterSmsServiceView;
import sodexo.pe.com.sodexo.presentation.presenter.RegisterSmsServicePresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.RegisterSmsServicePresenterImplement;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterSmsServiceFragment extends Fragment implements RegisterSmsServiceView {


    @BindView(R.id.et_cel_number)
    EditText etCelNumber;
    @BindView(R.id.sp_operator)
    Spinner spOperator;
    @BindView(R.id.rb_state_service_active)
    RadioButton rbStateServiceActive;
    @BindView(R.id.rb_state_service_inactive)
    RadioButton rbStateServiceInactive;
    @BindView(R.id.cb_terms)
    CheckBox cbTerms;
    @BindView(R.id.btn_clean)
    Button btnClean;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rbg_active)
    RadioGroup rbgActive;
    @BindView(R.id.terms_link)
    TextView termsLink;
    private RegisterSmsServicePresenter presenter;
    private List<OperatorEntity> listOperator;
    private ProgressCustomDialog dialog;
    private OperatorEntity operatorEntity;
    private  MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_sms, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new RegisterSmsServicePresenterImplement(this);
        populateOperator();
        presenter.getCellInfo();
    }

    private void populateOperator() {
        listOperator = new ArrayList<>();
        listOperator.add(new OperatorEntity("M", "MOVISTAR"));
        listOperator.add(new OperatorEntity("C", "CLARO"));
        listOperator.add(new OperatorEntity("N", "ENTEL"));
        OperatorAdapter adapter = new OperatorAdapter(getContext());
        adapter.addCards(listOperator);
        spOperator.setAdapter(adapter);
        spOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                operatorEntity = listOperator.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    @Override
    public void showInfo(CellInfoEntity entity) {
        etCelNumber.setText(entity.getCelular());
        spOperator.setSelection(((OperatorAdapter) spOperator.getAdapter()).getPosition(entity.getOperator()));
        if (entity.getRegisterSms().equals("1")) {
            rbStateServiceActive.setChecked(true);
            rbStateServiceInactive.setChecked(false);
        } else {
            rbStateServiceInactive.setChecked(true);
            rbStateServiceActive.setChecked(false);
        }

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("TERMS_PREFERENCES", Context.MODE_PRIVATE);
        boolean accepted = sharedPreferences.getBoolean("accept_terms",false);
        cbTerms.setChecked(accepted);
    }
    @OnClick(R.id.btn_save)
    public void save(){
        if(cbTerms.isChecked()) {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("TERMS_PREFERENCES", Context.MODE_PRIVATE);
            sharedPreferences.edit().putBoolean("accept_terms",cbTerms.isChecked()).apply();
            presenter.updateCellInfo(etCelNumber.getText().toString(), operatorEntity, rbgActive.getCheckedRadioButtonId() == rbStateServiceActive.getId() ? "1" : "0");
        } else
            Toast.makeText(getContext(), "Debes aceptar las condiciones", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.terms_link)
    public void followLink(){
        AlertUtil.showMessageAccept(getContext(), "Condiciones de uso", getString(R.string.terms_and_conditions), "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cbTerms.setChecked(true);
            }
        });
        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        //startActivity(browserIntent);
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }
}
