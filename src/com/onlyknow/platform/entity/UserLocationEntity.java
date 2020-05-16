package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_location", schema = "onlyknow_platform", catalog = "")
public class UserLocationEntity {
    private int ulId;
    private String userName;
    private double ulLongitude;
    private double ulDimension;
    private Date ulDate;

    @Id
    @Column(name = "UL_ID")
    public int getUlId() {
        return ulId;
    }

    public void setUlId(int ulId) {
        this.ulId = ulId;
    }

    @Basic
    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "UL_LONGITUDE")
    public double getUlLongitude() {
        return ulLongitude;
    }

    public void setUlLongitude(double ulLongitude) {
        this.ulLongitude = ulLongitude;
    }

    @Basic
    @Column(name = "UL_DIMENSION")
    public double getUlDimension() {
        return ulDimension;
    }

    public void setUlDimension(double ulDimension) {
        this.ulDimension = ulDimension;
    }

    @Basic
    @Column(name = "UL_DATE")
    public Date getUlDate() {
        return ulDate;
    }

    public void setUlDate(Date ulDate) {
        this.ulDate = ulDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLocationEntity that = (UserLocationEntity) o;
        return ulId == that.ulId &&
                Double.compare(that.ulLongitude, ulLongitude) == 0 &&
                Double.compare(that.ulDimension, ulDimension) == 0 &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(ulDate, that.ulDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ulId, userName, ulLongitude, ulDimension, ulDate);
    }
}
