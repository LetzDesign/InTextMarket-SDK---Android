package com.intext.intextmarket2.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

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

@Entity
public class IMBusinessHistory {

    @NonNull
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "category_id")
    private int categoryId;

    @ColumnInfo(name = "business_id")
    private int businessId;

    @ColumnInfo(name = "campaign_id")
    private int campaignId;

    @ColumnInfo(name = "job_id")
    private int jobId;

    @ColumnInfo(name = "word_bit")
    private float wordBit;

    @ColumnInfo(name = "client_sort")
    private int clientSort;

    @ColumnInfo(name = "delivery")
    private boolean delivery;

    @ColumnInfo(name = "carry_out")
    private boolean carryOut;

    @ColumnInfo(name = "shipping")
    private boolean shipping;

    @ColumnInfo(name = "open")
    private boolean open;

    @ColumnInfo(name = "open_hours")
    private String openHours;

    @ColumnInfo(name = "open_days")
    private String openDays;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    @ColumnInfo(name = "virtual_business")
    private String virtualBusiness;

    @ColumnInfo(name = "match_words")
    private List<String> matchWords;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String siteUrl;
    private String icon;

    public IMBusinessHistory() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public float getWordBit() {
        return wordBit;
    }

    public void setWordBit(float wordBit) {
        this.wordBit = wordBit;
    }

    public int getClientSort() {
        return clientSort;
    }

    public void setClientSort(int clientSort) {
        this.clientSort = clientSort;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public boolean isCarryOut() {
        return carryOut;
    }

    public void setCarryOut(boolean carryOut) {
        this.carryOut = carryOut;
    }

    public boolean isShipping() {
        return shipping;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
