package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;

import modelo.Actividad;
import modelo.AdministracionClub;
import modelo.Alquiler;
import modelo.Profesor;
import modelo.Servicio;
import modelo.Socio;

public class Archivo {
	
	
//	----------------------------------------
//  MOSTRAR EN ARCHIVO
//----------------------------------------	
	public static void mostrarProfesoresDelClubEnArchivo(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("mostrar profesores.txt");
		PrintStream ps = new PrintStream(os);
		
		ps.println("\n ----- PROFESORES DEL CLUB -----\n");
		for(Profesor profesor : club.getLstProfesores()) {
			ps.println(profesor.toString());
		}
	
		ps.println("\n----------------------------------\n");
		mostrarProfesoresClubArchivosLectura(club);
	}
	
	public static void mostrarSociosDelClubEnArchivo(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("mostrar sociosDelClub.txt");
		PrintStream ps = new PrintStream(os);
		ps.println("\n ----- SOCIOS DEL CLUB -----\n");
		
		for(Socio socio : club.getLstSocios()) {
			ps.println(socio.toString());
		}
		ps.println("\n----------------------------------\n");
		mostrarSociosClubArchivosLectura(club);	
	}
	
	public static void mostrarServiciosDelClubEnArchivo(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os; 
		PrintStream ps;
		for(Servicio servicio : club.getLstServicios()) {
			os = new FileOutputStream("mostrar servicio "+servicio.getNombreServicio()+".txt");
			ps = new PrintStream(os);
			ps.println(servicio.toString());
			ps.println("--Alquileres--");
			for(Alquiler alquiler : servicio.getLstAlquileres()) {
				ps.println(alquiler.toString() +",  precio: "+servicio.precioTotalAlquiler(alquiler));
			}
				
		}
		mostrarServiciosClubArchivosLectura(club);
		mostrarAlquileresServiciosArchivosLectura(club);
	}
	
	public static void mostrarActividadesDelClubEnArchivo(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os; 
		PrintStream ps;
	
		for(Actividad actividad : club.getLstActividades()) {
			os = new FileOutputStream("mostrar actividad "+actividad.getNombreActividad()+".txt");
			ps = new PrintStream(os);
			ps.println("\n\n-------------------------- ACTIVIDAD "+ actividad.getNombreActividad().toUpperCase() +" -----------------------------\n");
			ps.println(actividad.toString());
			//mostrar dias
			ps.print("\nDias [");
			for(String dia : actividad.getLstDias()) {
				ps.print(" - "+ dia);
			}
			ps.println(" ]\n\n");
//			mostrarSocios
			ps.println("--- Socios de la actividad ---\n\n");
			for(Socio socio : actividad.getLstSocios()) {
				ps.println(socio.toString());
			}
			ps.println("\n----------------------------------\n");
		}
		mostrarActividadesClubArchivosLectura(club);
		mostrarAlumnosActividadesClubArchivosLectura(club);
		mostrarDiasActividadesArchivosLectura(club);
	}
			
	 
	
//	----------------------------------------
//  MOSTRAR EN ARCHIVOS DE LECTURA
//----------------------------------------	
	public static void mostrarSociosClubArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("leer socios club.txt");
		PrintStream ps = new PrintStream(os);
		for(Socio socio : club.getLstSocios()) {
			ps.println(socio.mostrarArchivoLectura());
		}
	}
	
	public static void mostrarProfesoresClubArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("leer profesores club.txt");
		PrintStream ps = new PrintStream(os);
		for(Profesor profe : club.getLstProfesores()) {
			ps.println(profe.mostrarArchivoLectura());
		}
	}
	
	public static void mostrarServiciosClubArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("leer servicios club.txt");
		PrintStream ps = new PrintStream(os);
		for(Servicio servicio : club.getLstServicios()) {
			ps.println(servicio.mostrarArchivoLectura());
		}
	}
	
	public static void mostrarActividadesClubArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("leer actividades club.txt");
		PrintStream ps = new PrintStream(os);
		for(Actividad actividad : club.getLstActividades()) {
			ps.println(actividad.mostrarArchivoLectura());
		}
	}
	
	public static void mostrarAlumnosActividadesClubArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os;
		PrintStream ps;
		for(Actividad actividad : club.getLstActividades()) {
			os = new FileOutputStream("leer alumnos "+actividad.getNombreActividad()+".txt");
		    ps = new PrintStream(os);
			  for(Socio socio : actividad.getLstSocios()) {
				ps.println(socio.mostrarArchivoLectura());
			}
		}
	}
	
