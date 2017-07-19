package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RONALD on 18/08/2016.
 */
public class PlacesResponse {
    @SerializedName ("results")
    private List<PlaceResultData> results;

    @SerializedName ("status")
    private String status;

    public List<PlaceResultData> getResults() {
        return results;
    }

    public void setResults(List<PlaceResultData> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
