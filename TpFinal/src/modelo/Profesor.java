package modelo;

public class Profesor extends Persona{
	private String legajo;

	public Profesor(String nombre, String apellido, long dni, String legajo) throws Exception {
		super(nombre, apellido, dni);
		this.legajo = legajo;
	}

	public String getLegajo() {
		return legajo;
	}

	
	

	@Override
	public String toString() {
		return  super.toString() + "    legajo: " + legajo+"\n\n";
	}
	
	public boolean equals(Profesor profesor) {
		return legajo == profesor.getLegajo();
	}
	
	public  String mostrarArchivoLectura() {
		return super.mostrarArchivoLectura()+"$"+legajo;
	}
	
}
