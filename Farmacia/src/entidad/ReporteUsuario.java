package entidad;

import java.util.ArrayList;

public class ReporteUsuario {
    private int codigo;
    private  String nomCompletos;
    private  String descrpTipo;

    public ReporteUsuario() {
    }
    public ReporteUsuario(int codigo, String nomCompletos, String descrpTipo){
        this.codigo=codigo;
        this.descrpTipo=descrpTipo;
        this.nomCompletos=nomCompletos;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNomCompletos(String nomCompletos) {
        this.nomCompletos = nomCompletos;
    }

    public void setDescrpTipo(String descrpTipo) {
        this.descrpTipo = descrpTipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNomCompletos() {
        return nomCompletos;
    }

    public String getDescrpTipo() {
        return descrpTipo;
    }
}
