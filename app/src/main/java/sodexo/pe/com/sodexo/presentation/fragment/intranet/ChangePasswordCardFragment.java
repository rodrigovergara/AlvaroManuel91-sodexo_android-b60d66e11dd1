package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.adapter.NumberCardAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.ChangePasswordCardView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.ChangePasswordCardPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.ChangePasswordCardPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordCardFragment extends Fragment implements ChangePasswordCardView {
    @BindView(R.id.tv_password)
    EditText tvPassword;
    @BindView(R.id.sp_cards)
    Spinner spCards;
    @BindViews({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_10})
    List<Button> buttons;
    @BindViews({R.id.btn_1_new, R.id.btn_2_new, R.id.btn_3_new, R.id.btn_4_new, R.id.btn_5_new, R.id.btn_6_new, R.id.btn_7_new, R.id.btn_8_new, R.id.btn_9_new, R.id.btn_10_new})
    List<Button> buttonsNew;
    @BindViews({R.id.btn_1_repeat, R.id.btn_2_repeat, R.id.btn_3_repeat, R.id.btn_4_repeat, R.id.btn_5_repeat, R.id.btn_6_repeat, R.id.btn_7_repeat, R.id.btn_8_repeat, R.id.btn_9_repeat, R.id.btn_10_repeat})
    List<Button> buttonsRepeat;
    @BindView(R.id.ll_keyboard_pass)
    LinearLayout llKeyboardPass;
    @BindView(R.id.tv_password_new)
    EditText tvPassNew;
    @BindView(R.id.tv_password_repeat)
    EditText tvPassRepeat;
    @BindView(R.id.ll_new_pass)
    LinearLayout llNewpass;
    @BindView(R.id.ll_validate)
    LinearLayout llValidate;
    @BindView(R.id.tv_validate)
    TextView tvValidate;
    @BindView(R.id.btn_ok)
    Button btnOk;
    @BindView(R.id.et_validate)
    EditText etValidate;
    private ChangePasswordCardPresenter presenter;
    private ProgressCustomDialog dialog;
    private CardEntity cardEntity;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password_card, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 4) {
                    llKeyboardPass.setVisibility(View.GONE);
                    llNewpass.setVisibility(View.VISIBLE);
                    setRadomNumberInButton(buttonsNew);
                    setRadomNumberInButton(buttonsRepeat);
                    llValidate.setVisibility(View.VISIBLE);
                    tvValidate.setText(randomString(7));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = new ProgressCustomDialog();
        presenter = new ChangePasswordCardPresenterImplement(this);
        presenter.getNumberCards();
    }

    private void setRadomNumberInButton(List<Button> buttons) {
//        Random rng = new Random(); // Ideally just create one instance globally
//        Set<Integer> generated = new LinkedHashSet<Integer>();
//        List<Integer> orderNumber = new ArrayList<>();
//        while (generated.size() < 10) {
//            Integer next = rng.nextInt(10);
//            orderNumber.add(next);
//            generated.add(next);
//        }
        Integer[] integer = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Collections.shuffle(Arrays.asList(integer));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText(String.valueOf(integer[i]));
            buttons.get(i).setTag(integer[i]);
        }
    }


    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_10, R.id.btn_clean, R.id.iv_back})
    public void onClickPass(View view) {
        switch (view.getId()) {
            case R.id.btn_clean:
                tvPassword.setText("");
                break;
            case R.id.iv_back:
                mainView.openIntranetOption();
                break;
            default:
                if (tvPassword.getText().toString().length() < 4) {
                    tvPassword.setText(tvPassword.getText().toString() + view.getTag() + "");
                }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            mainView = (MainView) context;
        }
    }

    @OnClick({R.id.btn_1_new, R.id.btn_2_new, R.id.btn_3_new, R.id.btn_4_new, R.id.btn_5_new, R.id.btn_6_new, R.id.btn_7_new, R.id.btn_8_new, R.id.btn_9_new, R.id.btn_10_new, R.id.btn_clean_new, R.id.iv_back})
    public void onClickNew(View view) {
        switch (view.getId()) {
            case R.id.btn_clean_new:
                tvPassNew.setText("");
                break;
            default:
                if (tvPassNew.getText().toString().length() < 4) {
                    tvPassNew.setText(tvPassNew.getText().toString() + view.getTag() + "");
                }
        }
    }

    @OnClick({R.id.btn_1_repeat, R.id.btn_2_repeat, R.id.btn_3_repeat, R.id.btn_4_repeat, R.id.btn_5_repeat, R.id.btn_6_repeat, R.id.btn_7_repeat, R.id.btn_8_repeat, R.id.btn_9_repeat, R.id.btn_10_repeat, R.id.btn_clean_repeat})
    public void onClickRepeat(View view) {
        switch (view.getId()) {
            case R.id.btn_clean_repeat:
                tvPassRepeat.setText("");
                break;
            default:
                if (tvPassRepeat.getText().toString().length() < 4) {
                    tvPassRepeat.setText(tvPassRepeat.getText().toString() + view.getTag() + "");
                }
        }
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
    public void populateSpinner(final List<CardEntity> list) {
        NumberCardAdapter cardAdapter = new NumberCardAdapter(getContext());
        cardAdapter.addCards(list);
        spCards.setAdapter(cardAdapter);
        spCards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    cardEntity = list.get(i - 1);
                    showKeyboardPassword();
                }
                else {
                    llKeyboardPass.setVisibility(View.GONE);
                }
                llValidate.setVisibility(View.GONE);
                llNewpass.setVisibility(View.GONE);
                tvPassword.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showKeyboardPassword() {
        llKeyboardPass.setVisibility(View.VISIBLE);
        setRadomNumberInButton(buttons);
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    public static String randomString(int length) {
        String CHARACTER_SET = "0123456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(CHARACTER_SET.charAt(rnd.nextInt(CHARACTER_SET.length())));
        }
        return builder.toString();
    }

    @OnClick(R.id.btn_ok)
    public void correct() {
        if (tvValidate.getText().toString().equals(etValidate.getText().toString())) {
            if (tvPassNew.getText().toString().equals(tvPassRepeat.getText().toString())) {
                presenter.changePassword(cardEntity, tvPassword.getText().toString(), tvPassNew.getText().toString(), tvPassRepeat.getText().toString());

            } else {
                showError("Las claves nuevas deben de coincidir.");
            }
        } else {
            showError("El texto de validación no coincide. Por favor escriba el texto correctamente.");
        }
    }
}
