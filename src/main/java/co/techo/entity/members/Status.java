package co.techo.entity.members;

import lombok.Getter;

@Getter
public enum Status {

    ผู้สมัครใหม่("ผู้สมัครใหม่"),
    ศิษย์อานาปานสติ("ศิษย์อานาปานสติ"),
    ศิษย์เตโชวิปัสสนา("ศิษย์เตโชวิปัสสนา"),
    ศิษย์อานาฯ_1("ศิษย์อานาฯ ๑ วัน");

    private String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }


    @Override
    public String toString() {
        return displayName;
    }
}
