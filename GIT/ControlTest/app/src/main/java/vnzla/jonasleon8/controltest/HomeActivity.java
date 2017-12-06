package vnzla.jonasleon8.controltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Leon on 17/11/2017.
 */

public class HomeActivity extends AppCompatActivity {

    ImageButton botonConsulta, botonApagar, botonEliminar, imageButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        botonConsulta = (ImageButton) findViewById(R.id.botonConsulta);
        botonApagar = (ImageButton) findViewById(R.id.botonApagar);
        botonEliminar = (ImageButton) findViewById(R.id.botonEliminar);
        imageButton1 = (ImageButton) findViewById(R.id.imageButton1);

        botonConsulta.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                Intent intent = new Intent(HomeActivity.this, QueryActivity.class);
                startActivity(intent);
            }
        });

        // Este boton es el que cambia la bandera   botonApagar
        // Este boton es el que elimina el equipo botonEliminar

        imageButton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getBaseContext(), getResources().getString(R.string.mensaje13),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.menu_logout:
                finish();
            case R.id.menu_forget_logout:
                break;
        }

        }    */
    }

