package com.strifecore.core.domain;

public class ComponentName {

    private String publicName;
    private String devName;

    public ComponentName(String publicName, String devName) {
        this.publicName = publicName;
        this.devName = devName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "publicName='" + publicName + '\'' +
                ", devName='" + devName + '\'' +
                '}';
    }
}
