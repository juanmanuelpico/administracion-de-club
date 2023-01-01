package modelo;

public abstract class Persona {
	protected String nombre;
	protected String apellido;
	protected long dni;
	
	public Persona(String nombre, String apellido, long dni) throws Exception {
		this.nombre = nombre;
		this.apellido = apellido;
		setDni(dni);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) throws Exception{
		if(dni > 99999999 || dni < 1000000) // si el dni es mayor a 99 millones o menor que 1 millon, NO ES VALIDO
			throw new Exception ("el dni "+ dni + " es incorrecto");
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + "    Apellido: " + apellido + "    DNI: " + dni ;
	}
	
	public  String mostrarArchivoLectura() {
		return nombre+"$"+apellido+"$"+dni;
	}
	
	
	
}
