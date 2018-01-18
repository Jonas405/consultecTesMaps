package vnzla.jonasleon8.myapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon on 14/01/2018.
 */

public class QueyEnd extends AppCompatActivity {

    //EditText editext_temperatura, editext_voltaje, editext_estado;
    Button buttonApagar, buttonConsulta;
    TextView tvTmp, tvVol, tvEdo, tvIdEq, tvPc;
    String dp_email, dp_id_equipo, seleccion;

    private static String URL = "https://remote-admin.000webhostapp.com/query_end.php";
    private Snackbar snackbar;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryend);

        pd = new ProgressDialog(QueyEnd.this);

        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null){

            String seleccion=miBundle.getString("seleccion");
            //consultarEquipos(usuario);
        }

        // UBICAR DE PANTALLA ANTERIOR
        dp_email = "andreinaa.s25@gmail.com";
        dp_id_equipo = seleccion;

        // Declaración de botones
        buttonConsulta = (Button) findViewById(R.id.buttonConsulta);
        buttonApagar = (Button) findViewById(R.id.buttonApagar);

        // Declaración de Textos
        tvTmp = (TextView) findViewById(R.id.valTmp);
        tvVol = (TextView) findViewById(R.id.valVol);
        tvEdo = (TextView) findViewById(R.id.valEdo);
        tvIdEq = (TextView) findViewById(R.id.id_equipo);
        tvPc = (TextView) findViewById(R.id.pc);



        buttonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(QueyEnd.this,dp_id_equipo,
                        Toast.LENGTH_SHORT).show();
            }
        });

    /*    buttonApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               apagar();
            }
        });*/
    }


   /*     private void consulta(final String seleccion)
        {
            pd.setMessage("Consultado datos de equipos...");
            pd.show();

            RequestQueue queue = Volley.newRequestQueue(QueyEnd.this);
            String response = null;

            final String finalResponse = response;

            StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {

                            pd.hide();
                            showSnackbar(response);

                            try {
                                //Transformamos de String a JSONObject
                                JSONObject jsonObject = new JSONObject(response);

                                System.out.println(jsonObject);

                                int status = jsonObject.getInt("status");
                                String msg = jsonObject.getString("msg");
                                String st, stAux;

                                if ( status == 1 ) {
                                    System.out.println("acaaaaaa..");

                                    //Tomamos el Array en la posicion 0 y lo estoy llevando a JSONOBJECT
                                    JSONArray losEquipos = jsonObject.getJSONArray("data");

                                    st = losEquipos.getJSONObject(0).getString("flag_apagado");

                                    if ( st.equals("0") ) {
                                        stAux = "Estado: Encendido";
                                    } else {
                                        stAux = "Estado: Apagado";
                                    }

                                    System.out.println(losEquipos);
                                    tvIdEq.setText("Equipo: " + losEquipos.getJSONObject(0).getString("id_equipo") );
                                    tvPc.setText("Maquina: " + losEquipos.getJSONObject(0).getString("pc_usuario") );
                                    tvTmp.setText("Temperatura: " + losEquipos.getJSONObject(0).getString("temperatura") );
                                    tvVol.setText("Voltaje: " + losEquipos.getJSONObject(0).getString("voltaje") );
                                    tvEdo.setText(stAux);

                                    //System.out.println(losEquipos.getJSONObject(0).getString("voltaje"));

                                } else {

                                    pd.hide();
                                    // Mostrar mensaje
                                    Toast.makeText(QueyEnd.this, "No se encontraron equipos", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        *//*
                        if(response.equals("Login")) {

                            startActivity(new Intent(getApplicationContext(), Welcome.class));
                        }*//*
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
                {   //Estos son los parametros de consulta para el web Service
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("correo",  dp_email );
                    params.put("equipo", seleccion);
                    return params;
                }
            };
            postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(postRequest);

        }

    private void apagar()
    {
        final String URL2, user, pc;

        // Cambiar estos valores por los del usuario sesionado
        user = "andreinaa.s25@gmail.com";
        pc = "pc_andre";

        URL2 = "https://remote-admin.000webhostapp.com/bandera.php";

        pd.setMessage("Apagando equipo...");
        pd.show();

        RequestQueue queue = Volley.newRequestQueue(QueyEnd.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL2,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        pd.hide();
                        showSnackbar("Se ha apagado equipo.");
                        consulta(seleccion);

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
            {   //Estos son los parametros de consulta para el web Service
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user",  user );
                params.put("pc", pc);
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
*/
}


