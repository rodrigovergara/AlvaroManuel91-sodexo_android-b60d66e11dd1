package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ronaldvelasquez on 8/12/16.
 */

public class QuestionEntityData {
    @SerializedName("PreguntaId")
    private int id;
    @SerializedName("Titulo")
    private String title;
    @SerializedName("Descripcion")
    private String description;
    @SerializedName("ListOpcionEncuesta")
    private List<QuestionOptionsEntityData> options;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionOptionsEntityData> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionsEntityData> options) {
        this.options = options;
    }
}
