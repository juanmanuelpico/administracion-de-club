package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Alquiler {
	private int id;
	private LocalDate fecha;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	
	public Alquiler(int id, LocalDate fecha, LocalTime horaDesde, LocalTime horaHasta) throws Exception {
		
		this.id = id;
		this.fecha = fecha;
		this.horaDesde = horaDesde;
		setHoraHasta(horaHasta);
	}

	public int getId() {
		return id;
	}
	

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde)  {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) throws Exception {
		if(horaHasta.isBefore(horaDesde) || horaHasta.equals(horaDesde))
			throw new Exception("Error: el horarios no son validos");
		this.horaHasta = horaHasta;
	}
	

	@Override
	public String toString() {
		return "id="+id+"  fecha=" + fecha + ", horaDesde=" + horaDesde + ", horaHasta=" + horaHasta;
	}
	
	public  String mostrarArchivoLectura() {
		return fecha+"$"+horaDesde+"$"+horaHasta;
	}
	
	
	
}
