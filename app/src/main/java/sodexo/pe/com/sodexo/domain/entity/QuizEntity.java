package sodexo.pe.com.sodexo.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ronaldvelasquez on 8/12/16.
 */

public class QuizEntity implements Parcelable {
    private int id;
    private String title;
    private String registerDate;
    private boolean isQuizResolved;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.registerDate);
        dest.writeByte(this.isQuizResolved ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isCompanyQuiz ? (byte) 1 : (byte) 0);
    }

    public QuizEntity() {
    }

    protected QuizEntity(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.registerDate = in.readString();
        this.isQuizResolved = in.readByte() != 0;
        this.isCompanyQuiz = in.readByte() != 0;
    }

    public static final Parcelable.Creator<QuizEntity> CREATOR = new Parcelable.Creator<QuizEntity>() {
        @Override
        public QuizEntity createFromParcel(Parcel source) {
            return new QuizEntity(source);
        }

        @Override
        public QuizEntity[] newArray(int size) {
            return new QuizEntity[size];
        }
    };
}
