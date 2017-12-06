package vnzla.jonasleon8.controltest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    public static final String LOGIN_URL= "";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_PASSWORD= "password";
    public static final String LOGIN_SUCCESS= "success";
    public static final String SHARED_PREF_NAME= "tech";
    public static final String EMAIL_SHARED_PREF= "email";
    public static final String LOGGEDIN_SHARED_PREF= "loggedin";

    private boolean loggedIn=false;
    private EditText ediTextEmail;
    private EditText ediTextPassword;
    private Button buttonEnter;
    private Button buttonReg;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ediTextEmail= (EditText) findViewById(R.id.editTextEmail);
        ediTextPassword= (EditText) findViewById(R.id.editTextPassword);
        buttonEnter= (Button) findViewById(R.id.buttonEnter);
        buttonReg= (Button) findViewById(R.id.buttonReg);
    }

    public void onClick(View view) {

        Intent miIntent=null;

        switch (view.getId()){
            case R.id.buttonEnter:
               login();
                break;
            case R.id.buttonReg:
                miIntent=new Intent(MainActivity.this,RegActivity.class);
                break;
        }
        startActivity(miIntent);
    }

    private void login() {
        final String email=ediTextEmail.getText().toString().trim();
        final String password=ediTextPassword.getText().toString().trim();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                if (response.trim().equalsIgnoreCase(LOGIN_SUCCESS)){
                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME,
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.putBoolean(EMAIL_SHARED_PREF, true);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje10),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {


            protected Map<String,String>getPrams()throws AuthFailureError {

                Map<String,String>prams=new HashMap<>();
                prams.put(KEY_EMAIL, email);
                prams.put(KEY_PASSWORD, password);
                return prams;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


        private void Resume(){
            super.onResume();
            SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
            loggedIn=sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);
            if (loggedIn){
                Intent intent =new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }

    public void agregarBotonControl(){
        Button miControl = (Button) findViewById(R.id.buttonReg);
        miControl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }

}


