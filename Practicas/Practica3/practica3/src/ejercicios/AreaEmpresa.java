package ejercicios;

public class AreaEmpresa {
	private  String id;
	private int tardanza;
	
	public AreaEmpresa(String id, int tardanza) {
		this.id = id;
		this.tardanza = tardanza;
	}
	
	public String getId() {
		return this.id;
	}
	public int getTardanza() {
		return this.tardanza;
	}
}
