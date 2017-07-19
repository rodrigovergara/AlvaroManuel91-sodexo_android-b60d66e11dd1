package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ronaldvelasquez on 14/12/16.
 */

public class QuizResponseEntityData implements Serializable {
    @SerializedName("OpcionId")
    private int optionId;
    @SerializedName("TextCampoLibre")
    private String response;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
