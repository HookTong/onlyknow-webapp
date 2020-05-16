package com.onlyknow.platform.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "image_carousel_ad", schema = "onlyknow_platform", catalog = "")
public class ImageCarouselAdEntity {
    public final static String KEY_GROUP_ID = "groupId";
    private int groupId;
    private String hpImageUrl1;
    private String hpImageUrl2;
    private String hpImageUrl3;
    private String hpImageUrl4;
    private String hpImageUrl5;
    private String adImageUrl1;
    private String adImageUrl2;
    private String adImageUrl3;
    private String adLinkUrl1;
    private String adLinkUrl2;
    private String adLinkUrl3;
    private Date groupDate;

    @Id
    @Column(name = "GROUP_ID")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "HP_IMAGE_URL1")
    public String getHpImageUrl1() {
        return hpImageUrl1;
    }

    public void setHpImageUrl1(String hpImageUrl1) {
        this.hpImageUrl1 = hpImageUrl1;
    }

    @Basic
    @Column(name = "HP_IMAGE_URL2")
    public String getHpImageUrl2() {
        return hpImageUrl2;
    }

    public void setHpImageUrl2(String hpImageUrl2) {
        this.hpImageUrl2 = hpImageUrl2;
    }

    @Basic
    @Column(name = "HP_IMAGE_URL3")
    public String getHpImageUrl3() {
        return hpImageUrl3;
    }

    public void setHpImageUrl3(String hpImageUrl3) {
        this.hpImageUrl3 = hpImageUrl3;
    }

    @Basic
    @Column(name = "HP_IMAGE_URL4")
    public String getHpImageUrl4() {
        return hpImageUrl4;
    }

    public void setHpImageUrl4(String hpImageUrl4) {
        this.hpImageUrl4 = hpImageUrl4;
    }

    @Basic
    @Column(name = "HP_IMAGE_URL5")
    public String getHpImageUrl5() {
        return hpImageUrl5;
    }

    public void setHpImageUrl5(String hpImageUrl5) {
        this.hpImageUrl5 = hpImageUrl5;
    }

    @Basic
    @Column(name = "AD_IMAGE_URL1")
    public String getAdImageUrl1() {
        return adImageUrl1;
    }

    public void setAdImageUrl1(String adImageUrl1) {
        this.adImageUrl1 = adImageUrl1;
    }

    @Basic
    @Column(name = "AD_IMAGE_URL2")
    public String getAdImageUrl2() {
        return adImageUrl2;
    }

    public void setAdImageUrl2(String adImageUrl2) {
        this.adImageUrl2 = adImageUrl2;
    }

    @Basic
    @Column(name = "AD_IMAGE_URL3")
    public String getAdImageUrl3() {
        return adImageUrl3;
    }

    public void setAdImageUrl3(String adImageUrl3) {
        this.adImageUrl3 = adImageUrl3;
    }

    @Basic
    @Column(name = "AD_LINK_URL1")
    public String getAdLinkUrl1() {
        return adLinkUrl1;
    }

    public void setAdLinkUrl1(String adLinkUrl1) {
        this.adLinkUrl1 = adLinkUrl1;
    }

    @Basic
    @Column(name = "AD_LINK_URL2")
    public String getAdLinkUrl2() {
        return adLinkUrl2;
    }

    public void setAdLinkUrl2(String adLinkUrl2) {
        this.adLinkUrl2 = adLinkUrl2;
    }

    @Basic
    @Column(name = "AD_LINK_URL3")
    public String getAdLinkUrl3() {
        return adLinkUrl3;
    }

    public void setAdLinkUrl3(String adLinkUrl3) {
        this.adLinkUrl3 = adLinkUrl3;
    }

    @Basic
    @Column(name = "GROUP_DATE")
    public Date getGroupDate() {
        return groupDate;
    }

    public void setGroupDate(Date groupDate) {
        this.groupDate = groupDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageCarouselAdEntity that = (ImageCarouselAdEntity) o;
        return groupId == that.groupId &&
                Objects.equals(hpImageUrl1, that.hpImageUrl1) &&
                Objects.equals(hpImageUrl2, that.hpImageUrl2) &&
                Objects.equals(hpImageUrl3, that.hpImageUrl3) &&
                Objects.equals(hpImageUrl4, that.hpImageUrl4) &&
                Objects.equals(hpImageUrl5, that.hpImageUrl5) &&
                Objects.equals(adImageUrl1, that.adImageUrl1) &&
                Objects.equals(adImageUrl2, that.adImageUrl2) &&
                Objects.equals(adImageUrl3, that.adImageUrl3) &&
                Objects.equals(adLinkUrl1, that.adLinkUrl1) &&
                Objects.equals(adLinkUrl2, that.adLinkUrl2) &&
                Objects.equals(adLinkUrl3, that.adLinkUrl3) &&
                Objects.equals(groupDate, that.groupDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, hpImageUrl1, hpImageUrl2, hpImageUrl3, hpImageUrl4, hpImageUrl5, adImageUrl1, adImageUrl2, adImageUrl3, adLinkUrl1, adLinkUrl2, adLinkUrl3, groupDate);
    }
}
