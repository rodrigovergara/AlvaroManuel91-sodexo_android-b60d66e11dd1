package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.adapter.NumberCardAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.ViewCreditView;
import sodexo.pe.com.sodexo.presentation.presenter.ViewCreditPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.ViewCreditPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class ReplaceCardFragment extends Fragment implements ViewCreditView {

    @BindView(R.id.sp_cards)
    Spinner spinner;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    /*
    @BindView(R.id.tv_date)
    TextView tvDate;
    */
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_service)
    TextView tvService;
    @BindView(R.id.tv_credit)
    TextView tvCredit;
    @BindView(R.id.ll_card_detail)
    LinearLayout llCardDetail;

    @BindView(R.id.btn_block_card)
    Button btnBlockCard;

    private ViewCreditPresenter presenter;
    private ProgressCustomDialog progressCustomDialog;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_replace_card, container, false);
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
        presenter = new ViewCreditPresenterImplement(this);
        progressCustomDialog = new ProgressCustomDialog();
        presenter.getNumberCards();
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }

    @OnClick(R.id.btn_block_card)
    public void blockCard() {
        AlertUtil.showMessageAccept(getContext(), "", "Su tarjeta ha sido bloqueada satisfactoriamente", "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainView.openPaymentInformation();
            }
        });
    }

    @Override
    public void showLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
        }
    }

    @Override
    public void hideLoading() {
        if (progressCustomDialog != null) {
            progressCustomDialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }

    @Override
    public void populateSpinner(final List<CardEntity> list) {
        NumberCardAdapter numberCardAdapter = new NumberCardAdapter(getContext());
        numberCardAdapter.addCards(list);
        spinner.setAdapter(numberCardAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  if (i != 0) {
                                                      tvCardNumber.setText(list.get(i - 1).getCardCode());
                                                      presenter.getCardDetail(list.get(i - 1));
                                                  }

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {

                                              }
                                          }
        );
    }

    @Override
    public void showCardDetail(CardDetailEntity cardDetail) {
        llCardDetail.setVisibility(View.VISIBLE);
        //tvDate.setText(cardDetail.getDate());
        btnBlockCard.setEnabled(true);
        tvCredit.setText(cardDetail.getTotal());
        tvService.setText(cardDetail.getMessage());

    }
}
