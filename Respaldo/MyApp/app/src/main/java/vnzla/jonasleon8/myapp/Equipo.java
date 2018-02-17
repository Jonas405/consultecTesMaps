package vnzla.jonasleon8.myapp;

/**
 * Created by Leon on 14/01/2018.
 */

public class Equipo {

    private String idEquipo;
    private String temperatura;
    private String estado;
    private String voltaje;
    private String pc;

    public Equipo(String idEquipo, String temperatura, String voltaje, String pc, String estado) {
        this.idEquipo = idEquipo;
        this.temperatura = temperatura;
        this.voltaje = voltaje;
        this.pc = pc;
        this.estado = estado;
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getEstado() {

        if ( estado.equals("0") ) {
            return "Encendido";
        } else {
            return "Apagado";
        }
        //return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(String voltaje) {
        this.voltaje = voltaje;
    }
}
