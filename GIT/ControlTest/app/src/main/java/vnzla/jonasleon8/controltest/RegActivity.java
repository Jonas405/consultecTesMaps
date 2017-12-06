package vnzla.jonasleon8.controltest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class RegActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText editTextNombre,editTextEmail,editTextPassword,editTextApellido;
    Button buttonRegistrar;
    ProgressDialog progreso;
    RequestQueue request;  //Referencia WebService
    JsonObjectRequest jsonObjectRequest; //para llamar WebService

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        editTextNombre= (EditText) findViewById(R.id.editTextNombre);
        editTextPassword= (EditText) findViewById(R.id.editTextPassword);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextApellido= (EditText) findViewById(R.id.editTextApellido);

        request= Volley.newRequestQueue(getBaseContext());

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });
    }
        private void cargarWebService() {
            progreso=new ProgressDialog(getBaseContext());
            progreso.setMessage("Cargando");
            progreso.show();
            String url="https://remote-admin.000webhostapp.com/Registro.php="+ editTextEmail.getText().toString()+"&nombre="+
                    editTextNombre.getText().toString()+"&apellido="+editTextApellido.getText().toString()+
                    "&password="+editTextPassword.getText().toString();

            url=url.replace(" ", "%20");

            jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            request.add(jsonObjectRequest);
        }


    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje10),
                Toast.LENGTH_SHORT).show();

        progreso.hide();
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje11),
                Toast.LENGTH_SHORT).show();


    }


}

