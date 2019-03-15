package renaissance.njujiaowu.MyPkg;

import java.util.ArrayList;

public class CourseSoup {
    public static ArrayList<CourseInfo> CourseList;
    public static boolean isnewed = false;
//    public static void init() {
//        CourseList = new ArrayList<CourseInfo>();
//    }
    public static void add(int Term, String Name, int unit, int score, String Type) {
        if (isnewed == false) {
            CourseList = new ArrayList<CourseInfo>();
            isnewed = true;
        }
        CourseInfo info = new CourseInfo(Term, Name, unit, score, Type);
        CourseList.add(info);
    }
    public static int length() {
        if (isnewed == false) {
            CourseList = new ArrayList<CourseInfo>();
            isnewed = true;
        }
        return CourseList.size();
    }
}
