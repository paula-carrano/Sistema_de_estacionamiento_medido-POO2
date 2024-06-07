package ar.edu.unq.po2.Inspector;

import ar.edu.unq.po2.SEM.*;

public class Inspector {
    private String inspectorID;
    private Zona zona;
    private SEM sistema;

    public Inspector(String inspectorID, Zona zona, SEM sistema) {
        this.inspectorID = inspectorID;
        this.zona = zona;
        this.sistema = sistema;
    }

    public void altaInfraccion(String patente) {
        if (!this.verificarEstacionamiento(patente)) {
            sistema.generarInfraccion(patente, this);
        }
    }

    public boolean verificarEstacionamiento(String patente) {
        return sistema.verificarEstacionamientoConVigencia(patente);
    }

    // Getters y setters
    public String getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(String inspectorID) {
        this.inspectorID = inspectorID;
    }

    public String getZonaID() {
        return zona.getZonaID();
    }
}
