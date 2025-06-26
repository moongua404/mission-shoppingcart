package mission.application.domain;

import mission.application.domain.enums.LectureType;

public class Lecture {
    int id;
    String name;
    LectureType type;
    int cost;

    public Lecture(int id, String name, LectureType type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LectureType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }
}
