package fr.routardfilrouge.routard.metier;

public class POISearch {
    private String poiName;
    private POIType type;
    private Subdivision subdivision;

    public POISearch() {
        poiName = "";
        type = new POIType();
        subdivision = new Subdivision();
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public POIType getType() {
        return type;
    }

    public void setType(POIType type) {
        this.type = type;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }
}
