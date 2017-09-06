package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asahel on 9/5/17.
 */

public class BlockingReasonEntityData {

    @SerializedName("TIPBLOQ_UBA_ID")
    private String id;

    @SerializedName("TIPBLOQ_UBA_DESC")
    private String description;

    public BlockingReasonEntityData(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
