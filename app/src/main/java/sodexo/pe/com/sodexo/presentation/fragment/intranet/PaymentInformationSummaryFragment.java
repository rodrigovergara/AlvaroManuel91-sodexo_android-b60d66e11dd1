package sodexo.pe.com.sodexo.presentation.fragment.intranet;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.ReplacementCardEntity;
import sodexo.pe.com.sodexo.domain.entity.ReplenishmentAmountEntity;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.PaymentInformationSummaryView;
import sodexo.pe.com.sodexo.presentation.presenter.PaymentInformationSummaryPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.PaymentInformationSummaryPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class PaymentInformationSummaryFragment extends Fragment implements PaymentInformationSummaryView {

    public static final String REPLACEMENT_CARD = "replacement_card";

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_replacement_cost)
    TextView tvReplacementCost;

    @BindView(R.id.tv_shipping_cost)
    TextView tvShippingCost;

    private PaymentInformationSummaryPresenter paymentInformationSummaryPresenter;
    private ProgressCustomDialog progressCustomDialog;
    private MainView mainView;
    private ReplacementCardEntity replacementCardEntity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_information_summary, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            replacementCardEntity = getArguments().getParcelable(REPLACEMENT_CARD);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        paymentInformationSummaryPresenter = new PaymentInformationSummaryPresenterImplement(this);
        progressCustomDialog = new ProgressCustomDialog();
        paymentInformationSummaryPresenter.getReplenishmentAmount(
                replacementCardEntity.getCardNumber(),
                replacementCardEntity.getDepartmentId() + "-" +
                        replacementCardEntity.getProvinceId() + "-" +
                        replacementCardEntity.getDistrictId());
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
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }

    @OnClick(R.id.btn_request_replacement)
    public void requestCardReplacement() {
        paymentInformationSummaryPresenter.replaceCard(replacementCardEntity);
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
    public void onGetReplenishmentAmount(ReplenishmentAmountEntity replenishmentAmountEntity) {
        tvReplacementCost.setText("S/. "+replenishmentAmountEntity.getAmount() + "");
    }

    @Override
    public void onReplaceCardSuccess(String message) {
        showError(message);
        mainView.openIntranetOption();
    }
}

