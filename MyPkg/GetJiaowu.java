package MyPkg;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import com.zhy.http.okhttp.request.RequestCall;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;



import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.naming.*;


public class GetJiaowu {
    public static final int LoginSucceed = 1;
    public static final int PasswordWrong = 2;
    public static final int CaptchaWrong = 3;
    public static final int CaptchaExpire = 4;

    private static class Holder{
        private static final GetJiaowu hehehe = new GetJiaowu();
    }

    public static GetJiaowu play(){
        return Holder.hehehe;
    }

    public static void loadCaptcha(final String schoolUrl, String image_name) throws Exception{
        OkHttpUtils
                .get()
                .url(schoolUrl + "ValidateCode.jsp")
                .build()
                .execute(
                new FileCallBack("D:/", image_name) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(File response, int id) { System.out.println("yes"); }
                });
    }

    //CookieJar是用于保存Cookie的
    static class  LocalCookieJar implements CookieJar {
        List<Cookie> cookies;

        @Override
        public List<Cookie> loadForRequest(HttpUrl arg0) {
            if (cookies != null)
                return cookies;
            return new ArrayList<Cookie>();
        }

        @Override
        public void saveFromResponse(HttpUrl arg0, List<Cookie> cookies) {
            System.out.println("----------------saveFromResponse");
            for (Cookie cookie : cookies) {
                System.out.println(cookie);
            }
            this.cookies = cookies;
        }

    }

    public static int login_get(String username, String password, String captcha) {

        GetJiaowu.play().login("http://elite.nju.edu.cn/jiaowu/", username, password, captcha);

        return LoginSucceed;
    }

    public static void init() {

        //CourseSoup.init();

        load();

    }

    private static void load() {
        try {

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
                    .followSslRedirects(false)
                    .cookieJar(new LocalCookieJar())   //为OkHttp设置自动携带Cookie的功能
                    //.addInterceptor(new LoggerInterceptor("TAG"))
                    //.cookieJar(new CookieJarImpl(new PersistentCookieStore(getBaseContext()))) //要在内存Cookie前
                    .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//内存Cookie
                    .cache(null)
                    .build();
            OkHttpUtils.initClient(okHttpClient);

            GetJiaowu.play().loadCaptcha("http://elite.nju.edu.cn/jiaowu/", "loadCaptcha.jpg");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //System.out.println();
    public static void login(final String schoolUrl, final String xh, final String passwd, String catpcha) {
        System.out.println("logining...");
        OkHttpUtils.post().url(schoolUrl + "login.do")
                .addParams("userName", xh)
                .addParams("password", passwd)
                .addParams("ValidateCode", catpcha)
                .build()
                .execute(new StringCallback() {
                    @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(String response, int id) {
                        //LogUtil.e(this, response);
                if (response.contains("验证码错误！")) {
                    System.out.println("验证码错误！");
                    //验证码错误的处理函数
                    //callback.onFail(app.mContext.getString(R.string.captcha_err));
                } else if (response.contains("密码错误")) {
                    System.out.println("密码错误");
                    //密码错误的处理函数
                    //callback.onFail(app.mContext.getString(R.string.pwd_err));
                } else if (response.contains("验证码已过期")) {
                    System.out.println("验证码已过期");
                    //连接超时的处理函数
                    //callback.onFail(app.mContext.getString(R.string.timeout_err));
                } else {
                    //登陆成功，获取下一界面的内容
                    //System.out.println(response);
                    toCommonCourse(schoolUrl);
                    //toCurriculum(schoolUrl); //课程表页面
                    //toGrade(schoolUrl); //成绩页面
                }
            }
        });
    }

    public static void toScore(String grade_url, int Grade) {
        OkHttpUtils.get().url(grade_url).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String s, int i) {
//body > div:nth-child(11) > table > tbody > tr:nth-child(2) > td:nth-child(4) > table > tbody
                Document doc = Jsoup.parse(s);
                Elements table = doc.select("body").select("div:nth-child(11)").select("table")
                        .select("tbody").select("tr:nth-child(2)").select("td:nth-child(4)")
                        .select("table").select("tbody");
                for(Element ele : table) {
//body > div:nth-child(11) > table > tbody > tr:nth-child(2) > td:nth-child(4) > table > tbody > tr:nth-child(2)
                    Elements course_list = ele.getElementsByAttribute("align");
                    for(Element course : course_list) {
                        //if(course.select("td:nth-child(1)").text().equals("序号")) break;
                        //System.out.println(course.select("td:nth-child(1)").text());
//body > div:nth-child(11) > table > tbody > tr:nth-child(2) > td:nth-child(4) > table > tbody > tr:nth-child(2) > td:nth-child(3)
                        String course_name = course.select("td:nth-child(3)").text();
                        String course_unit = course.select("td:nth-child(6)").text();
                        String course_score = course.select("td:nth-child(7)").text();
                        String course_type = course.select("td:nth-child(5)").text();
                        if(course_name.length() > 0 && course_unit.length() > 0 && course_score.length() > 0) { //不加这一句会出现很多空的语句
                            //System.out.println(course_name.length() + " " + course_unit.length() + " " + course_score.length());
                            //添加课程信息，默认转化的为float类型
                            CourseSoup.add(Grade, course_name, (int)Float.parseFloat(course_unit), (int)Float.parseFloat(course_score), course_type);
                            System.out.println(Grade + " " + course_name + " units:" + course_unit + " score:" + course_score);
                        }
                    }
                    /*int length = CourseSoup.length();
                    System.out.println("length: " + length);
                    for(int j = 0; j < length; j++) {
                        System.out.println(CourseSoup.CourseList.get(j));
                    }*/
                }
            }
        });
    }

    public static void toGrade(final String schoolUrl) {
        OkHttpUtils.get().url(schoolUrl+"student/studentinfo/achievementinfo.do?method=searchTermList")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String s, int i) {
                Document doc = Jsoup.parse(s);
                //body > div:nth-child(11) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > table > tbody
                Elements term_list_Board = doc.select("body").select("div:nth-child(11)").select("table")
                        .select("tbody").select("tr:nth-child(2)").select("td:nth-child(2)")
                        .select("div").select("table").select("tbody");
                for(Element term_list_Board_ele : term_list_Board) {
                    Elements term_list = term_list_Board_ele.getElementsByAttribute("align");
                    int grade = 1;
                    for(Element term : term_list) {
                        System.out.println(term.text());
                        //body > div:nth-child(11) > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > table > tbody > tr:nth-child(3) > td > a
                        String grade_url = schoolUrl + term.getElementsByTag("a").attr("href");
                        toScore(grade_url, grade++);
                    }
                }
            }
        });
    }

    public static void toCurriculum(final String schoolUrl) {
        //LogUtil.d(this, "get normal+" + user_name);
        //分析“我的课程”这个页面
        OkHttpUtils.get().url(schoolUrl + "student/teachinginfo/courseList.do?method=currentTermCourse")
                .build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                        //callback.onFail(ACCESS_ERR2);
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        //System.out.println(response);
                        Document doc = Jsoup.parse(response);
//body > div:nth-child(10) > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr.TABLE_TH > th:nth-child(1)
                        Elements tbody = doc.select("body").select("div:nth-child(10)").select("table")
                                .select("tbody").select("tr:nth-child(3)").select("td:nth-child(2)").select("table")
                                .select("tbody");
                        for(Element tbody_context : tbody) {
                            Elements course_list = tbody_context.getElementsByAttribute("align");
                            for(Element single_course : course_list) {
                                //body > div:nth-child(10) > table > tbody > tr:nth-child(3) > td:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(3)
                                String course_name = single_course.select("td:nth-child(3)").text();
                                String teacher = single_course.select("td:nth-child(5)").text();
                                String schedual = single_course.select("td:nth-child(6)").text();
                                //按照空格切分一节课的字符串
                                String [] str = schedual.split("\\s+");
                                int str_size = str.length;
                                for(int i = 0; i < str_size; i++) {
                                    if(str[i].contains("第")) {
                                        int time = -1;
                                        if(str[i].contains("1-")) time = MorningCourse.CourseFirst;
                                        else if(str[i].contains("2-")) time = MorningCourse.CourseSecond;
                                        else if(str[i].contains("3-")) time = MorningCourse.CourseThird;
                                        if(time == -1) continue;
                                        switch (str[i-1]) {
                                            case "周一": MorningCourse.Monday = time; break;
                                            case "周二": MorningCourse.Tuesday = time; break;
                                            case "周三": MorningCourse.Wednesday = time; break;
                                            case "周四": MorningCourse.Thrusday = time; break;
                                            case "周五": MorningCourse.Friday = time; break;
                                        }
                                    }
                                }
                                System.out.println(course_name + " " + teacher + " " + schedual);
                            }
                        }

                        //System.out.println("login succeed!");
                        //System.out.println(response);
                        //response这个字符串就是网页的html代码
                        //Url.VIEWSTATE_POST_CODE = ParseCourse.parseViewStateCode(response);
                        //callback.onSuccess(response);

                        //更新没有早课的情况
                        if(MorningCourse.Monday == -1) MorningCourse.Monday = MorningCourse.CourseNone;
                        if(MorningCourse.Tuesday == -1) MorningCourse.Tuesday = MorningCourse.CourseNone;
                        if(MorningCourse.Wednesday == -1) MorningCourse.Wednesday = MorningCourse.CourseNone;
                        if(MorningCourse.Thrusday == -1) MorningCourse.Thrusday = MorningCourse.CourseNone;
                        if(MorningCourse.Friday == -1) MorningCourse.Friday = MorningCourse.CourseNone;
                        System.out.println("Mon:" + MorningCourse.Monday);
                        System.out.println("Tue:" + MorningCourse.Tuesday);
                        System.out.println("Wed:" + MorningCourse.Wednesday);
                        System.out.println("Thr:" + MorningCourse.Thrusday);
                        System.out.println("Fri:" + MorningCourse.Friday);

                        toGrade(schoolUrl);
                    }
                });
    }

    public static void toCommonCourse(final String schoolUrl) {
        OkHttpUtils.get().url(schoolUrl + "student/elective/courseList.do?method=discussRenewCourseList&campus=仙林校区")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int i) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String s, int i) {
                Document doc = Jsoup.parse(s);
                //#tbCourseList > tbody
                Elements course_board = doc.select("#tbCourseList").select("tbody");
                for(Element ele : course_board) {
                    Elements course_list = ele.getElementsByAttribute("class");
                    for(Element course_single : course_list) {
                        //#tbCourseList > tbody > tr:nth-child(1) > td:nth-child(3)
                        String CourseName = course_single.select("td:nth-child(3)").text();
                        String CourseUnit = course_single.select("td:nth-child(4)").text();
                        String CourseSchedual = course_single.select("td:nth-child(5)").text();
                        String CourseTeacher = course_single.select("td:nth-child(6)").text();
                        String CourseLimit = course_single.select("td:nth-child(7)").text();
                        String CourseChosen = course_single.select("td:nth-child(8)").text();
                        System.out.println(CourseName + " 学分：" + CourseUnit + " " + CourseSchedual
                               + " " + CourseTeacher + " 限额：" + CourseLimit + " 已选：" + CourseChosen);
                    }
                }
            }
        });
    }

}
