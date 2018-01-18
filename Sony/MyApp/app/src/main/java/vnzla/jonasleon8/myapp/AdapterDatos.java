package vnzla.jonasleon8.myapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Leon on 14/01/2018.
 */

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> implements View.OnClickListener
{

    ArrayList<Equipo> listaEquipos;
    private View.OnClickListener listener;

    public AdapterDatos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.devicelist, null, false);
        //Agregamos el evento onClick manual y creamos que se mantenga escuchando nuestra lista
        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {

        holder.asignarDatos(listaEquipos.get(position));


    }

    @Override
    public int getItemCount() {
        return listaEquipos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener=listener;
    }

    @Override
    public void onClick(View view) {

        if (listener!=null){
            listener.onClick(view);
        }
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView txIdEquipo, txTemperatura, txVoltaje, txEstado, txPc;

        public ViewHolderDatos(View itemView) {
            super(itemView);

            txIdEquipo = (TextView) itemView.findViewById(R.id.id_equipo);
            txTemperatura = (TextView) itemView.findViewById(R.id.temperatura);
            txVoltaje = (TextView) itemView.findViewById(R.id.voltaje);
            txPc = (TextView) itemView.findViewById(R.id.pc);
            txEstado = (TextView) itemView.findViewById(R.id.estado);

        }

        public void asignarDatos(Equipo equipo) {

            txIdEquipo.setText(equipo.getIdEquipo());
            txTemperatura.setText(equipo.getTemperatura());
            txVoltaje.setText(equipo.getVoltaje());
            txPc.setText(equipo.getPc());
            txEstado.setText(equipo.getEstado());

        }
    }
}
