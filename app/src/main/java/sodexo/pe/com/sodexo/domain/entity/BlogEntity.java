package sodexo.pe.com.sodexo.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ronaldvelasquez on 12/12/16.
 */

public class BlogEntity implements Parcelable {
    private int id;
    private String title;
    private String sumilla;
    private String message;
    private String image;
    private String url;
    private String registerDate;
    private boolean isCompanyBlog;

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

    public boolean isCompanyBlog() {
        return isCompanyBlog;
    }

    public void setCompanyBlog(boolean companyBlog) {
        isCompanyBlog = companyBlog;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BlogEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.sumilla);
        dest.writeString(this.message);
        dest.writeString(this.image);
        dest.writeString(this.url);
        dest.writeString(this.registerDate);
        dest.writeByte(this.isCompanyBlog ? (byte) 1 : (byte) 0);
    }

    protected BlogEntity(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.sumilla = in.readString();
        this.message = in.readString();
        this.image = in.readString();
        this.url = in.readString();
        this.registerDate = in.readString();
        this.isCompanyBlog = in.readByte() != 0;
    }

    public static final Creator<BlogEntity> CREATOR = new Creator<BlogEntity>() {
        @Override
        public BlogEntity createFromParcel(Parcel source) {
            return new BlogEntity(source);
        }

        @Override
        public BlogEntity[] newArray(int size) {
            return new BlogEntity[size];
        }
    };
}
