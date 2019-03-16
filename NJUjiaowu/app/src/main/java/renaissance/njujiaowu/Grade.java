package renaissance.njujiaowu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import renaissance.njujiaowu.MyPkg.CourseInfo;
import renaissance.njujiaowu.MyPkg.CourseSoup;

public class Grade extends AppCompatActivity {

    ListView lv_grade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lv_grade = (ListView)findViewById(R.id.lv_grade);

    }

    @Override
    protected void onResume() {
        Map grades = new HashMap();
        if (CourseSoup.CourseList != null){
            for (CourseInfo courseInfo :CourseSoup.CourseList){
                if (courseInfo.CourseTerm != -1) {
                    grades.put(courseInfo.CourseName, Integer.toString(courseInfo.CourseScore)+"*"+Integer.toString(courseInfo.CourseTerm));
                }
            }
        }

        Set<Map.Entry<String,String>> entrys = grades.entrySet();
        List<Map.Entry<String,String>> gradeList = new ArrayList<>(entrys);

        for (int i = 0; i < gradeList.size(); i++) {
            for (int j = i+1; j < gradeList.size(); j++) {
                int iTerm = Integer.parseInt(gradeList.get(i).getValue().split("\\*")[1]);
                int jTerm = Integer.parseInt(gradeList.get(j).getValue().split("\\*")[1]);
                if (iTerm > jTerm){
                    Map.Entry<String,String> entry = gradeList.get(j);
                    gradeList.set(j,gradeList.get(i));
                    gradeList.set(i,entry);
                }
            }
        }
        lv_grade.setAdapter(new GradeAdapter(gradeList,Grade.this));
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent i = new Intent(Grade.this,MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
