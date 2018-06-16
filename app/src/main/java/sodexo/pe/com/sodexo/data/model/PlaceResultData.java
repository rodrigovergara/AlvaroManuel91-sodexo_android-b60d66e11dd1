package sodexo.pe.com.sodexo.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by RONALD on 18/08/2016.
 */
public class PlaceResultData {
    @SerializedName ("address_components")
    private List<AddressComponentData> addressComponentDataList;

    @SerializedName ("formatted_address")
    private String formatAddress;

    @SerializedName ("geometry")
    private Geometry geometry;

    public List<AddressComponentData> getAddressComponentDataList() {
        return addressComponentDataList;
    }

    public void setAddressComponentDataList(List<AddressComponentData> addressComponentDataList) {
        this.addressComponentDataList = addressComponentDataList;
    }

    public String getFormatAddress() {
        return formatAddress;
    }

    public void setFormatAddress(String formatAddress) {
        this.formatAddress = formatAddress;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public class AddressComponentData {
        @SerializedName ("long_name")
        private String longName;

        @SerializedName ("short_name")
        private String shortName;

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }
    }

    public class Geometry {
        @SerializedName ("location")
        private Location locationType;

        public Location getLocationType() {
            return locationType;
        }

        public void setLocationType(Location locationType) {
            this.locationType = locationType;
        }

        public class Location {
            @SerializedName ("lat")
            private double latitude;

            @SerializedName ("lng")
            private double longitude;

            public double getLatitude() {
                return latitude;
            }

            public void setLatitude(double latitude) {
                this.latitude = latitude;
            }

            public double getLongitude() {
                return longitude;
            }

            public void setLongitude(double longitude) {
                this.longitude = longitude;
            }
        }
    }
}
