package modelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Actividad {
	private String nombreActividad;
	private Profesor profesor;
	private List<String> lstDias;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private List<Socio> lstSocios;
	private int cupoSocios;
	
	public Actividad(String nombreActividad, Profesor profesor, LocalTime horaDesde,
			LocalTime horaHasta, int cupoSocios) throws Exception {
		this.nombreActividad = nombreActividad;
		this.profesor = profesor;
		this.lstDias = new ArrayList<String>();
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.lstSocios = new ArrayList<Socio>();
		setCupoSocios(cupoSocios);
		
	}

	public String getNombreActividad() {
		return nombreActividad;
	}


	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public int getCupoSocios() {
		return cupoSocios;
	}

	public void setCupoSocios(int cupoSocios) throws Exception {
		if(cupoSocios <=0)
			throw new Exception("ERROR: cantida de cupo incorrecto");
		this.cupoSocios = cupoSocios;
	}

	public List<String> getLstDias() {
		return lstDias;
	}

	public List<Socio> getLstSocios() {
		return lstSocios;
	}

	@Override
	public String toString() {
		return "Profesor: " + profesor + 
				"\nHorario: Desde las " + horaDesde + "hs Hasta las " + horaHasta + "hs     Cupo: "
				+ cupoSocios;
	}
	
	public  String mostrarArchivoLectura() {
		return nombreActividad+"$"+profesor.getLegajo()+"$"+horaDesde+"$"+horaHasta+"$"+cupoSocios;
	}
	
	public  String mostrarDiasArchivoLectura1() {
		String dias = nombreActividad+";";
		for(String d : lstDias) {
			dias = dias + d +";";
		}
		return dias ;
	}
	
//	----------------------------------------
//    DIAS
//----------------------------------------
	
	public String traerDia(String dia) {
		String diaEncontrado = null;
		int i = 0;
		// buscamos en la lista de dias si el dia que mandamos por parametro se encuentra
//		en el caso de que se encuentre sale del bucle y se retorna, en caso contrario se retornara null
		while (diaEncontrado == null && i < lstDias.size()) {
			if(lstDias.get(i).equalsIgnoreCase(dia))
				diaEncontrado = lstDias.get(i);
			
			i++;
		}
		return diaEncontrado;
	}
	
	public boolean agregarDia(String dia) throws Exception{
		if(!esDiaCorrecto(dia))
			throw new Exception("ERROR: el dia "+ dia+" no es correcto");
		if(traerDia(dia)!=null)
			throw new Exception("ERROR: el dia "+ dia+" ya esta ingresado");
		if(lstDias.size() == 5)
			throw new Exception("ERROR: la cantidad de dias maximo es de 5, ya no se pueden agregar dias");
		
		return lstDias.add(dia);
	}

	public boolean eliminarDia(String dia) throws Exception{
		if(traerDia(dia)==null)
			throw new Exception("ERROR: el dia "+ dia+" nunca fue ingresado");
		if(lstDias.size() == 0)
			throw new Exception("ERROR: no hay dias agregados");
		
		return lstDias.remove(dia);
	}
	
	
//	si el dia coincide con algun dia correcto se retorna true, de lo contrario se retorna false
	public boolean esDiaCorrecto(String dia) {
		boolean retorno = false;
//		se genera un vector de String que va a tener almacenado los dias correctos
		String[] dias = {"lunes", "martes", "miercoles","jueves","viernes", "sabado", "domingo"};
		int i = 0;
//		se recorre el vector de dias hasta que el dia ingresado sea igual a un dia correcto, en ese caso la variable retorno va a ser true
//	    en caso de que el dia que se ingresa no se encuentre en el vector, la variable retorno sera falsa
		while(!retorno && i < dias.length) {
			if(dias[i].equalsIgnoreCase(dia))
				retorno = true;
			
			i++;
		}
		return retorno;
	}
	

	
	
//	----------------------------------------
//       SOCIOS
//----------------------------------------	
	
	public Socio traerSocio(String nroCarnet) {
		Socio socio = null;
		int i  = 0;
		while(socio == null && i < lstSocios.size()) {
			if(lstSocios.get(i).getNroCarnet().equalsIgnoreCase(nroCarnet))
				socio = lstSocios.get(i);
			
			i++;
		}
	
	return socio;
	}
	
	public boolean agregarSocio(String nombre, String apellido, long dni, String nroCarnet) throws Exception {
		if(traerSocio(nroCarnet) != null)
			throw new Exception("ERROR:  ya hay un socio con el carnet nro "+ nroCarnet+" ingresado en esta actividad");
		if(lstSocios.size() == this.cupoSocios)
			throw new Exception("ERROR: ya no se pueden agregar mas socios a la actividad "+nombreActividad+", se excedio el cupo de "+cupoSocios);
		 
		return lstSocios.add(new Socio(nombre, apellido, dni, nroCarnet));
	}
	
	public boolean agregarSocio(Socio socio) throws Exception {
		if(traerSocio(socio.getNroCarnet()) != null)
			throw new Exception("ERROR:  ya hay un socio con el carnet nro "+ socio.getNroCarnet()+" ingresado en esta actividad");
		if(lstSocios.size() == this.cupoSocios)
			throw new Exception("ERROR: ya no se pueden agregar mas socios a la actividad");
		
		return lstSocios.add(socio);
	}
	
	public boolean eliminarSocio(Socio socio) throws Exception {
		if(traerSocio(socio.getNroCarnet()) == null)
			throw new Exception("ERROR: nunca hubo un socio con carnet nro "+ socio.getNroCarnet()+" ingresado en esta actividad");
		if(lstSocios.size() == 0)
			throw new Exception("ERROR: no hay ningun socio en esta actividad");
		
		return lstSocios.remove(socio);
	}
	
	
	
	
	
	
	
	
	

}
