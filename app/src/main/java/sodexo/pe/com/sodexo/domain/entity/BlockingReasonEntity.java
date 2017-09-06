package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by asahel on 9/5/17.
 */

public class BlockingReasonEntity {

    private String id;
    private String description;

    public BlockingReasonEntity(String id, String description) {
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
