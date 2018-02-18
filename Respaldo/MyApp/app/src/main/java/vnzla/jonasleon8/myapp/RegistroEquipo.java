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
import android.widget.TextView;

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
 * Created by Leon on 27/01/2018.
 */

public class RegistroEquipo extends AppCompatActivity {

    private static String S_URL ="https://remote-admin.000webhostapp.com/registrar_pc100.php";

    TextView email, maquina, estado;
    EditText editTextEmail, editTextMaquina, editTextEstado, editTextid_equipo;
    Button buttonRegistrar;
    private Snackbar snackbar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_equipo);
        pd = new ProgressDialog(RegistroEquipo.this);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMaquina = (EditText) findViewById(R.id.editTextMaquina);
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextid_equipo = (EditText) findViewById(R.id.editTextId_equipo);
        buttonRegistrar = (Button) findViewById(R.id.buttonRegistrar);



        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EquipoRegistrado();
            }
        });

    }


    private void EquipoRegistrado(){

        final String email = editTextEmail.getText().toString();
        final String maquina = editTextMaquina.getText().toString();
        final String estado = editTextEstado.getText().toString();
        final String id_equipo = editTextid_equipo.getText().toString();


        //Validando campos
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Porfavor ingrese el email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(maquina)) {
            editTextMaquina.setError("Porfavor ingrese su maquina");
            editTextMaquina.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(id_equipo)) {
            editTextid_equipo.setError("Porfavor ingrese el Numero de equipo");
            editTextid_equipo.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(estado)) {
            editTextEstado.setError("Porfavor ingrese el estado");
            editTextEstado.requestFocus();
            return;
        }




        pd.setMessage("Registrando Equipo...");
        pd.show();

        RequestQueue queue = Volley.newRequestQueue(RegistroEquipo.this);
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        showSnackbar("Equipo Registrado!");

                        editTextEmail.setText(" ");
                        editTextMaquina.setText(" ");
                        editTextEstado.setText(" ");
                        editTextid_equipo.setText(" ");

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

                params.put("correo_usuario", editTextEmail.getText().toString());
                params.put("pc_usuario", editTextMaquina.getText().toString());
                params.put("id_equipo", editTextid_equipo.getText().toString());
                params.put("flag_apagado", editTextEstado.getText().toString());

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

