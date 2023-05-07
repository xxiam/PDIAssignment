// project class for assignment

public class Project {
    
    private String province;
    private String beneficiary;
    private String beneficiaryNum;
    private String assetClass;
    private String name;
    private String description;
    private String stage;
    private Location location;

    public Project(String inEntry) {
        //entry would look lke PROV_TERR_EN,BENEFICIARY_NAME_EN,BENEFICIARY_BAND_NBR,ASSET_CLASS_EN,PROJECT_NAME_EN,PROJECT_DESC_EN,PROJECT_STAGE_EN,LATITUDE,LONGITUDE,COORD_SYS
        //do not split the data if there are commas in between quotes
        String[] data = inEntry.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //regex to split on commas not in quotes (https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes)
        this.province = data[0];
        this.beneficiary = data[1];
        this.beneficiaryNum = data[2];
        this.assetClass = data[3];
        this.name = data[4];
        this.description = data[5];
        this.stage = data[6];
        this.location = new Location(Float.parseFloat(data[7]), Float.parseFloat(data[8]), data[9]);
    }

    /* accessors */
    public String getProvince() {
        return province;
    }
    public String getBeneficiary() {
        return beneficiary;
    }
    public String getBeneficiaryNum() {
        return beneficiaryNum;
    }
    public String getName() {
        return name;
    }
    public String getStage() {
        return stage;
    }
    public Location getLocation() {
        return location;
    }
}