package com.baselhack17.team12;

import java.sql.Timestamp;

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
public class cars {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int streetId;
    private Double speed;
    private Double size;
    private Timestamp timeStamp;
    private Integer direction;

    @Id
    @Column(name = "id", unique = true)
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
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
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
        cars cars = (cars) o;
        return id == cars.id &&
                streetId == cars.streetId &&
                Objects.equal(speed, cars.speed) &&
                Objects.equal(streetId, cars.streetId) &&
                Objects.equal(size, cars.size) &&
                Objects.equal(timeStamp, cars.timeStamp) &&
                Objects.equal(direction, cars.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, streetId, speed, size, timeStamp, direction);
    }
}
