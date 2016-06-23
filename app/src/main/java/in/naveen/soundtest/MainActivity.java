package in.naveen.soundtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity {
    ImageButton l, c, r;
    MediaPlayer mL, mC, mR;
    SeekBar volumeSeekbar;
    AudioManager audio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l = (ImageButton) findViewById(R.id.imageViewL);

        c = (ImageButton) findViewById(R.id.imageViewC);

        r = (ImageButton) findViewById(R.id.imageViewR);

        volumeSeekbar = (SeekBar) findViewById(R.id.seekBar);



        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        mC = MediaPlayer.create(this, R.raw.center);
        mL = MediaPlayer.create(this, R.raw.left);
        mR = MediaPlayer.create(this, R.raw.right);
        final int maxvolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        final int currentvolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeSeekbar.setMax(maxvolume);
        volumeSeekbar.setProgress(currentvolume);



//code to change change system volume

        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int music_volume, boolean fromUser) {

                audio.setStreamVolume(AudioManager.STREAM_MUSIC, music_volume, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//play left channel
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mL.start();
            }
        });
//play center channel
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mC.start();
            }
        });
//play right channel
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mR.start();

            }
        });


    }

//code to create menu item
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_res, menu);
        return true;
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            //exit the app
            case R.id.exit:
                this.finish();
                return true;

            //about the app
            case R.id.about:
                new AlertDialog.Builder(MainActivity.this)

                        .setTitle("About")
                        .setMessage("App Developed by sngh.naveen@gmail.com")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        })
                        .setIcon(null)
                        .show();

                return true;

            //help code
            case R.id.help:


              final Dialog dialog =new Dialog(this);
                dialog.setTitle("Help");
                dialog.setContentView(R.layout.custom_dialog);

                dialog.show();




                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
