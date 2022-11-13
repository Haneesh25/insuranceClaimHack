package com.example.insuranceclaimhack;

public class ExampleItem {
    private String name,claimName,claimDesc;
    //private String mText2;

    public ExampleItem(String name, String claimName, String claimDesc) {

        this.name = name;
        this.claimName = claimName;
        this.claimDesc = claimDesc;
    }

    public String getName() {
        return name;
    }

    public String getClaimName() {
        return claimName;
    }

    public String getClaimDesc() {
        return claimDesc;
    }
}