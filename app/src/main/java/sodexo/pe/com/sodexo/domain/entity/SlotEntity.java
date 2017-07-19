package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 14/10/2016.
 */

public class SlotEntity {
    private int id;
    private String description;

    public SlotEntity(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
