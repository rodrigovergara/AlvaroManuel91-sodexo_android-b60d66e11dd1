package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.domain.entity.BlogEntity;
import sodexo.pe.com.sodexo.domain.entity.QuizEntity;
import sodexo.pe.com.sodexo.presentation.adapter.RVQuzListAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.QuizView;
import sodexo.pe.com.sodexo.presentation.presenter.QuizPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.QuizPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

public class QuizFragment extends Fragment implements QuizView {
    @BindView(R.id.rv_quizz)
    RecyclerView rvQuizz;
    private QuizPresenter presenter;
    private ProgressCustomDialog progress;
    private RVQuzListAdapter adapter;
    private MainView view;
    private int currentDataValue = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new QuizPresenterImplement(this);
        if (getArguments() != null) {
            currentDataValue = getArguments().getInt("data_value",0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RVQuzListAdapter(this, getContext());
        rvQuizz.setAdapter(adapter);
        rvQuizz.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.getQuizList();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            view = (MainView) context;
        }
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
        AlertUtil.showMessageAccept(getActivity(), null, error, "ACEPTAR", null);
    }

    @Override
    public void showQuizList(List<QuizEntity> list) {
        adapter.addList(list);
        if(currentDataValue!=0) {
            QuizEntity targetQuiz = null;
            for (QuizEntity tempQuiz : list) {
                if (tempQuiz.getId() == currentDataValue) {
                    targetQuiz = tempQuiz;
                    break;
                }
            }
            if (targetQuiz != null) {
                showQuizDetail(targetQuiz);
            }
            currentDataValue = 0;
        }
    }

    @OnClick(R.id.iv_back)
    public void back() {
        view.openIntranetOption();
    }

    @Override
    public void showQuizDetail(QuizEntity quizEntity) {
        view.showQuizDetail(quizEntity);
    }
}
