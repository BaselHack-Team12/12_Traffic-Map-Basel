package com.baselhack17.team12;

import static com.google.common.base.Objects.equal;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * //TODO write here something nicer.
 */
@Entity
public class Cars {
    private int id;
    private int streetId;
    private Double speed;
    private Double size;
    private Date timeStamp;
    private Integer direction;
    private Streets streetsByStreetId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "streetId")
    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    @Basic
    @Column(name = "speed")
    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Basic
    @Column(name = "size")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Basic
    @Column(name = "timeStamp")
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Basic
    @Column(name = "direction")
    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return id == cars.id &&
                streetId == cars.streetId &&
                equal(speed, cars.speed) &&
                equal(size, cars.size) &&
                equal(timeStamp, cars.timeStamp) &&
                equal(direction, cars.direction);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, streetId, speed, size, timeStamp, direction);
    }

    @ManyToOne
    @JoinColumn(name = "streetId", referencedColumnName = "id", nullable = false)
    public Streets getStreetsByStreetId() {
        return streetsByStreetId;
    }

    public void setStreetsByStreetId(Streets streetsByStreetId) {
        this.streetsByStreetId = streetsByStreetId;
    }
}
