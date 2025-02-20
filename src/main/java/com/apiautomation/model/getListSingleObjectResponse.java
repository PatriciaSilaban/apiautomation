package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class getListSingleObjectResponse {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public DataItem dataItem;

    public static class DataItem {

        @JsonProperty("year")
        public int year;

        @JsonProperty("price")
        public double price;

        @JsonProperty("CPU model")
        public String cpuModel;

        @JsonProperty("Hard disk size")
        public String hardDiskSize;
    }
}