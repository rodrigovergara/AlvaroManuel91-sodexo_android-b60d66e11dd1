package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
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
import sodexo.pe.com.sodexo.domain.entity.BlockingReasonEntity;
import sodexo.pe.com.sodexo.domain.entity.CardDetailEntity;
import sodexo.pe.com.sodexo.domain.entity.CardEntity;
import sodexo.pe.com.sodexo.presentation.adapter.BlockingReasonAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.NumberCardAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.BlockCardView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.ViewCreditView;
import sodexo.pe.com.sodexo.presentation.presenter.BlockCardPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.ViewCreditPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.BlockCardPresenterImplement;
import sodexo.pe.com.sodexo.presentation.presenter.implement.ViewCreditPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class BlockCardFragment extends Fragment implements BlockCardView {

    @BindView(R.id.sp_cards)
    Spinner spCards;

    @BindView(R.id.sp_reasons)
    Spinner spReasons;

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

    private String cardNumber;
    private String reasonId;

    private ViewCreditPresenter presenter;
    private BlockCardPresenter blockCardPresenter;
    private ProgressCustomDialog progressCustomDialog;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_block_card, container, false);
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
        blockCardPresenter = new BlockCardPresenterImplement(this);
        progressCustomDialog = new ProgressCustomDialog();
        presenter.getNumberCards();
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }

    @OnClick(R.id.btn_block_card)
    public void blockCard() {
        if (cardNumber == null)
            AlertUtil.showAlertDialog(getContext(), "Por favor, seleccione una tarjeta.");
        else if (reasonId == null)
            AlertUtil.showAlertDialog(getContext(), "Por favor, seleccione un motivo.");
        else
            blockCardPresenter.blockCard(cardNumber, reasonId);
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
    public void populateReplacementCards(final List<CardEntity> list) {
        blockCardPresenter.getBlockingReasons();
        NumberCardAdapter numberCardAdapter = new NumberCardAdapter(getContext());
        numberCardAdapter.addCards(list);
        spCards.setAdapter(numberCardAdapter);
        spCards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                  if (i != 0) {
                                                      tvCardNumber.setText(list.get(i - 1).getCardCode());
                                                      presenter.getCardDetail(list.get(i - 1));
                                                      cardNumber = list.get(i - 1).getCardNumber();
                                                  } else {
                                                      cardNumber = null;
                                                  }

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> adapterView) {
                                                  cardNumber = null;
                                              }
                                          }
        );
    }

    @Override
    public void populateReasonsSpinner(final List<BlockingReasonEntity> list) {
        BlockingReasonAdapter blockingReasonAdapter = new BlockingReasonAdapter(list, getContext());
        spReasons.setAdapter(blockingReasonAdapter);
        spReasons.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    btnBlockCard.setEnabled(false);
                    reasonId = null;
                }else{
                    btnBlockCard.setEnabled(true);
                    reasonId = list.get(position-1).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                btnBlockCard.setEnabled(false);
                reasonId = null;
            }
        });
    }

    @Override
    public void showCardDetail(CardDetailEntity cardDetail) {
        llCardDetail.setVisibility(View.VISIBLE);
        //tvDate.setText(cardDetail.getDate());
        tvCredit.setText(cardDetail.getTotal());
        tvService.setText(cardDetail.getMessage());

    }

    @Override
    public void onBlockCardSuccess(String message) {
        //"Su tarjeta ha sido bloqueada satisfactoriamente"
        AlertUtil.showAlertDialog(getContext(), message);
    }
}