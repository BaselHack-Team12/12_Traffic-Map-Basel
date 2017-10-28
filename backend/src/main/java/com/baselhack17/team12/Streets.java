package com.baselhack17.team12;

import static com.google.common.base.Objects.equal;
import static java.lang.Double.compare;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * //TODO write here something nicer.
 */
@Entity
public class Streets {
    private int id;
    private String streetName;
    private double longitude;
    private double latitude;
    private String area;
    private double speedlimit;
    private Double density;
    private Double dangerId;
    private Collection<Cars> carsById;

    @Id
    @Column(name = "id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Streets streets = (Streets) o;
        return id == streets.id &&
                compare(streets.longitude, longitude) == 0 &&
                compare(streets.latitude, latitude) == 0 &&
                compare(streets.speedlimit, speedlimit) == 0 &&
                equal(streetName, streets.streetName) &&
                equal(area, streets.area) &&
                equal(density, streets.density) &&
                equal(dangerId, streets.dangerId);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, streetName, longitude, latitude, area, speedlimit, density, dangerId);
    }

    @OneToMany(mappedBy = "streetsByStreetId")
    public Collection<Cars> getCarsById() {
        return carsById;
    }

    public void setCarsById(Collection<Cars> carsById) {
        this.carsById = carsById;
    }
}