//	los metodos de inicializar crean un archivo en caso de que no exista, si existe no lo crean
	public static void inicializarAlumnosActividadesClubArchivosLectura(AdministracionClub club) throws IOException {
		for(Actividad actividad : club.getLstActividades()) {
			File file = new File("leer alumnos "+actividad.getNombreActividad()+".txt");
		    if(!file.exists()) {
		    	file.createNewFile();
		    }
			}
	}
	
	public static void mostrarAlquileresServiciosArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os;
		PrintStream ps;
		for(Servicio servicio : club.getLstServicios()) {
			os = new FileOutputStream("leer alquileres "+servicio.getNombreServicio()+".txt");
		    ps = new PrintStream(os);
			  for(Alquiler alquiler : servicio.getLstAlquileres()) {
				ps.println(alquiler.mostrarArchivoLectura());
			}
		}
	}
	
	public static void inicializarAlquileresServiciosArchivosLectura(AdministracionClub club) throws IOException {
		for(Servicio servicio : club.getLstServicios()) {
			//si el archivo no existe lo crea
			File file = new File("leer alquileres "+servicio.getNombreServicio()+".txt");
			if(!file.exists()) {
			file.createNewFile();
			}
		}
	}
	
	public static void mostrarDiasActividadesArchivosLectura(AdministracionClub club) throws FileNotFoundException {
		FileOutputStream os = new FileOutputStream("leer dias actividades.txt");
		PrintStream  ps = new PrintStream(os);
		for(Actividad actividad: club.getLstActividades()) {
		   ps.println(actividad.mostrarDiasArchivoLectura1());
		}
	}
	
	

