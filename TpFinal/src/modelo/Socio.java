package modelo;

public class Socio extends Persona{
	private String nroCarnet;

	public Socio(String nombre, String apellido, long dni, String nroCarnet) throws Exception {
		super(nombre, apellido, dni);
		this.nroCarnet = nroCarnet;
	}

	public String getNroCarnet() {
		return nroCarnet;
	}

	public void setNroCarnet(String nroCarnet) {
		this.nroCarnet = nroCarnet;
	}
	
	@Override
	public String toString() {
		return  super.toString() +"    nroCarnet: " + nroCarnet;
	}
	
	public  String mostrarArchivoLectura() {
		return super.mostrarArchivoLectura()+"$"+ nroCarnet;
	}
	
	
	

}
