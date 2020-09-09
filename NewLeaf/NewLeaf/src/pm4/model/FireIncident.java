package pm4.model;

public class FireIncident {
	protected int fireIncidentPK;
	protected Neighborhood neighborhood;
	
	public FireIncident(int fireIncidentPK, Neighborhood neighborhood) {
		this.fireIncidentPK = fireIncidentPK;
		this.neighborhood = neighborhood;
	}

	public int getFireIncidentPK() {
		return fireIncidentPK;
	}

	public void setFireIncidentPK(int fireIncidentPK) {
		this.fireIncidentPK = fireIncidentPK;
	}

	public Neighborhood getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
}
