package com.test.nkfindjob.domain.po;

import java.io.Serializable;

public class Company implements Serializable {
    private String name;
    private String industry;
    private String scale;
    private String capital;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", industry='" + industry + '\'' +
                ", scale='" + scale + '\'' +
                ", capital='" + capital + '\'' +
                '}';
    }

    public Company(String name, String industry, String scale, String capital) {
        this.name = name;
        this.industry = industry;
        this.scale = scale;
        this.capital = capital;
    }

    public Company() {
    }
}
