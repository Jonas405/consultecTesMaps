package vnzla.jonasleon8.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon on 14/01/2018.
 */

public class Login extends AppCompatActivity {

    //Button loginButton;
    Button buttonEnter, buttonReg;
    //EditText loginUserName, loginPassword;
    EditText editTextEmail, editTextPassword;
    String emailHolder;

    TextView registerTextView;
    private static String URL = "https://remote-admin.000webhostapp.com/login.php";
    private Snackbar snackbar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //loginUserName = (EditText) findViewById(R.id.loginEmail);
        //loginPassword = (EditText) findViewById(R.id.loginPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        pd = new ProgressDialog(Login.this);
        buttonEnter = (Button) findViewById(R.id.buttonEnter);
        buttonReg = (Button) findViewById(R.id.buttonReg);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  params.put("email", editTextEmail.getText().toString());
                params.put("password", editTextPassword.getText().toString()); */
                loginRequest();

                String usuario= editTextEmail.getText().toString();

                Intent miIntent=new Intent(Login.this, Home.class);
                Bundle miBundle=new Bundle();
                miBundle.putString("usuario",editTextEmail.getText().toString());
                miIntent.putExtras(miBundle);

                startActivity(miIntent);
                //Validar si el usuario y el email coinciden para entrar

             /*   if( editTextEmail.getText().toString().equals("email")
                        &editTextPassword.getText().toString().equals("password"))
                {
                    Toast.makeText(Login.this,"Email y password correctos",
                            Toast.LENGTH_SHORT).show();
                    Intent goHome = new Intent(Login.this, Home.class);
                    startActivity(goHome);
                }else{
                    Toast.makeText(Login.this,"Usuario no registrado",
                            Toast.LENGTH_LONG).show();
                }*/

            }
        });


       buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegistro = new Intent(Login.this, Registration.class);
                startActivity(goRegistro);
            }
        });
    }

    private void loginRequest(){
    //Validaciones

        final String username = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextEmail.setError("Porfavor ingrese email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Porfavor ingrese su password");
            editTextPassword.requestFocus();
            return;
        }

        //Fin de validaciones
        pd.setMessage("Signing In . . .");
        pd.show();

        RequestQueue queue = Volley.newRequestQueue(Login.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
// Validar si los datos son correctos ingresar
                        pd.hide();
                        showSnackbar(response);


                        if(response.equals("Login")) {

                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pd.hide();
                        Log.d("ErrorResponse", error.toString());

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", editTextEmail.getText().toString());
                params.put("password", editTextPassword.getText().toString());
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }

    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_LONG)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }
}