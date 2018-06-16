package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ronaldvelasquez on 8/12/16.
 */

public class QuizDetailEntityData {
    @SerializedName("Encuesta")
    private QuizEntityData quiz;

    @SerializedName("ListPreguntaEncuesta")
    private List<QuestionEntityData> questions;

    public QuizEntityData getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntityData quiz) {
        this.quiz = quiz;
    }

    public List<QuestionEntityData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntityData> questions) {
        this.questions = questions;
    }
}
