package vnzla.jonasleon8.myapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

/**
 * Created by Leon on 17/01/2018.
 */

public class SplashActivity extends Activity {
    private final int DURACION_SPLASH = 2000;
    private ProgressDialog pd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(SplashActivity.this, Login.class);
                startActivity(intent);
                finish();

            };
        }, DURACION_SPLASH);

        pd = new ProgressDialog(SplashActivity.this);
        pd.setMessage("ControlTest");
        pd.show();
    }
}