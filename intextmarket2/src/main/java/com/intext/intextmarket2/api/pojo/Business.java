package com.intext.intextmarket2.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ing. Letzer Cartagena Negron
 * InTextChat @2019
 * @author INTEXT SOFTWARE LLC
 *
 * Copyright (C) 2019 INTEXT SOFTWARE LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Business {

    @SerializedName("category_id")
    @Expose
    private Integer categoryId;

    @SerializedName("businnes_id")
    @Expose
    private Integer businnesId;

    @SerializedName("campaign_id")
    @Expose
    private Integer campaign_id;

    @SerializedName("job_id")
    @Expose
    private Integer job_id;

    @SerializedName("word_bit")
    @Expose
    private Float word_bit;

    @SerializedName("client_sort")
    @Expose
    private Integer clientSort;

    @SerializedName("delivery")
    @Expose
    private Boolean delivery;

    @SerializedName("carry_out")
    @Expose
    private Boolean carryOut;

    @SerializedName("shipping")
    @Expose
    private Boolean shipping;

    @SerializedName("open")
    @Expose
    private Boolean open;

    @SerializedName("open_hours")
    @Expose
    private String openHours;

    @SerializedName("open_days")
    @Expose
    private String openDays;

    @SerializedName("location")
    @Expose
    private Location location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("site_url")
    @Expose
    private String siteUrl;

    @SerializedName("virtual_business")
    @Expose
    private String virtualBusiness;

    @SerializedName("match_words")
    @Expose
    private List<String> matchWords = null;

    @SerializedName("icon")
    @Expose
    private String icon;


    public Integer getCategoryId() { return categoryId; }

    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public Integer getBusinnesId() {
        return businnesId;
    }

    public void setBusinnesId(Integer businnesId) {
        this.businnesId = businnesId;
    }

    public Integer getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Integer campaign_id) {
        this.campaign_id = campaign_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Float getWord_bit() {
        return word_bit;
    }

    public void setWord_bit(Float word_bit) {
        this.word_bit = word_bit;
    }

    public Integer getClientSort() {
        return clientSort;
    }

    public void setClientSort(Integer clientSort) {
        this.clientSort = clientSort;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public Boolean getCarryOut() {
        return carryOut;
    }

    public void setCarryOut(Boolean carryOut) {
        this.carryOut = carryOut;
    }

    public Boolean getShipping() {
        return shipping;
    }

    public void setShipping(Boolean shipping) {
        this.shipping = shipping;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    public String getOpenDays() {
        return openDays;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getVirtualBusiness() {
        return virtualBusiness;
    }

    public void setVirtualBusiness(String virtualBusiness) {
        this.virtualBusiness = virtualBusiness;
    }

    public List<String> getMatchWords() {
        return matchWords;
    }

    public void setMatchWords(List<String> matchWords) {
        this.matchWords = matchWords;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
