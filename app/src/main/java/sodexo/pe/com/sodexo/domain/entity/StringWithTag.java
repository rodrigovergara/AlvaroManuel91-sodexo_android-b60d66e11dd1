package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by asahel on 9/7/17.
 */

public class StringWithTag {
    private String string;
    private Object tag;

    public StringWithTag(String string, Object tag) {
        this.string = string;
        this.tag = tag;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "StringWithTag{" +
                "string='" + string + '\'' +
                ", tag=" + tag +
                '}';
    }
}