//	----------------------------------------
//  LEER ARCHIVOS DE LECTURA
//----------------------------------------	
	public static void leerSociosClub(AdministracionClub club) throws NumberFormatException, IOException, Exception{
		File direccion = new File(".");
		File archivo = new File(direccion,"leer socios club.txt");
		
		FileReader lectorArchivo = new FileReader(archivo);
		BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
		
		String linea = "";
		
		
		while(linea != null) {
			linea = lectorBuffer.readLine();
			
				
			if(linea != null) {
			String[] vector = linea.split("[$]");
		     
           
				try {
					club.agregarSocioAlClub(vector[0], vector[1], Long.parseLong(vector[2]), vector[3]);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
          
		}
		
		lectorBuffer.close();
		lectorArchivo.close();
		
	}
	
public static void leerServiciosClub(AdministracionClub club) throws NumberFormatException, IOException, Exception{
		
		FileReader lectorArchivo = new FileReader("leer servicios club.txt");
		BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
		
		String linea = "";
		
		while(linea != null) {
			linea = lectorBuffer.readLine();
			
			if(linea != null) {
			String[] vector = linea.split("[$]");
			
			
				try {
					club.agregarServicio(vector[0], Double.parseDouble(vector[1]));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		lectorBuffer.close();
		lectorArchivo.close();
		
		
	}
	
public static void leerProfesoresClub(AdministracionClub club) throws NumberFormatException, IOException, Exception{
	
	FileReader lectorArchivo = new FileReader("leer profesores club.txt");
	BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
	
	String linea = "";
	
	while(linea != null) {
		linea = lectorBuffer.readLine();
		
		if(linea != null) {
		String[] vector = linea.split("[$]");
		
		
			try {
				club.agregarProfesorAlClub(vector[0], vector[1], Long.parseLong(vector[2]), vector[3]);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	lectorBuffer.close();
	lectorArchivo.close();
	
}

public static void leerActividadesClub(AdministracionClub club) throws NumberFormatException, IOException, Exception{
	
	FileReader lectorArchivo = new FileReader("leer actividades club.txt");
	BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
	
	String linea = "";
	
	while(linea != null) {
		linea = lectorBuffer.readLine();
		
		if(linea != null) {
		String[] vector = linea.split("[$]");
		
//		String nombreActividad, Profesor profesor, LocalTime horaDesde,
//		LocalTime horaHasta, int cupoSocios
		String nombre = vector[0];
		String legajo = vector[1];
		String horaDesde[] = vector[2].split("[:]");
		String horaHasta[] = vector[3].split("[:]");
		LocalTime desde = LocalTime.of(Integer.parseInt(horaDesde[0]), Integer.parseInt(horaDesde[1]));
		LocalTime hasta = LocalTime.of(Integer.parseInt(horaHasta[0]), Integer.parseInt(horaHasta[1]));
		int cupo = Integer.parseInt(vector[4]);
		
		try {
			club.agregarActividad(nombre,club.traerProfesor(legajo), desde, hasta, cupo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
	}
	
	lectorBuffer.close();
	lectorArchivo.close();
	leerDiasActividades(club);
	
	
}
	
	
public static void leerDiasActividades(AdministracionClub club) throws NumberFormatException, IOException, Exception{
	
	FileReader lectorArchivo = new FileReader("leer dias actividades.txt");
	BufferedReader lectorBuffer = new BufferedReader(lectorArchivo);
	
	String linea = "";
	
	while(linea != null) {
		linea = lectorBuffer.readLine();
		
		if(linea != null) {
		String[] vector = linea.split("[;]");
		
 
           for(int i = 1; i < vector.length; i++) {
    	        try {
				club.agregarDiaDeActividad(vector[0], vector[i]);
			    } catch (Exception e) {
				  System.out.println(e.getMessage());
			    }
           }
			
		}
	}
	
	lectorBuffer.close();
	lectorArchivo.close();
	
}

public static void leerSociosActividades(AdministracionClub club) throws NumberFormatException, IOException, Exception{
	String linea = "";
	for(int i = 0; i < club.getLstActividades().size(); i++) {
		linea = "";
		String nombre = club.getLstActividades().get(i).getNombreActividad();
		FileReader lectorArchivo;
		BufferedReader lectorBuffer;
		lectorArchivo = new FileReader("leer alumnos "+nombre+".txt");
	    lectorBuffer = new BufferedReader(lectorArchivo);
		while(linea != null) {
			
		   linea = lectorBuffer.readLine();
		
		   if(linea != null) {
		   String[] vector = linea.split("[$]");
		
		   try {
           club.agregarSocioEnActividad(nombre, vector[3]);
		   }catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
           
		}
		  
	}
		lectorBuffer.close();
		lectorArchivo.close();
  }
	 

}

public static void leerAlquileresServicios(AdministracionClub club) throws NumberFormatException, IOException, Exception{
	String linea = "";
	for(int i = 0; i < club.getLstServicios().size(); i++) {
		linea = "";
		String nombre =club.getLstServicios().get(i).getNombreServicio();
		FileReader lectorArchivo;
		BufferedReader lectorBuffer;
		lectorArchivo = new FileReader("leer alquileres "+nombre+".txt");
	    lectorBuffer = new BufferedReader(lectorArchivo);
		while(linea != null) {
			
		   linea = lectorBuffer.readLine();
		
		   if(linea != null) {
		   String[] vector = linea.split("[$]");
		   String[] fechaStr=vector[0].split("[-]");
		   String horaDesde[] = vector[1].split("[:]");
		   String horaHasta[] = vector[2].split("[:]");
		   
		   LocalDate fecha = LocalDate.of(Integer.parseInt(fechaStr[0]),Integer.parseInt(fechaStr[1]), Integer.parseInt(fechaStr[2]));
		   LocalTime desde = LocalTime.of(Integer.parseInt(horaDesde[0]), Integer.parseInt(horaDesde[1]));
		   LocalTime hasta = LocalTime.of(Integer.parseInt(horaHasta[0]), Integer.parseInt(horaHasta[1]));
		   
		   try {
           club.alquilarServico(nombre, fecha, desde, hasta);
		   }catch(Exception e) {
			   System.out.println(e.getMessage());
		   }
           
		}
		  
	}
		lectorBuffer.close();
		lectorArchivo.close();
  }
}
	 

}
