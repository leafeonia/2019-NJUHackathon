package MyPkg;

import java.util.ArrayList;

public class CourseSoup {
    static ArrayList<CourseInfo> CourseList;
    public static void init() {
        CourseList = new ArrayList<CourseInfo>();
    }
    public static void add(int Term, String Name, int unit, int score, String Type) {
        CourseInfo info = new CourseInfo(Term, Name, unit, score, Type);
        CourseList.add(info);
    }
    public static int length() { return CourseList.size(); }
}
