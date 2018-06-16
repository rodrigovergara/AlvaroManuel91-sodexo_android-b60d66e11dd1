package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ronaldvelasquez on 8/12/16.
 */

public class BlogEntityData {
    @SerializedName("BlogId")
    private int id;
    @SerializedName("Titulo")
    private String title;
    @SerializedName("Sumilla")
    private String sumilla;
    @SerializedName("Mensaje")
    private String message;
    @SerializedName("Imagen")
    private String image;
    @SerializedName("FechaRegistro")
    private String registerDate;
    @SerializedName("EsBlogEmpresa")
    private boolean isCompanyBlog;
    @SerializedName("Url")
    private String url;

    public boolean isCompanyBlog() {
        return isCompanyBlog;
    }

    public void setCompanyBlog(boolean companyBlog) {
        isCompanyBlog = companyBlog;
    }

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

    public String getSumilla() {
        return sumilla;
    }

    public void setSumilla(String sumilla) {
        this.sumilla = sumilla;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
