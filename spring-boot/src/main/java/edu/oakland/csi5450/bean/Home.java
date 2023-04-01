
package edu.oakland.csi5450.bean;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Home {

    private Integer homeId;

    @NotNull
    @Min(1)
    private Integer floorSpace;

    @NotNull
    @Min(1)
    private Short numFloors;

    @NotNull
    @Min(1)
    private Short numBedrooms;

    @NotNull
    @Min(0)
    private Integer fullBaths;

    @NotNull
    @Min(0)
    private Integer halfBaths;

    @NotNull
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "99999.9")
    private Double landSize;

    @NotNull
    @Min(1500)
    private Short yearBuilt;

    @NotBlank
    private String homeType;

    @NotNull
    private Boolean isForSale;

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public Integer getFloorSpace() {
        return floorSpace;
    }

    public void setFloorSpace(Integer floorSpace) {
        this.floorSpace = floorSpace;
    }

    public Short getNumFloors() {
        return numFloors;
    }

    public void setNumFloors(Short numFloors) {
        this.numFloors = numFloors;
    }

    public Short getNumBedrooms() {
        return numBedrooms;
    }

    public void setNumBedrooms(Short numBedrooms) {
        this.numBedrooms = numBedrooms;
    }

    public Integer getFullBaths() {
        return fullBaths;
    }

    public void setFullBaths(Integer fullBaths) {
        this.fullBaths = fullBaths;
    }

    public Integer getHalfBaths() {
        return halfBaths;
    }

    public void setHalfBaths(Integer halfBaths) {
        this.halfBaths = halfBaths;
    }

    public Double getLandSize() {
        return landSize;
    }

    public void setLandSize(Double landSize) {
        this.landSize = landSize;
    }

    public Short getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Short yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getHomeType() {
        return homeType;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public Boolean getIsForSale() {
        return isForSale;
    }

    public void setIsForSale(Boolean isForSale) {
        this.isForSale = isForSale;
    }

    public Object getOwnerId() {
        return null;
    }
}
