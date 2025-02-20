package com.apiautomation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class getListOfAllObjectResponse {
    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("data")
    public DataItem dataItem;

    public static class DataItem {

        @JsonProperty("color")
        public String color;

        @JsonProperty("capacity")
        public String capacity;

        @JsonProperty("capacity GB")
        public int capacityGB;

        @JsonProperty("price")
        public float price;

        @JsonProperty("generation")
        public String generation;

        @JsonProperty("year")
        public int year;

        @JsonProperty("CPU model")
        public String cpuModel;

        @JsonProperty("Hard disk size")
        public String hardDiskSize;

        @JsonProperty("Strap Colour")
        public String strapColour;

        @JsonProperty("Case Size")
        public String caseSize;

        @JsonProperty("Color")
        public String Color;

        @JsonProperty("Description")
        public String description;

        @JsonProperty("Capacity")
        public String Capacity;

        @JsonProperty("Screen size")
        public float screenSize;

        @JsonProperty("Generation")
        public String Generation;

        @JsonProperty("Price")
        public float Price;

    }
}