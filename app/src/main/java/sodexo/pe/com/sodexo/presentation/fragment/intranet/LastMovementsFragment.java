package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.CardTypeEntity;
import sodexo.pe.com.sodexo.domain.entity.MovementEntity;
import sodexo.pe.com.sodexo.presentation.adapter.CardTypeAdapter;
import sodexo.pe.com.sodexo.presentation.adapter.RVMovementAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.LastMovementsView;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.presenter.LastMovementsPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.LastMovementsPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastMovementsFragment extends Fragment implements LastMovementsView {

    @BindView(R.id.sp_cards)
    Spinner spCard;
    @BindView(R.id.rv_movement)
    RecyclerView rvMovement;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.ll_header_movement)
    LinearLayout llHeaderMovement;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.et_search)
    EditText etSearch;
    private LastMovementsPresenter presenter;
    private ProgressCustomDialog dialog;
    private RVMovementAdapter adapter;
    private MainView mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_last_movements, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
        presenter = new LastMovementsPresenterImplement(this);
        presenter.getTypeCards();
    }

    @Override
    public void showLoadig() {
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
    public void populateCardsType(final List<CardTypeEntity> list) {
        CardTypeAdapter adapter = new CardTypeAdapter(getContext());
        adapter.addCards(list);
        spCard.setAdapter(adapter);
        spCard.setSelection(0);
        spCard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    presenter.getMovements(list.get(i - 1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @OnClick(R.id.iv_back)
    public void back() {
        mainView.openIntranetOption();
    }

    @Override
    public void showMovements(List<MovementEntity> list) {
        llHeaderMovement.setVisibility(View.VISIBLE);
        llSearch.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.VISIBLE);
        rvMovement.setVisibility(View.VISIBLE);

        tvMessage.setText(list.get(0).getMessage());
        adapter = new RVMovementAdapter();
        adapter.addList(list);
        rvMovement.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovement.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        AlertUtil.showMessageAccept(getContext(), "", message, "Aceptar", null);
    }
}
