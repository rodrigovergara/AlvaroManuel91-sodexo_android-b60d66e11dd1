package sodexo.pe.com.sodexo.domain.entity;

/**
 * Created by RONALD on 04/10/2016.
 */

public class OptionEntity {
    private int id;
    private int idImage;

    public OptionEntity(int id, int idImage) {
        this.id = id;
        this.idImage = idImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
