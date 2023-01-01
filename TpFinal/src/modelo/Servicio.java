package modelo;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Servicio {
	private int idServicio;
	private String nombreServicio;
	private List<Alquiler> lstAlquileres;
	private double precioHora;

	
	
	
	public Servicio(int idServicio, String nombreServicio, double precioHora) {
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
		this.lstAlquileres = new ArrayList<Alquiler>();
		this.precioHora = precioHora;
		
	}

	public int getIdServicio() {
		return idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	
	public List<Alquiler> getLstAlquileres() {
		return lstAlquileres;
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}

	@Override
	public String toString() {
		return "Id: " + idServicio + "    Nombre: " + nombreServicio + "    precio hora: " + precioHora;
	}
	
	public  String mostrarArchivoLectura() {
		return nombreServicio+"$"+precioHora;
	}
	
//	este metodo retorna una lista de alquileres en la misma fecha
	public List<Alquiler> traerAlquileres(LocalDate fecha) {
		List<Alquiler> alquileresFecha = new ArrayList<Alquiler>();
		
		for(Alquiler alquiler : lstAlquileres) {
			if(alquiler.getFecha().equals(fecha))
				alquileresFecha.add(alquiler);
		}
		
		return alquileresFecha;
	}

	public Alquiler traerAlquiler(int id) {
		Alquiler alquiler = null;
		int i = 0;
		while(alquiler == null && i < lstAlquileres.size()) {
			if(lstAlquileres.get(i).getId() == id)
				alquiler = lstAlquileres.get(i);
			
			i++;
		}
		return alquiler;
	}
//	este metodo realiza una busqueda sobre la lista de alquileres que se realicen en la misma fecha en la que se quiere alquilar
// 1)empieza a buscar si la hora de inicio del alquiler de la lista en la posicion que indica el iterador es igual a la del parametro
// 2)sino, busca si la hora de inicio esta entre la hora de inicio y la hora de finalizacion del alquiler
//	3)sino, busca si la hora de finalizacion interrumpe (esta en el medio) de los horarios desde y hasta del alquiler
// 4)sino, se fija si la hora de finalizacion es igual a la del alquiler
//	5) sino, por ultimo se fija si los horarios mandados por parametro comprenden por dentro a los horarios del alquiler, es decir, se fija si empieza antes del alquiler y termina despues
// si se cumple alguna de las condiciones del 1) al 5) entonces la variable esValido valdra false, de este modo saldra del bucle y se retornara dejando en claro que los horarios 
//	del alquiler a ingresar interrumpen los horarios de un alquiler existente
	public boolean esAlquilerValido(LocalDate fecha, LocalTime desde, LocalTime hasta) {
		List<Alquiler> alquileresFecha = traerAlquileres(fecha);
		boolean esValido = true;
		int i = 0;
		if(!alquileresFecha.isEmpty()) {
		while(esValido && i < alquileresFecha.size()) {
			Alquiler aux = alquileresFecha.get(i);
			if(desde.equals(aux.getHoraDesde()) || (desde.isAfter(aux.getHoraDesde()) && desde.isBefore(aux.getHoraHasta())) ||
					(hasta.isAfter(aux.getHoraDesde()) && hasta.isBefore(aux.getHoraHasta())) || (hasta.equals(aux.getHoraHasta())) 
						|| desde.isBefore(aux.getHoraDesde()) && hasta.isAfter(aux.getHoraHasta()))
				esValido = false;
			
			i++;
		}
	   }
		return esValido;
	}

	
	public boolean alquilar(LocalDate fecha, LocalTime desde, LocalTime hasta) throws Exception {
		if(!esAlquilerValido(fecha, desde, hasta))
			throw new Exception("ERROR : los horarios del alquiler a agregar interrumpen los horarios de un alquiler existente");
		
		return lstAlquileres.add(new Alquiler(calcularId(), fecha, desde, hasta));
	}
	
	public int calcularId() {
		int id = 1;
		
		if(!lstAlquileres.isEmpty())
			id = lstAlquileres.get(lstAlquileres.size()-1).getId() + 1;
		
		return id;
	}

	public boolean eliminarAlquiler(int idAlquiler) throws Exception {
		Alquiler eliminar = traerAlquiler(idAlquiler);
		if(eliminar == null)
			throw new Exception("Error: el alquiler con id "+ idAlquiler+" no existe");
		
		return lstAlquileres.remove(eliminar);
	}
	
	public boolean eliminarAlquiler(Alquiler alquiler) {
		return lstAlquileres.remove(alquiler);
	}

	public double precioTotalAlquiler(Alquiler alquiler) {
		double horas = alquiler.getHoraHasta().getHour() - alquiler.getHoraDesde().getHour();
		return horas * precioHora;
	}
	
	

	
}