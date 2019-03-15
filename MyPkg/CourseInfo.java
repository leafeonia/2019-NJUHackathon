package MyPkg;

public class CourseInfo {
    public int CourseTerm;
    public String CourseName;
    public int CourseUnit;
    public int CourseScore;
    public String CourseType;
    CourseInfo(int term, String name, int unit, int score, String type) {
        CourseTerm = term;
        CourseName = name;
        CourseUnit = unit;
        CourseScore = score;
        CourseType = type;
    }
    @Override
    public String toString() {
        return CourseTerm + " " + CourseName + " " + CourseUnit + " " + CourseScore + " " + CourseType;
    }
}
