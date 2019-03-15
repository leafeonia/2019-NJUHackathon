package renaissance.njujiaowu;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class LoginNetwork extends Application {
    private LoginActivity mHost;
    private Context mContext;
    private ImageView mImageCatpcha;

    public int getcaptchaInfo;
    public int loginInfo;

    LoginNetwork(LoginActivity host,ImageView mImageView){
        mHost = host;
        mImageCatpcha = mImageView;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("LoginNetwork", "构造函数开始");

        initComponent();
        initOkHttp();
        getCaptcha();
    }

    private void initComponent(){
        mContext = mHost.getApplicationContext();
//        mImageCatpcha = mHost.mImageCatpcha;

        getcaptchaInfo = 0;
        loginInfo = 0;
    }

    private void initOkHttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
                .followSslRedirects(false)
                .cookieJar(new LocalCookieJar())   //为OkHttp设置自动携带Cookie的功能
                .addInterceptor(new LoggerInterceptor("TAG"))
                //.cookieJar(new CookieJarImpl(new PersistentCookieStore(getBaseContext()))) //要在内存Cookie前
                .cookieJar(new CookieJarImpl(new MemoryCookieStore()))//内存Cookie
                .cache(null)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }

    static class LocalCookieJar implements CookieJar{
        List<Cookie> cookies;

        @Override
        public List<Cookie> loadForRequest(HttpUrl arg0) {
            if (cookies != null)
                return cookies;
            return new ArrayList<Cookie>();
        }

        @Override
        public void saveFromResponse(HttpUrl arg0, List<Cookie> cookies) {
            System.out.println("saveFromResponse所获得的Cookie信息：");
            for (Cookie cookie : cookies) {
                System.out.println(cookie);
            }
            this.cookies = cookies;
        }
    }

    private boolean isLoadingCaptcha = false;
    public void getCaptcha(){
        if(isLoadingCaptcha){
            this.getcaptchaInfo = 1;
            Log.d("getCaptcha", "isLoadingCaptcha为真");
            return;
        }
        Log.d("getCaptcha", "进入getCaptcha函数");
        isLoadingCaptcha = true;
        OkHttpUtils.get()
                .url(mHost.getString(R.string.njunet_network) + mHost.getString(R.string.njunet_captcha))
                .build()
                .execute(
                new FileCallBack(mContext.getCacheDir().getAbsolutePath(), "loadCaptcha.jpg") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoginNetwork.this.getcaptchaInfo = 2;
                        Log.d("getCaptcha", "进入onError函数");
                        e.printStackTrace();
                        //mImageCatpcha.setImageResource(R.mipmap.ic_launcher);
                        isLoadingCaptcha = false;
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        LoginNetwork.this.getcaptchaInfo = 3;
                        Log.d("getCaptcha", "进入onResponse函数");
                        mImageCatpcha.setImageBitmap(BitmapFactory.decodeFile(
                                mContext
                                        .getCacheDir()
                                        .getAbsolutePath() + "/loadCaptcha.jpg"));
                        isLoadingCaptcha = false;
                    }
                });
    }

    public void login(final String xh,
                      final String passwd,
                      final String catpcha){
        Log.d("login", "开始login函数");
        OkHttpUtils.post()
                .url(mHost.getString(R.string.njunet_network) + mHost.getString(R.string.njunet_login))
                .addParams("userName", xh)
                .addParams("password", passwd)
                .addParams("ValidateCode", catpcha)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LoginNetwork.this.loginInfo = 1;
                        Log.d("login", "进入onError");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response.contains(mHost.getString(R.string.njunet_login_captcha_err))) {
                            LoginNetwork.this.loginInfo = 2;
                            Log.d("login", mHost.getString(R.string.njunet_login_captcha_err));
                            mHost.showNotice("验证码错误");
                        } else if (response.contains(mHost.getString(R.string.njunet_login_password_err))) {
                            LoginNetwork.this.loginInfo = 3;
                            Log.d("login", mHost.getString(R.string.njunet_login_password_err));
                            mHost.showNotice("密码错误");
                        } else if (response.contains(mHost.getString(R.string.njunet_login_timeout_err))) {
                            LoginNetwork.this.loginInfo = 4;
                            Log.d("login", mHost.getString(R.string.njunet_login_timeout_err));
                            mHost.showNotice("已超时");
                        } else {
                            LoginNetwork.this.loginInfo = 5;
                            Log.d("login", response);
                            mHost.showNotice("登录成功");
                            mHost.quit();
                        }
                    }
        });
    }
}
