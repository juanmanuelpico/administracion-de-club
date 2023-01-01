package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AdministracionClub {
	private List<Actividad> lstActividades;
	private List<Servicio> lstServicios;
	private List<Socio> lstSocios;
	private List<Profesor> lstProfesores;
	
	
	public AdministracionClub() {
		this.lstActividades = new ArrayList<Actividad>();
		this.lstServicios = new ArrayList<Servicio>();
		this.lstSocios = new ArrayList<Socio>();
		this.lstProfesores = new ArrayList<Profesor>();
		
	}

	public List<Actividad> getLstActividades() {
		return lstActividades; 
	}

	public List<Servicio> getLstServicios() {
		return lstServicios;
	}

	public List<Socio> getLstSocios() {
		return lstSocios;
	}

	public List<Profesor> getLstProfesores() {
		return lstProfesores;
	}
	
//	----------------------------------------
//    PROFESORES
//----------------------------------------
	
	public Profesor traerProfesor(String nroLegajo) {
		Profesor profesor = null;
		int i = 0;
		while(profesor == null && i < lstProfesores.size()) {
			if(lstProfesores.get(i).getLegajo().equalsIgnoreCase(nroLegajo))
				profesor = lstProfesores.get(i);
			
			i++;
		}
	    return profesor;
	}
	
	public Profesor traerProfesor(long dni) {
		Profesor profesor = null;
		int i = 0;
		while(profesor == null && i < lstProfesores.size()) {
			if(lstProfesores.get(i).getDni() == dni)
				profesor = lstProfesores.get(i);
			
			i++;
		}
		
		return profesor;
	}
	
	public boolean agregarProfesorAlClub(String nombre, String apellido, long dni, String legajo) throws Exception {
		if(traerProfesor(legajo) != null )
			throw new Exception("ERROR: ya hay un profesor con el numero de legajo "+ legajo +" en el club");
		if(traerProfesor(dni) != null )
			throw new Exception("ERROR: ya hay un profesor con el numero de DNI "+ dni +" en el club");
		return lstProfesores.add(new Profesor(nombre, apellido, dni, legajo));
	}
	
	public boolean eliminarProfesorAlClub(String legajo) throws Exception {
		Profesor profesor = traerProfesor(legajo);
		if(profesor == null )
			throw new Exception("ERROR: no existe un profesor con el numero de legajo "+ legajo +" en el club");
		//antes de eliminar al profesor del club tenemos que eliminarlo de la actividad/es que dirija, esto lo haremos dandole el valor de null
		for(Actividad a : lstActividades) {
			if(a.getProfesor().equals(profesor))
				a.setProfesor(null);
		}
		
		
		return lstProfesores.remove(profesor);
	}
	
	public void modificarProfesor(String nombre, String apellido, long dni, String legajo) throws Exception {
		Profesor profesor = traerProfesor(legajo);
		if(profesor == null )
			throw new Exception("ERROR: no existe un profesor con el numero de legajo "+ legajo +" en el club");
		
		// si el dni del profesor es distinto al que viene por parametro se busca si ese dni del parametro corresponde a algun profesor
		// si pertenece a algun profesor entonces tira una Excepcion, si el dni no corresponde a otro profe entonces se modifica
		if(profesor.getDni() != dni) {
			if(traerProfesor(dni) != null)
				throw new Exception("Error, ya hay otro profesor con el dni nro "+dni);
			else
				profesor.setDni(dni);
		}
		
		profesor.setNombre(nombre);
		profesor.setApellido(apellido);
	}
//	----------------------------------------
//  SERVICIOS
//----------------------------------------
	
	public Servicio traerServicio(int id) {
		Servicio servicio = null;
		int i = 0;
		while(servicio == null && i < lstServicios.size()) {
			if(lstServicios.get(i).getIdServicio() == id)
					servicio = lstServicios.get(i);
			
			i++;
		}
		return servicio;
	}
	
	public Servicio traerServicio(String nombre) {
		Servicio servicio = null;
		int i = 0;
		while(servicio == null && i < lstServicios.size()) {
			if(lstServicios.get(i).getNombreServicio().equalsIgnoreCase(nombre))
					servicio = lstServicios.get(i);
			
			i++;
		}
		return servicio;
	}
	
	

	
	public int calcularIdServicio() {
		int id = 1;
		if(!lstServicios.isEmpty())
			id = lstServicios.get(lstServicios.size()-1).getIdServicio() + 1;
		
		return id;
	}
	
	public boolean agregarServicio(String nombreServicio, double precioHora) throws Exception {
		if(traerServicio(nombreServicio) != null)
			throw new Exception("Error: el servicio con el nombre "+ nombreServicio+" ya existe");
		
	    return lstServicios.add(new Servicio(calcularIdServicio(), nombreServicio, precioHora));
	}
	
	
	public boolean eliminarServicio(int id) throws Exception {
		Servicio eliminar = traerServicio(id);
		if(eliminar == null)
			throw new Exception("ERROR: el servicio con id "+ id +" no existe");
		// para eliminar un servicio primero hay que eliminar todos los alquileres 
		for(Alquiler a : eliminar.getLstAlquileres())
			eliminar.eliminarAlquiler(a);
		
		return lstServicios.remove(eliminar);
	}
	
	public boolean alquilarServico(String nombreServicio, LocalDate fecha,
			LocalTime desde, LocalTime hasta) throws Exception {
		
		Servicio servicio = traerServicio(nombreServicio);
		if(servicio == null)
			throw new Exception("Error: el servicio con nombre "+ nombreServicio +" no existe");
		
		return servicio.alquilar(fecha, desde, hasta);
	}
	
	public boolean eliminarAlquilarServico(String nombreServicio, int idAlquiler) throws Exception {
		
		Servicio servicio = traerServicio(nombreServicio);
		if(servicio == null)
			throw new Exception("Error: el servicio con nombre "+ nombreServicio +" no existe");
		Alquiler alquiler = servicio.traerAlquiler(idAlquiler);
		if(alquiler == null)
			throw new Exception("Error: el alquiler con id "+ idAlquiler +" no existe en el servicio "+ servicio.getNombreServicio());
		
		return servicio.eliminarAlquiler(alquiler);
	}
	
	
	
	
	

	
	
	
	
	
	
//	----------------------------------------
//  SOCIOS
//----------------------------------------
	
	public Socio traerSocioEnClub(String nroCarnet) {
		Socio socio = null;
		int i = 0;
		while(socio == null && i < lstSocios.size()) {
			if(lstSocios.get(i).getNroCarnet().equals(nroCarnet))
				socio = lstSocios.get(i);
			
			i++;
		}
		
		return socio;
	}
	
	public Socio traerSocioEnClub(long dni) {
		Socio socio = null;
		int i = 0;
		while(socio == null && i < lstSocios.size()) {
			if(lstSocios.get(i).getDni() == dni)
				socio = lstSocios.get(i);
			
			i++;
		}
		
		return socio;
	}
	
	
	public boolean agregarSocioAlClub(String nombre, String apellido, long dni, String nroCarnet) throws Exception {
		if(traerSocioEnClub(nroCarnet) != null)
			throw new Exception("ERROR: el socio con carnet numero "+ nroCarnet+ " ya pertenece al club");
		if(traerSocioEnClub(dni) != null)
			throw new Exception("ERROR: ya hay un socio con el dni "+ dni+ " en el club");
		
		return lstSocios.add(new Socio(nombre, apellido, dni, nroCarnet));
	}
	
	public boolean eliminarSocioDelClub(String nroCarnet) throws Exception {
		Socio socio = traerSocioEnClub(nroCarnet);
		if(socio == null)
			throw new Exception("ERROR: nunca hubo un socio con carnet nro "+ nroCarnet +" ingresado en el club");
		if(lstSocios.size() == 0)
			throw new Exception("ERROR: no hay ningun socio en el club");
		//antes de eliminar al soocio del club debemos eliminarlo de todas las actividades si es que realiza alguna/s
		for(Actividad a : lstActividades) {
			if(a.traerSocio(socio.getNroCarnet()) != null)
				a.eliminarSocio(socio);
		}
		
		return lstSocios.remove(socio);
	}
	
	public void modificarSocio(String nombre, String apellido, long dni, String nroCarnet) throws Exception {
		Socio socio = traerSocioEnClub(nroCarnet);
		if(socio == null )
			throw new Exception("ERROR: no existe un socio con el numero de carnet "+ nroCarnet +" en el club");
		
		// si el dni del socio es distinto al que viene por parametro se busca si ese dni del parametro corresponde a algun socio
		// si pertenece a algun socio entonces tira una Excepcion, si el dni no corresponde a otro socio entonces se modifica
		if(socio.getDni() != dni) {
			if(traerSocioEnClub(dni) != null)
				throw new Exception("Error, ya hay otro socio con el dni nro "+dni +" en el club");
			else
				socio.setDni(dni);
		}
		
		socio.setNombre(nombre);
		socio.setApellido(apellido);
	}
	
	
	
//	----------------------------------------
//  ACTIVIDADES
//----------------------------------------

	public Actividad traerActividad(String nombre) {
		Actividad actividad = null;
		int i = 0;
		while(actividad == null && i < lstActividades.size()) {
			if(lstActividades.get(i).getNombreActividad().equalsIgnoreCase(nombre))
				actividad = lstActividades.get(i);
			
			i++;
		}
		return actividad;
	}
	
	public boolean agregarActividad(String nombreActividad, Profesor profesor, LocalTime horaDesde,
			LocalTime horaHasta, int cupoSocios) throws Exception {
		if(traerActividad(nombreActividad) != null)
			throw new Exception("ERROR: la actividad "+ nombreActividad + " que desea agregar ya se realiza en el club");
		if(traerProfesor(profesor.getLegajo()) == null)
			throw new Exception("ERROR: el profesor de la actividad que desea agregar no pertenece al club");
		if(horaHasta.isBefore(horaDesde) || horaDesde.equals(horaHasta))
			throw new Exception("ERROR: Horario incorrecto");
		
		return lstActividades.add(new Actividad(nombreActividad, profesor, horaDesde, horaHasta, cupoSocios));
		
	}
	
	public boolean eliminarActividad(String nombreActividad) throws Exception {
		Actividad eliminar = traerActividad(nombreActividad);
		if(eliminar == null)
			throw new Exception("ERROR: la actividad "+ nombreActividad + " que desea eliminar NO se realiza en el club");
		if(lstActividades.isEmpty())
			throw new Exception("ERROR: no hay actividades cargadas en el club");
		
		return lstActividades.remove(eliminar);
	}
	
	public void modificarProfesorActividad(String nombreActividad, String legajoNuevo) throws Exception {
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad "+ nombreActividad + " que desea modificar NO se realiza en el club");
		if(lstActividades.isEmpty())
			throw new Exception("ERROR: no hay actividades cargadas en el club");
		
		Profesor nuevo = traerProfesor(legajoNuevo);
//		si el profesor no existe en la lista de profesores del club entonces no se puede cambiar
		if(nuevo == null)
			throw new Exception("ERROR: el profesor no pertenece al club");
		
		actividad.setProfesor(nuevo);
	}
	
	public void modificarNombreActividad(String nombreActividad, String nuevoNombre) throws Exception{
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad "+ nombreActividad + " que desea modificar NO se realiza en el club");
		if(lstActividades.isEmpty())
			throw new Exception("ERROR: no hay actividades cargadas en el club");
//		si existe alguna actividad con el mismo nombre que "nuevoNombre" no se puede modificar
		if(traerActividad(nuevoNombre) != null)
			throw new Exception("ERROR: ya hay una actividad con el mismo nombre de la que desea actualizar");
		
		actividad.setNombreActividad(nuevoNombre);
	}
	
	public void modificarHorarioActividad(String nombre, LocalTime horaDesde, LocalTime horaHasta) throws Exception {
		Actividad actividad = traerActividad(nombre);
		if(actividad == null)
			throw new Exception("ERROR: la actividad "+ nombre + " que desea eliminar NO se realiza en el club");
		if(lstActividades.isEmpty())
			throw new Exception("ERROR: no hay actividades cargadas en el club");
		if(horaHasta.isBefore(horaDesde) || horaDesde.equals(horaHasta))
			throw new Exception("ERROR: Horario incorrecto");
		
		
		actividad.setHoraDesde(horaDesde);
		actividad.setHoraHasta(horaHasta);
	}
	
	
	
	public void modificarActividad(String nombreActividad,String nuevoNombre, String nuevoLegajo, LocalTime horaDesde,
			LocalTime horaHasta, int nuevoCupo) throws Exception {
		modificarProfesorActividad(nombreActividad, nuevoLegajo);
		modificarHorarioActividad(nombreActividad, horaDesde, horaHasta);
		modificarNombreActividad(nombreActividad, nuevoNombre);
	}
	
	
	
	
//	agrega al socio usando solo el numero del carnet, que trae al socio que lo contenga
	public boolean agregarSocioEnActividad(String nombreActividad, String nroCarnet) throws Exception {
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad no existe");
		Socio socio = traerSocioEnClub(nroCarnet);
		if(socio == null)
			throw new Exception("ERROR: no existe un socio en el club con el numero de carnet " + nroCarnet );
		
		return actividad.agregarSocio(socio);
	}
	
//	no hay un modificarSocioEnActividad porque ya tenemos el metodos modificarSocioEnClub
//	como los socios que estan en las actividades si o si perteneces al club, entonces directamente un socio se modiica desde el club
//	y por consecuencia estara modificado en la actividad/es que realice
	
	public boolean eliminarSocioEnActividad(String nombreActividad, String nroCarnet) throws Exception {
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad no existe");
		Socio socio = traerSocioEnClub(nroCarnet);
		if(socio == null)
			throw new Exception("ERROR: no existe un socio en el club con el numero de carnet " + nroCarnet );
		
		return actividad.eliminarSocio(socio);
	}
	
	
	public boolean agregarDiaDeActividad(String nombreActividad, String dia) throws Exception {
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad no existe");
		
		return actividad.agregarDia(dia);
	}
	
	public boolean eliminarDiaDeActividad(String nombreActividad, String dia) throws Exception {
		Actividad actividad = traerActividad(nombreActividad);
		if(actividad == null)
			throw new Exception("ERROR: la actividad no existe");
		
		return actividad.eliminarDia(dia);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	

}
