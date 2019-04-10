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

public class ServiceBusiness {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("transaction_id")
    @Expose
    private String transaction_id;
    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("business")
    @Expose
    private List<Business> business = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public List<Category> getCategories() { return categories; }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Business> getBusiness() {
        return business;
    }

    public void setBusiness(List<Business> business) {
        this.business = business;
    }
}
