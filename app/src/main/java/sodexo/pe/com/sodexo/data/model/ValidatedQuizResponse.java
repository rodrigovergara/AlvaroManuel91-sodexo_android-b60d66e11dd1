package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public class ValidatedQuizResponse {
    @SerializedName("Id")
    private int id;
    @SerializedName("Exito")
    private boolean correct;
    @SerializedName("Mensaje")
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
