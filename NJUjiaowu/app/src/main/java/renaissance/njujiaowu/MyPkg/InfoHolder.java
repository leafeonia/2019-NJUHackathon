package renaissance.njujiaowu.MyPkg;

import java.util.ArrayList;
import java.util.List;

import renaissance.njujiaowu.R;

public class InfoHolder {

    public static List<InfoHolder> m_InfoList;

    public String courseName;
    public String courseCredit;
    public String courseTime;
    public String courseTeacher;
    public String courseLimit;
    public String courseChosen;
    public int recommandLevel;


    public InfoHolder(String courseName, String courseUnit, String courseSchedual, String courseTeacher, String courseLimit, String courseChosen, int recommandLevel) {
        this.courseName = courseName;
        this.courseCredit = courseUnit;
        this.courseTime = courseSchedual;
        this.courseTeacher = courseTeacher;
        this.courseLimit = courseLimit;
        this.courseChosen = courseChosen;
        this.recommandLevel = return_color(recommandLevel);
    }

    public static void setCourseList(String CourseName, String CourseUnit, String CourseSchedual,
                              String CourseTeacher, String CourseLimit, String CourseChosen, int recommandLevel){
        if(m_InfoList == null)
            m_InfoList = new ArrayList<>();
        m_InfoList.add(new InfoHolder(CourseName, CourseUnit, CourseSchedual, CourseTeacher, CourseLimit, CourseChosen, recommandLevel));
    }

    private int return_color(int level){
        switch(level){
            case 0: return R.drawable.red_button;
            case 1: return R.drawable.yellow_button;
            case 2: return R.drawable.green_button;
            case 3: return R.drawable.grey_button;
        }
        return 0;
    }
}
