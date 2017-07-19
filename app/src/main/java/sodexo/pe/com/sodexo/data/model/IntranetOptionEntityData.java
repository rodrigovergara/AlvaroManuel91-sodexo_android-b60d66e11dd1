package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ronaldvelasquez on 7/12/16.
 */

public class IntranetOptionEntityData {
    @SerializedName("ColorFondo")
    private String background;
    @SerializedName("Titulo")
    private String title;
    @SerializedName("ImagenFondo")
    private String image;
    @SerializedName("CodigoOpcion")
    private String optionCode;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOptionCode() {
        return optionCode;
    }

    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }
}
