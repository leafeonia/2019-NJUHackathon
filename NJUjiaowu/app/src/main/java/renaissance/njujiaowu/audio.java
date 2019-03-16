package renaissance.njujiaowu;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class audio extends AppCompatActivity {
//    private Button bplay,bpause,bstop;
//
//    private MediaPlayer mp = MediaPlayer.create(this,R.raw.audio1);
//
//
//
//    @Override
//
//    public void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_audio);
//
//
//
//        bplay = (Button)findViewById(R.id.play);
//
//        bpause = (Button)findViewById(R.id.pause);
//
//        bstop = (Button)findViewById(R.id.stop);
//
//        bplay.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//
//            public void onClick(View v) {
//
//                try {
//
//                    mp.setDataSource("/res/raw/audio1.mp3");
//
//                    mp.prepare();
//
//                    mp.start();
//
//                } catch (IllegalArgumentException e) {
//
//                    e.printStackTrace();
//
//                } catch (IllegalStateException e) {
//
//                    e.printStackTrace();
//
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//
//                }
//
//                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
//
//                    @Override
//
//                    public void onCompletion(MediaPlayer mp) {
//
//                        mp.release();
//
//                    }
//
//                });
//
//            }
//
//        });
//
//
//
//        bpause.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//
//            public void onClick(View v) {
//
//                if(mp != null){
//
//                    mp.pause();
//
//                }
//
//            }
//
//        });
//
//
//
//        bstop.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//
//            public void onClick(View v) {
//
//                if(mp != null){
//
//                    mp.stop();
//
//                }
//
//            }
//
//        });
//
//    }
//
//
//
//    @Override
//
//    protected void onDestroy() {
//
//        if(mp != null)
//
//            mp.release();
//
//        super.onDestroy();
//
//    }

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mediaPlayer = MediaPlayer.create(this,R.raw.audio1);
//        try {
//            mediaPlayer.setDataSource("/res/raw/audio1.mp3");
////            mediaPlayer.prepare();
////            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        playAudio();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        // 通过异步的方式装载媒体资源
//        mediaPlayer.prepareAsync();
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                // 装载完毕回调
//                 mediaPlayer.start();
//            }
//        });
    }

    private void playAudio(){
//        MediaPlayer mediaPlayer;
//        mediaPlayer = MediaPlayer.create(this, R.raw.test_audio_1);
        if (!mediaPlayer.isPlaying())
            mediaPlayer.start();
        else if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop(); mediaPlayer.start();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            Intent i = new Intent(audio.this,MainActivity.class);
            startActivity(i);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
