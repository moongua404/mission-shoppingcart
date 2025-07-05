package mission.application.domain.enums;

import java.util.Map;

public enum LectureType {
    DevOps("DevOps"), DBMS("DBMS"), Lang("Lang"), FW("F/W"), CS("CS");

    private static final Map<String, LectureType> namingMap =
            Map.of(DevOps.name, DevOps, DBMS.name, DBMS, Lang.name, Lang, FW.name, FW, CS.name, CS);
    private final String name;

    LectureType(String name) {
        this.name = name;
    }

    public static LectureType getLectureType(String lectureType) {
        return namingMap.get(lectureType.trim());
    }

    public String getName() {
        return name;
    }
}
