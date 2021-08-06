package com.test.nkfindjob.domain.po;

import java.io.Serializable;

public class Job implements Serializable {
    private Integer id;
    private String title;
    private String comName;
    private String wages;
    private String city;
    private String qualification;
    private String treatment;

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", comName='" + comName + '\'' +
                ", wages='" + wages + '\'' +
                ", city='" + city + '\'' +
                ", qualification='" + qualification + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getWages() {
        return wages;
    }

    public void setWages(String wages) {
        this.wages = wages;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Job(String title, String comName, String wages, String city, String qualification, String treatment) {
        this.title = title;
        this.comName = comName;
        this.wages = wages;
        this.city = city;
        this.qualification = qualification;
        this.treatment = treatment;
    }

    public Job(Integer id, String title, String comName, String wages, String city, String qualification, String treatment) {
        this.id = id;
        this.title = title;
        this.comName = comName;
        this.wages = wages;
        this.city = city;
        this.qualification = qualification;
        this.treatment = treatment;
    }

    public Job() {
    }
}
