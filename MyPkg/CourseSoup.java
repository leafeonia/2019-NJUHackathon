package MyPkg;

import java.util.ArrayList;

public class CourseSoup {
    public static ArrayList<CourseInfo> CourseList;
    /*public static void init() {
        CourseList = new ArrayList<CourseInfo>();
    }*/
    public static void add(int Term, String Name, int unit, int score, String Type) {
        if(CourseList.isEmpty() == true) CourseList = new ArrayList<CourseInfo>();
        CourseInfo info = new CourseInfo(Term, Name, unit, score, Type);
        CourseList.add(info);
    }
    public static int length() {
        if(CourseList.isEmpty() == true) return -1;
        return CourseList.size();
    }
}
