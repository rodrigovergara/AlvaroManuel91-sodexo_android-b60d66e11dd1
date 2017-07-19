package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 13/10/2016.
 */

public class OperatorEntity {
    private String id;
    private String description;

    public OperatorEntity(String id, String description) {
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
