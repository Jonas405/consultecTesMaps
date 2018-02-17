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
import android.widget.CheckBox;
import android.widget.EditText;

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

public class Registration extends AppCompatActivity
{

    private static String S_URL ="https://remote-admin.000webhostapp.com/registro_user2.php";

    EditText editTextNombre, editTextApellido, editTextEmail, editTextPassword;
    Button buttonRegistrar, buttonVolver;


    CheckBox checkBoxTerms;

    // Material design
    private Snackbar snackbar;
    private ProgressDialog pd;

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);
    pd = new ProgressDialog(Registration.this);
        /*
        signupButton =(Button)findViewById(R.id.signupButton);
        signUpEmail = (EditText)findViewById(R.id.signUpEmail);
        signUpName =(EditText)findViewById(R.id.signUpName);
        signUpPassword = (EditText)findViewById(R.id.signUpPassword);
        checkBoxTerms = (CheckBox)findViewById(R.id.checkBoxTerms);
        */
    // Declaración de inputs
    editTextNombre = (EditText) findViewById(R.id.editTextNombre);
    editTextApellido = (EditText) findViewById(R.id.editTextApellido);
    editTextEmail = (EditText) findViewById(R.id.editTextEmail);
    editTextPassword = (EditText) findViewById(R.id.editTextPassword);

    // Declaración de botones
    buttonRegistrar =(Button) findViewById(R.id.buttonRegistrar);
    buttonVolver =(Button) findViewById(R.id.buttonVolver);

    buttonRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            signupRequest();
                /*
                if(checkBoxTerms.isChecked() == true){

                    signupRequest();

                }else{

                    Toast.makeText(getApplicationContext(),"Please Accept Terms & Services",Toast.LENGTH_SHORT).show();
                }*/
        }
    });

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverLogin = new Intent(Registration.this, Login.class);
                startActivity(volverLogin);
            }
        });
}


    private void signupRequest(){

        final String nombre = editTextNombre.getText().toString();
        final String apellido = editTextApellido.getText().toString();
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(nombre)) {
            editTextEmail.setError("Porfavor ingrese el nombre");
            editTextEmail.requestFocus();
            return;
        }

         if (TextUtils.isEmpty(apellido)) {
            editTextApellido.setError("Porfavor ingrese su Apellido");
            editTextApellido.requestFocus();
            return;
             }

         if (TextUtils.isEmpty(email)) {
                editTextEmail.setError("Porfavor ingrese su Email");
                editTextEmail.requestFocus();
                return;
            }

         if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Porfavor ingrese su password");
                    editTextPassword.requestFocus();
                    return;
            }

        pd.setMessage("Registrando usuario...");
        pd.show();


            RequestQueue queue = Volley.newRequestQueue(Registration.this);
            String response = null;
            final String finalResponse = response;

            StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            pd.hide();

                            showSnackbar("Usuario Registrado!");

    /*
                            if(response.equals("Successfully Signed In")) {
                                startActivity(new Intent(getApplicationContext(), Login.class));
                            }
    */
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
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
                    params.put("nombre", editTextNombre.getText().toString());
                    params.put("apellido", editTextApellido.getText().toString());

                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(postRequest);


    }


    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }


}
