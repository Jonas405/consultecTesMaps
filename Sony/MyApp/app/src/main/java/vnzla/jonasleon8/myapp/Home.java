package vnzla.jonasleon8.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon on 14/01/2018.
 */

public class Home extends AppCompatActivity {

    Button buttonConsulta;
    ArrayList<Equipo> listaEquipos;
    RecyclerView recycler;
    AdapterDatos adapter;
    private ProgressDialog pd;
    private Snackbar snackbar;
    private static String URL = "http://remote-admin.000webhostapp.com/lista_equipos.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
   //     buttonConsulta = (Button) findViewById(R.id.buttonConsulta);


        recycler = (RecyclerView) findViewById(R.id.recyclerEquipo);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pd = new ProgressDialog(Home.this);

        listaEquipos = new ArrayList<Equipo>();

        pd.setMessage("Consultando equipos ...");
        pd.show();

        Bundle miBundle=this.getIntent().getExtras();

        if (miBundle!=null){

            String usuario=miBundle.getString("usuario");
            consultarEquipos(usuario);
        }




     adapter = new AdapterDatos(listaEquipos);

        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                 Toast.makeText(getApplicationContext(),"Consultado equipo "+listaEquipos.get(recycler.getChildAdapterPosition(view))
                        .getIdEquipo(),Toast.LENGTH_LONG).show();

//String sDesignation= employeeList.get(0).get("designation");

                String seleccion= listaEquipos.get(recycler.getChildAdapterPosition(view))
                        .getIdEquipo();

                Intent miIntent=new Intent(Home.this, QueyEnd.class);
                Bundle miBundle=new Bundle();

                miBundle.putString("seleccion",listaEquipos.get(recycler.getChildAdapterPosition(view))
                        .getIdEquipo());
                miIntent.putExtras(miBundle);
                startActivity(miIntent);


            }
        });

  /*      buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goRegistro = new Intent(Login.this, Registration.class);
                startActivity(goRegistro);
            }
        });
*/
        recycler.setAdapter(adapter);
    }


    private void consultarEquipos(final String usuario)
    {

        RequestQueue queue = Volley.newRequestQueue(Home.this);
        String response = null;
        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);
                        // Response from the server is in the form if a JSON, so we need a JSON Object
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            System.out.println(jsonObject);

                            int status = jsonObject.getInt("status");
                            String msg = jsonObject.getString("msg");

                            if ( status == 1 ) {

                                JSONArray losEquipos = jsonObject.getJSONArray("data");
                                listaEquipos.clear();

                                for (int i = 0; i < losEquipos.length(); i++ ) {

                                    JSONObject objectInArray = losEquipos.getJSONObject(i);

                                    Equipo auxEquipo = new Equipo(
                                            objectInArray.getString("id_equipo"),
                                            objectInArray.getString("temperatura"),
                                            objectInArray.getString("voltaje"),
                                            objectInArray.getString("pc_usuario"),
                                            objectInArray.getString("flag_apagado")
                                    );

                                    listaEquipos.add(auxEquipo);
                                }

                                System.out.println(listaEquipos);
                                pd.hide();
                                adapter.notifyDataSetChanged();

                            } else {

                                pd.hide();
                                // Mostrar mensaje
                                Toast.makeText(Home.this, "No se encontraron equipos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pd.hide();
                        System.out.println(error);
                        Log.d("ErrorResponse", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("correo", usuario);
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
