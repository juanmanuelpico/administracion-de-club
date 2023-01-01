package test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import modelo.Actividad;
import modelo.AdministracionClub;
import modelo.Alquiler;
import modelo.Profesor;
import modelo.Servicio;
import modelo.Socio;
public class TestModelo {

	public static void main(String[] args)  {
		AdministracionClub adm= new AdministracionClub();
		
		
//	PRIMERO AGREGAR TOODOS LOS DATOS Y AGREGARLOS A LOS ARCHIVOS	
//		try {
//			adm.agregarSocioAlClub("Juan", "Pico", 94755616, "3423");
//			adm.agregarSocioAlClub("Sofia", "Vottari", 44563247, "3422");
//			adm.agregarSocioAlClub("Lara", "Dofi", 44563234, "3421");
//			adm.agregarSocioAlClub("Kevin", "Oviedo", 44563214, "3425");
//			adm.agregarSocioAlClub("Luana", "Fremon", 95678323, "3420");
//			adm.agregarSocioAlClub("Fernanda", "Robledo", 43567221, "5000");
//			adm.agregarSocioAlClub("Juan", "Lopez", 33567498, "123");
//			adm.agregarSocioAlClub("Kevin", "Roldan", 46756443, "324");
//			adm.agregarSocioAlClub("Angel", "Zamin", 44563124, "456");
//			adm.agregarSocioAlClub("Jose", "Peña", 23456789, "1");
//			adm.agregarSocioAlClub("Amanda", "Dervil", 3458901, "2");
//			adm.agregarSocioAlClub("Laura", "Chazf", 23456732, "34"); 
//			adm.agregarSocioAlClub("Angela", "Numan", 44521999, "35");
//		}catch(Exception e) {
//			System.out.println(e.getMessage());  
//		}
//		
//		
//		
//		
//		
//		
//		try {
//			adm.agregarProfesorAlClub("Roberto", "Gomez", 32456221, "4000");
//			adm.agregarProfesorAlClub("Carlos", "Perez", 30540232, "4001");
//			adm.agregarProfesorAlClub("Braian", "Adams", 31560555, "4002");
//			adm.agregarProfesorAlClub("Juana", "Calvo", 30560555, "4003");
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		
//		try {
//			adm.agregarActividad("basquet", adm.traerProfesor("4000"), LocalTime.of(15, 00),  LocalTime.of(17, 00), 6);
//			adm.agregarActividad("natacion", adm.traerProfesor("4003"), LocalTime.of(15, 00),  LocalTime.of(17, 00), 4);
//			adm.agregarActividad("futbol", adm.traerProfesor("4002"), LocalTime.of(13, 00),  LocalTime.of(16, 00), 5);
//			adm.agregarActividad("voley", adm.traerProfesor("4001"), LocalTime.of(20, 00),  LocalTime.of(21, 00), 8);
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			adm.agregarDiaDeActividad("basquet", "martes");
//			adm.agregarDiaDeActividad("basquet", "miercoles");
//			adm.agregarDiaDeActividad("basquet", "viernes");
//			adm.agregarDiaDeActividad("natacion", "lunes");
//			adm.agregarDiaDeActividad("natacion", "jueves");
//			adm.agregarDiaDeActividad("futbol", "martes");
//			adm.agregarDiaDeActividad("futbol", "miercoles");
//			adm.agregarDiaDeActividad("voley", "martes");
//			adm.agregarDiaDeActividad("voley", "sabado");
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		try {
//			adm.agregarSocioEnActividad("basquet", "5000");
//			adm.agregarSocioEnActividad("basquet", "3425");
//			adm.agregarSocioEnActividad("basquet", "35");
//			adm.agregarSocioEnActividad("basquet", "34");
//			
//			adm.agregarSocioEnActividad("futbol", "34");
//			adm.agregarSocioEnActividad("futbol", "1");
//			adm.agregarSocioEnActividad("futbol", "3422");
//			
//			adm.agregarSocioEnActividad("natacion", "123");
//			adm.agregarSocioEnActividad("natacion", "1");
//			adm.agregarSocioEnActividad("natacion", "324");
//			
//			adm.agregarSocioEnActividad("voley", "3423");
//			adm.agregarSocioEnActividad("voley", "3425");
//			adm.agregarSocioEnActividad("voley", "3421");
//			adm.agregarSocioEnActividad("voley", "123");
//			
//			
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//			
//		}
	 /*

		try {
			adm.agregarServicio("alquiler de cancha", 200);
			adm.agregarServicio("alquiler de salon", 450);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			adm.alquilarServico("alquiler de cancha", LocalDate.of(2020, 12, 3), LocalTime.of(18, 00), LocalTime.of(20, 00));
			adm.alquilarServico("alquiler de salon", LocalDate.of(2020, 12, 5), LocalTime.of(18, 00), LocalTime.of(20, 00));
			adm.alquilarServico("alquiler de salon", LocalDate.of(2020, 12, 3), LocalTime.of(18, 00), LocalTime.of(20, 00));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		try {
			Archivo.mostrarProfesoresDelClubEnArchivo(adm);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			Archivo.mostrarSociosDelClubEnArchivo(adm);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			Archivo.mostrarActividadesDelClubEnArchivo(adm);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	
		
		
		
		
//		 try {
//			mostrarServiciosDelClubEnArchivo(adm);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
		
  
		 
//	COMENTAR LO ANTERIOR Y LEER DESDE ARCHIVOS	
		
//		try {
//			leerSociosClub(adm);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mostrarSociosDelClub(adm);
//		try {
//			mostrarSociosDelClubEnArchivo(adm);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
////-------------------------------------------------------
//		try {
//			leerProfesoresClub(adm);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mostrarProfesoresDelClub(adm);
//	  try {
//			mostrarProfesoresDelClubEnArchivo(adm);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
////	----------------------------------------------	
//		//AGREGAR DIAS, ALUMNOS, MAS ACTIVIDADES AL CLUB
//		try {
//			leerActividadesClub(adm);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		mostrarActividadesDelClub(adm);
//		try {
//			mostrarActividadesDelClubEnArchivo(adm);
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//-----------------------------------------------------------
	/*	try {
			leerServiciosClub(adm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mostrarServiciosDelClub(adm);
		 try {
			mostrarServiciosDelClubEnArchivo(adm);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	*/	
//		 POR ULTIMO PROBAR ERRORES
	
		
//		menuActividades(adm); 
//		menuSocios(adm);
		Menu.menuPrincipal(adm);
		
		
	}
	

}



	
	










