package ar.edu.unq.po2.Inspector;

import ar.edu.unq.po2.SEM.*;

public class Inspector {
    
	private String inspectorID;
    private Zona zona;
    private SEM sistema;

    public Inspector(String inspectorID, SEM sistema) {
        this.setSistema(sistema);
        this.setInspectorID(inspectorID);
        this.setZona(zona);
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

	private void setZona(Zona zona2) {
		this.zona=zona2;
	}

    public String getZona() {
        return this.zona.getZonaID();
    }

	public void altaInfraccion(String patente) {
        if (!this.verificarEstacionamiento(patente)) {
            sistema.generarInfraccion(patente, this);
        }
    }

    public boolean verificarEstacionamiento(String patente) {
        return sistema.verificarEstacionamientoConVigencia(patente);
    }

 
    
}
