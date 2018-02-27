package com.lmp.timeflies.technique;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.lmp.timeflies.player.Play_FinDePartie;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ChronoService extends Service {
    public static String str_receiver = "com.lmp.timeflies.technique.receiver";
    //public static String str_receiver = "com.countdowntimerservice.receiver";
    private Handler mHandler = new Handler();
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String strDate;
    Date date_current, date_diff;
    SharedPreferences mpref;
    SharedPreferences.Editor mEditor;
    String str_testing="";

    private Timer mTimer = null;
    public static final long NOTIFY_INTERVAL = 1000;
    Intent intent;
    long long_mins;

    @Override
    public void onCreate() {
        super.onCreate();

        mpref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mpref.edit();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Log.i("creation du service", simpleDateFormat.toString());
        //Toast.makeText(getApplicationContext(), "creation du service : ",Toast.LENGTH_LONG).show();
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 5, NOTIFY_INTERVAL);
        intent = new Intent(str_receiver);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TimeDisplayTimerTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(new Runnable() {

                @Override
                public void run() {

                    calendar = Calendar.getInstance();
                    simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    strDate = simpleDateFormat.format(calendar.getTime());
                    Log.i("strDate : ", strDate);
                    twoDatesBetweenTime();

                }

            });
        }
    }

    private String twoDatesBetweenTime() {
        Log.i("twodates  : ", "twdabe");
        int int_minutes = Integer.valueOf(mpref.getString("minutes", ""));
        Log.i("twodates  mpref minutes : ", mpref.getString("minutes", ""));

        try {
            date_current = simpleDateFormat.parse(strDate);
        } catch (Exception e) {
            Log.i("twodates  exception  date current ",e.getMessage());
        }

        try {
            date_diff = simpleDateFormat.parse(mpref.getString("data",""));
        } catch (Exception e) {
            Log.i("twodates  exception  date diff ",e.getMessage());
        }

        try {

            //calcul du temps affiché
            long diff = date_current.getTime() - date_diff.getTime();
            //int int_hours = Integer.valueOf(mpref.getString("hours", ""));
            int_minutes = Integer.valueOf(mpref.getString("minutes", ""));
            Log.i("twodates  mpref minutes : ", mpref.getString("minutes", ""));
            //long int_timer = TimeUnit.HOURS.toMillis(int_hours);
            long int_timer = TimeUnit.MINUTES.toMillis(int_minutes);
            long_mins = int_timer - diff;

            Log.i("twodates  : ", ""+long_mins);
            mEditor.putLong("minutess",long_mins);
            long diffSeconds2 = long_mins / 1000 % 60;
            long diffMinutes2 = long_mins / (60 * 1000) % 60;
            //long diffHours2 = long_mins / (60 * 60 * 1000) % 24;
            //String str_testing="";
            if (long_mins > 0) {
                str_testing = diffMinutes2 + ":" + diffSeconds2;
                if(!mpref.getBoolean("finish", false)) {
                    fn_update(str_testing);
                } else {
                   fin_de_la_partie();
                }
            } else {
                mEditor.putBoolean("finish", true).commit();
                fin_de_la_partie();
            }
        }catch (Exception e){
            Log.i("twodates  exception : ",e.getMessage());
            mTimer.cancel();
            mTimer.purge();

        }

        return "";
    }

    private void fin_de_la_partie() {
        mTimer.cancel();
        Intent fin = new Intent(getApplicationContext(), Play_FinDePartie.class);
        fin.putExtra("time",str_testing);
        fin.putExtra("minutes",long_mins);
        startActivity(fin);
    }

    private void fn_update(String str_time) {
        intent.putExtra("time",str_time);
        Log.i("fn_update : ", str_time);
       // Toast.makeText(getApplicationContext(), "dans le service : " + str_time, Toast.LENGTH_SHORT).show();
        sendBroadcast(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.e("Service finish","Finish");
    }

}
