package sodexo.pe.com.sodexo.domain.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RONALD on 05/10/2016.
 */

public class OptionIntranetEntity {
    private String id;
    private String title;
    private String urlImage;
    private String imageBackground;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }
}
