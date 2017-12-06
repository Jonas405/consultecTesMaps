package vnzla.jonasleon8.controltest;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Leon on 17/11/2017.
 */

public class QueryActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    EditText id_equipo;
    TextView temperatura, voltaje, valorV, valorT;
    Button botonConsulta2;
    ProgressDialog progeso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        valorV = (TextView) findViewById(R.id.valorV);
        valorT = (TextView) findViewById(R.id.valorT);
        request= Volley.newRequestQueue(getBaseContext());

        botonConsulta2.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                cargarWebService();
            }
        });
    }

    private void cargarWebService(){
        progeso=new ProgressDialog(getBaseContext());
        progeso.setMessage("Consultando...");
        progeso.show();

        String url="https://remote-admin.000webhostapp.com/leer2.php?id_equipo"
                +id_equipo.getText().toString();
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onResponse(JSONObject response){
        progeso.hide();
        Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje12),
                Toast.LENGTH_SHORT).show();

        Consulta miConsulta=new Consulta();

        JSONArray json=response.optJSONArray("resultado");
        JSONObject jsonObject=null;

            try{
                    jsonObject=json.getJSONObject(0);
                    miConsulta.setValorT(jsonObject.optString("temperatura"));
                    miConsulta.setValorV(jsonObject.optString("voltaje"));
                }catch (JSONException e){
                    e.printStackTrace();
            }
        valorT.setText(miConsulta.getValorT());
        valorV.setText(miConsulta.getValorV());

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }



}



