package vnzla.jonasleon8.controltest;

/**
 * Created by Leon on 02/12/2017.
 */

public class Consulta {
    private Integer id_equipo;

    public Integer getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(Integer id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getValorV() {
        return valorV;
    }

    public void setValorV(String valorV) {
        this.valorV = valorV;
    }

    public String getValorT() {
        return valorT;
    }

    public void setValorT(String valorT) {
        this.valorT = valorT;
    }

    public Boolean getFlag_apagado() {
        return flag_apagado;
    }

    public void setFlag_apagado(Boolean flag_apagado) {
        this.flag_apagado = flag_apagado;
    }

    private String valorV;
    private String valorT;
    private Boolean flag_apagado;



}
