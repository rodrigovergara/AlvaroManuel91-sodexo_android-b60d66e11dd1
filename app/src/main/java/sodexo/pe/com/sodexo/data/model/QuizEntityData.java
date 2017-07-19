package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ronaldvelasquez on 8/12/16.
 */

public class QuizEntityData {
    @SerializedName("EncuestaId")
    private int id;
    @SerializedName("Titulo")
    private String title;
    @SerializedName("FechaRegistro")
    private String registerDate;
    @SerializedName("EsEncuestaResuelta")
    private boolean isQuizResolved;
    @SerializedName("EsEncuestaEmpresa")
    private boolean isCompanyQuiz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isQuizResolved() {
        return isQuizResolved;
    }

    public void setQuizResolved(boolean quizResolved) {
        isQuizResolved = quizResolved;
    }

    public boolean isCompanyQuiz() {
        return isCompanyQuiz;
    }

    public void setCompanyQuiz(boolean companyQuiz) {
        isCompanyQuiz = companyQuiz;
    }
}
