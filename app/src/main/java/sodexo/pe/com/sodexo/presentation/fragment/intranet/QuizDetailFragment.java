package sodexo.pe.com.sodexo.presentation.fragment.intranet;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.data.model.QuestionEntityData;
import sodexo.pe.com.sodexo.data.model.QuestionOptionsEntityData;
import sodexo.pe.com.sodexo.data.model.QuizDetailEntityData;
import sodexo.pe.com.sodexo.data.model.QuizResponseEntityData;
import sodexo.pe.com.sodexo.domain.entity.OptionEntity;
import sodexo.pe.com.sodexo.presentation.adapter.SpinnerOptionAdapter;
import sodexo.pe.com.sodexo.presentation.dialog.ProgressCustomDialog;
import sodexo.pe.com.sodexo.presentation.interfaces.MainView;
import sodexo.pe.com.sodexo.presentation.interfaces.QuizDetailView;
import sodexo.pe.com.sodexo.presentation.presenter.QuizDetailPresenter;
import sodexo.pe.com.sodexo.presentation.presenter.implement.QuizDetailPresenterImplement;
import sodexo.pe.com.sodexo.util.AlertUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizDetailFragment extends Fragment implements QuizDetailView {

    public static final String QUIZ_ID = "quiz_id";
    @BindView(R.id.ll_quiz_detail)
    LinearLayout llQuizDetail;
    @BindView(R.id.title)
    TextView tvTitle;
    private int quizId;
    private ProgressCustomDialog progress;
    private QuizDetailPresenter presenter;
    private MainView view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            quizId = getArguments().getInt(QUIZ_ID);
        }
        presenter = new QuizDetailPresenterImplement(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainView) {
            view = (MainView) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (quizId != 0)
            presenter.getQuestions(quizId);
    }

    @Override
    public void showLoading() {
        if (progress == null) {
            progress = new ProgressCustomDialog();
        }
        progress.show(getActivity().getSupportFragmentManager(), ProgressCustomDialog.class.getName());
    }

    @Override
    public void hideloading() {
        if (progress != null) {
            progress.dismiss();
        }
    }

    @Override
    public void showError(String error) {
        AlertUtil.showMessageAccept(getContext(), null, error, "ACEPTAR", null);
    }

    @Override
    public void showQuestions(QuizDetailEntityData quizDetail) {
        tvTitle.setText(quizDetail.getQuiz().getTitle());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        for (final QuestionEntityData question : quizDetail.getQuestions()) {
            View questionView = inflater.inflate(R.layout.item_question, null);
            LinearLayout llItemQuestion = (LinearLayout) questionView.findViewById(R.id.ll_item_question);
            llItemQuestion.setTag(question);
            TextView tvQuestion = (TextView) questionView.findViewById(R.id.tv_question);
            tvQuestion.setText(question.getTitle());
            LinearLayout llQuestionOption = (LinearLayout) questionView.findViewById(R.id.ll_question_option);
            if (question.getOptions().size() == 1) {
                View optionSingleView = inflater.inflate(R.layout.item_question_option, null);
                LinearLayout linearLayoutSingle = (LinearLayout) optionSingleView.findViewById(R.id.ll_option_single);
                linearLayoutSingle.setTag(question.getOptions().get(0));
                llQuestionOption.addView(optionSingleView);
            } else {
                SpinnerOptionAdapter adapter = new SpinnerOptionAdapter(getContext());
                View optionView = inflater.inflate(R.layout.item_option_multiple, null);
                LinearLayout linearLayoutMultitple = (LinearLayout) optionView.findViewById(R.id.ll_option_multiple);
                Spinner spinner = (Spinner) optionView.findViewById(R.id.sp_options);
                final EditText etOption = (EditText) optionView.findViewById(R.id.et_response);
                adapter.addOptions(question.getOptions());
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        etOption.setVisibility(question.getOptions().get(i).isFreeField() ? View.VISIBLE : View.GONE);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                llQuestionOption.addView(optionView);
            }
            llQuizDetail.addView(questionView);
        }

    }

    @OnClick(R.id.btn_register_quiz)
    public void onClick() {
        if (quizId != 0) {
            List<QuizResponseEntityData> list = new ArrayList<>();
            for (int i = 0; i < llQuizDetail.getChildCount(); i++) {
                LinearLayout llquestion = (LinearLayout) llQuizDetail.getChildAt(i);
                LinearLayout llOptions = (LinearLayout) llquestion.getChildAt(1);
                int numberOption = ((QuestionEntityData)llquestion.getTag()).getOptions().size();
                if (numberOption == 1) {
                    LinearLayout llSingle = (LinearLayout) llOptions.getChildAt(0);
                    EditText etAingle = (EditText) llSingle.getChildAt(0);
                    QuestionOptionsEntityData option = (QuestionOptionsEntityData) llSingle.getTag();
                    QuizResponseEntityData quizResponse = new QuizResponseEntityData();
                    quizResponse.setOptionId(option.getId());
                    quizResponse.setResponse(etAingle.getText().toString().trim());
                    list.add(quizResponse);
                } else {
                    LinearLayout llMultile = (LinearLayout) llOptions.getChildAt(0);
                    Spinner spinner = (Spinner) llMultile.getChildAt(0);
                    SpinnerOptionAdapter spinnerOptionAdapter = (SpinnerOptionAdapter)spinner.getAdapter();
                    EditText editText = (EditText) llMultile.getChildAt(1);
                    QuestionOptionsEntityData questionOptionsEntityData = spinnerOptionAdapter.getItem(spinner.getSelectedItemPosition());
                    QuizResponseEntityData quizResponse = new QuizResponseEntityData();
                    quizResponse.setOptionId(questionOptionsEntityData.getId());
                    if (questionOptionsEntityData.isFreeField()) {
                        quizResponse.setResponse(editText.getText().toString().trim());
                    }
                    list.add(quizResponse);
                }
            }
            presenter.sendResponse(quizId, list);
        }


    }

    @Override
    public void showSuccess() {
        Toast.makeText(getContext(), "Se respondio la encuesta correctamente", Toast.LENGTH_SHORT).show();
        view.openQuiz();
    }

}
