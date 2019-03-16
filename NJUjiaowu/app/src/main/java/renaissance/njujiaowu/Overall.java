package renaissance.njujiaowu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import renaissance.njujiaowu.MyPkg.CourseInfo;
import renaissance.njujiaowu.MyPkg.CourseSoup;

public class Overall extends AppCompatActivity {

    TextView tv_points,tv_gpa,tv_tongshi,tv_tongxiu,tv_xuanxiu,tv_hexin,tv_pingtai,tv_1a,tv_1b,tv_2a,tv_2b,tv_3a,tv_3b,tv_4a,tv_4b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_points = (TextView)findViewById(R.id.tv_points);
        tv_gpa = (TextView)findViewById(R.id.tv_gpa);
        tv_tongshi = (TextView)findViewById(R.id.tv_tongshi);
        tv_tongxiu = (TextView)findViewById(R.id.tv_tongxiu);
        tv_pingtai = (TextView)findViewById(R.id.tv_pingtai);
        tv_xuanxiu = (TextView)findViewById(R.id.tv_xuanxiu);
        tv_hexin = (TextView)findViewById(R.id.tv_hexin);
        tv_1a = (TextView)findViewById(R.id.tv_1a);
        tv_1b = (TextView)findViewById(R.id.tv_1b);
        tv_2a = (TextView)findViewById(R.id.tv_2a);
        tv_2b = (TextView)findViewById(R.id.tv_2b);
        tv_3a = (TextView)findViewById(R.id.tv_3a);
        tv_3b = (TextView)findViewById(R.id.tv_3b);
        tv_4a = (TextView)findViewById(R.id.tv_4a);
        tv_4b = (TextView)findViewById(R.id.tv_4b);



    }

    @Override
    protected void onResume() {
        int points = 0;
        int total_score = 0;
        int tongxiu_points = 0;
        int tongxiu_score = 0;
        int tongshi_score = 0;
        int tongshi_points = 0;
        int pingtai_points = 0;
        int pingtai_score = 0;
        int xuanxiu_points = 0;
        int xuanxiu_score = 0;
        int hexin_points = 0;
        int hexin_score = 0;
        int points_term[] = new int[8];
        int score_term[] = new int[8];
        if (CourseSoup.CourseList != null){
            int term = 0;
            for (CourseInfo courseInfo :CourseSoup.CourseList){
                term = Math.max(term,courseInfo.CourseTerm);
            }
            for (CourseInfo courseInfo :CourseSoup.CourseList){
                if (courseInfo.CourseTerm != -1){
                    points += courseInfo.CourseUnit;
                    total_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                    switch (courseInfo.CourseType){
                        case "通修":
                            tongxiu_points += courseInfo.CourseUnit;
                            tongxiu_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                            break;
                        case "通识":
                            tongshi_points += courseInfo.CourseUnit;
                            tongshi_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                            break;
                        case "选修":
                            xuanxiu_points += courseInfo.CourseUnit;
                            xuanxiu_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                            break;
                        case "平台":
                            pingtai_points += courseInfo.CourseUnit;
                            pingtai_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                            break;
                        case "核心":
                            hexin_points += courseInfo.CourseUnit;
                            hexin_score += courseInfo.CourseScore*courseInfo.CourseUnit;
                            break;
                        default:
                            break;
                    }
                    points_term[term - courseInfo.CourseTerm]+= courseInfo.CourseUnit;
                    score_term[term - courseInfo.CourseTerm]+=courseInfo.CourseScore*courseInfo.CourseUnit;
                }

            }
            tv_points.setText(Integer.toString(points));
            if (points != 0)tv_gpa.setText(Double.toString((double)total_score/points/20).substring(0,4));
            if (tongshi_points != 0)tv_tongshi.setText(Integer.toString(tongshi_points)+"/"+Double.toString((double)tongshi_score/tongshi_points/20).substring(0,4));
            if (tongxiu_points != 0)tv_tongxiu.setText(Integer.toString(tongxiu_points)+"/"+Double.toString((double)tongxiu_score/tongxiu_points/20).substring(0,4));
            if (pingtai_points != 0)tv_pingtai.setText(Integer.toString(pingtai_points)+"/"+Double.toString((double)pingtai_score/pingtai_points/20).substring(0,4));
            if (hexin_points != 0)tv_hexin.setText(Integer.toString(hexin_points)+"/"+Double.toString((double)hexin_score/hexin_points/20).substring(0,4));
            if (xuanxiu_points != 0)tv_xuanxiu.setText(Integer.toString(xuanxiu_points)+"/"+Double.toString((double)xuanxiu_score/xuanxiu_points/20).substring(0,4));
            if (points_term[0] != 0)tv_1a.setText(Integer.toString(points_term[0])+"/"+Double.toString((double)score_term[0]/points_term[0]/20).substring(0,4));
            if (points_term[1] != 0)tv_1b.setText(Integer.toString(points_term[1])+"/"+Double.toString((double)score_term[1]/points_term[1]/20).substring(0,4));
            if (points_term[2] != 0)tv_2a.setText(Integer.toString(points_term[2])+"/"+Double.toString((double)score_term[2]/points_term[2]/20).substring(0,4));
            if (points_term[3] != 0)tv_2b.setText(Integer.toString(points_term[3])+"/"+Double.toString((double)score_term[3]/points_term[3]/20).substring(0,4));
            if (points_term[4] != 0)tv_3a.setText(Integer.toString(points_term[4])+"/"+Double.toString((double)score_term[4]/points_term[4]/20).substring(0,4));
            if (points_term[5] != 0)tv_3b.setText(Integer.toString(points_term[5])+"/"+Double.toString((double)score_term[5]/points_term[5]/20).substring(0,4));
            if (points_term[6] != 0)tv_4a.setText(Integer.toString(points_term[6])+"/"+Double.toString((double)score_term[6]/points_term[6]/20).substring(0,4));
            if (points_term[7] != 0)tv_4b.setText(Integer.toString(points_term[7])+"/"+Double.toString((double)score_term[7]/points_term[7]/20).substring(0,4));
        }
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent i = new Intent(Overall.this,MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
