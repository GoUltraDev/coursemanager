package co.techo.entity.courses;


public enum Location {
    แก่งคอย("แก่งคอย"),
    ลานหิน("ลานหิน"),
    หาดใหญ่("หาดใหญ่"),
    มูลนิธิอ่อนนุช("มูลนิธิ อ่อนนุช");

    private String displayName;

    Location(String display) {
        this.displayName = display;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
    }
