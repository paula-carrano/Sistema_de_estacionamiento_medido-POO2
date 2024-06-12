package ar.edu.unq.po2.Inspector;

import ar.edu.unq.po2.SEM.*;

public class Inspector {
    
	private String inspectorID;
    private String zonaID;
    private SEM sistema;

    public Inspector(String inspectorID, SEM sistema, String zonaID) {
        this.setSistema(sistema);
        this.setInspectorID(inspectorID);
        this.setZonaID(zonaID);
    }


	public void altaInfraccion(String patente) {
        if (!this.verificarEstacionamiento(patente)) {
            sistema.generarInfraccion(patente, this);
        }
    }

    public boolean verificarEstacionamiento(String patente) {
        return sistema.verificarEstacionamientoConVigencia(patente);
    }

    // Getters y setters.
    public String getInspectorID() {
        return inspectorID;
    }

    public void setInspectorID(String inspectorID) {
        this.inspectorID = inspectorID;
    }
    
    private void setSistema(SEM sistema2) {
		this.sistema=sistema2;
    }

    public String getZonaID() {
        return zonaID;
    }
    
    public void setZonaID(String id) {
    	this.zonaID=id;
    }
}
