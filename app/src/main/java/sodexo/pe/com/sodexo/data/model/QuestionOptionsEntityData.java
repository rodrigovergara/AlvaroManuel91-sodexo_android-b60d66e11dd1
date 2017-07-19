package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ronaldvelasquez on 9/12/16.
 */

public class QuestionOptionsEntityData {
    @SerializedName("OpcionId")
    private int id;
    @SerializedName("Titulo")
    private String title;
    @SerializedName("TieneCampoLibre")
    private boolean isFreeField;

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

    public boolean isFreeField() {
        return isFreeField;
    }

    public void setFreeField(boolean freeField) {
        isFreeField = freeField;
    }
}
