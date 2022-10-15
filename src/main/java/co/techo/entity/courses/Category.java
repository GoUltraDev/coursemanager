package co.techo.entity.courses;

import lombok.Getter;

@Getter
public enum Category {
    คอร์สอานาปานสติ("คอร์สอานาปานสติ"),
    คอร์สเตโชวิปัสสนา("คอร์สเตโชวิปัสสนา"),
    คอร์สเตโชฯ_ศิษย์เก่า("คอร์สเตโชฯ (ศิษย์เก่า)"),
    คอร์สศิษย์เก่า_3("คอร์สศิษย์เก่า (๓ วัน)"),
    คอร์สวิถีอาสวะ("คอร์สวิถีอาสวะ"),
    คอร์สฤาษี_14("คอร์สฤาษี (๑๔ วัน)"),
    ธรรมะแคมป์("ธรรมะแคมป์"),
    คอร์สอานาปานสติ_1("คอร์สอานาปานสติ ๑ วัน");

    private String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}


