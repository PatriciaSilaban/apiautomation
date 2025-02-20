package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class getListOfObjectByIDSResponse {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public DataItem dataItem;

    public static class DataItem {

        @JsonProperty("color")
        public String color;

        @JsonProperty("capacity GB")
        public int capacityGB;

        @JsonProperty("price")
        public double price;

        @JsonProperty("Capacity")
        public String Capacity;

        @JsonProperty("Screen size")
        public double screenSize;
    }

    public getListOfObjectByIDSResponse get(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }
}