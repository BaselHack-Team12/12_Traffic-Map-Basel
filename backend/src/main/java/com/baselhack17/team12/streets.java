package com.baselhack17.team12;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

/**
 * //TODO write here something nicer
 */
@Entity
public class streets {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String streetName;
    private double longitude;
    private double latitude;
    private String area;
    private double speedlimit;
    private Double density;
    private Double dangerId;
    private Integer speedingCarsCount;
    private Integer totalCarsCount;

    @Id
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "streetName")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Basic
    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "speedlimit")
    public double getSpeedlimit() {
        return speedlimit;
    }

    public void setSpeedlimit(double speedlimit) {
        this.speedlimit = speedlimit;
    }

    @Basic
    @Column(name = "density")
    public Double getDensity() {
        return density;
    }

    public void setDensity(Double density) {
        this.density = density;
    }

    @Basic
    @Column(name = "dangerId")
    public Double getDangerId() {
        return dangerId;
    }

    public void setDangerId(Double dangerId) {
        this.dangerId = dangerId;
    }

    @Basic
    @Column(name = "speedingCarsCount")
    public Integer getSpeedingCarsCount() {
        return speedingCarsCount;
    }

    public void setSpeedingCarsCount(Integer count) {
        this.speedingCarsCount = count;
    }

    @Basic
    @Column(name = "totalCarsCount")
    public Integer getTotalCarsCount() {
        return totalCarsCount;
    }

    public void setTotalCarsCount(Integer totalCarsCount) {
        this.totalCarsCount = totalCarsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        streets streets = (com.baselhack17.team12.streets) o;
        return id == streets.id &&
                Double.compare(streets.longitude, longitude) == 0 &&
                Double.compare(streets.latitude, latitude) == 0 &&
                Double.compare(streets.speedlimit, speedlimit) == 0 &&
                Objects.equal(streetName, streets.streetName) &&
                Objects.equal(area, streets.area) &&
                Objects.equal(density, streets.density) &&
                Objects.equal(dangerId, streets.dangerId) &&
                Objects.equal(speedingCarsCount, streets.speedingCarsCount) &&
                Objects.equal(totalCarsCount, streets.totalCarsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, streetName, longitude, latitude, area, speedlimit, density,
                dangerId, speedingCarsCount, totalCarsCount);
    }
}
