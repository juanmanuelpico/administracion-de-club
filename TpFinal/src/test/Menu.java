package test;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import modelo.Actividad;
import modelo.AdministracionClub;
import modelo.Profesor;
import modelo.Servicio;
import modelo.Socio;

public class Menu {
	
//	-----------------------------
//	MENU
//	-----------------------------
	public static void menuSocios(AdministracionClub club) {
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
		System.out.println("---- menu socios ----\n");
		System.out.println("	1- mostrar socios del club");
		System.out.println("	2- leer socios del club (archivo)");
		System.out.println("	3- eliminar socio del club");
		System.out.println("	4- modificar socio del club");
		System.out.println("	0- volver al menu principal");
		System.out.println("	Indique cual opcion desea realizar");
		opcion = scan.nextInt();
		
		switch (opcion) {
		case 1: 
			mostrarSociosDelClub(club);
			break;
			
		case 2: 
			// se leen los socios desde el archivo
			 try {
			 Archivo.leerSociosClub(club);	
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 try {	
				 Archivo.mostrarSociosDelClubEnArchivo(club);
				 }catch(Exception e) {
					 System.out.println(e.getMessage());
				 }
			 break;
			 
		case 3: System.out.println("    ingrese numero de carnet de socio a eliminar");
		        String carnet = scan.next();
			 try {// se elimina el socio 
				club.eliminarSocioDelClub(carnet);
				Archivo.mostrarSociosDelClubEnArchivo(club);
			 } catch (Exception e) {
				System.out.println(e.getMessage());
			 }
			 break;  
			 
		case 4 : 
			   System.out.println("    ingrese numero de carnet de socio a modificar");
			   String nCarnet = scan.next();
			   System.out.println("    ingrese nuevo nombre");
			   String nombre = scan.next();
			   System.out.println("    ingrese nuevo apellido");
			   String apellido = scan.next();
			   System.out.println("    ingrese nuevo dni");
			   Long dni = scan.nextLong();
			   try {
				club.modificarSocio(nombre, apellido, dni, nCarnet);
				Archivo.mostrarSociosDelClubEnArchivo(club);
			   } catch (Exception e) {
				System.out.println(e.getMessage());
			  }
			   break;
			   
		case 0 : System.out.println("\n saliste del menu socios\n");
		        break;
		
		default : System.out.println("    numero de opcion invalido");
		         break;
			}
		
		}while(opcion != 0);
//		scan.close();
	}
	
	
	public static void menuProfesores(AdministracionClub club) {
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
		System.out.println("---- menu profesores ----\n");
		System.out.println("	1- mostrar profesores del club");
		System.out.println("	2- leer profesores del club (archivo)");
		System.out.println("	3- eliminar profesor del club");
		System.out.println("	4- modificar profesor del club");
		System.out.println("	0- volver al menu principal");
		System.out.println("	Indique cual opcion desea realizar");
		opcion = scan.nextInt();
		scan.nextLine();
		
		switch (opcion) {
		case 1: 
			mostrarProfesoresDelClub(club);
			break;
			
		case 2: 
			// se leen los profesores desde el archivo
			 try {
			 Archivo.leerProfesoresClub(club);	
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 try {
				 Archivo.mostrarProfesoresDelClubEnArchivo(club);
				 }catch(Exception e) {
					 System.out.println(e.getMessage());
				 }
			 break;
			 
		case 3: System.out.println("    ingrese numero de lrgajo de profesor a eliminar");
		        String legajo = scan.next();
			 try {// se elimina el profesor 
				club.eliminarProfesorAlClub(legajo);
				Archivo.mostrarProfesoresDelClubEnArchivo(club);
			 } catch (Exception e) {
				System.out.println(e.getMessage());
			 }
			 break;  
			 
		case 4 : 
			   System.out.println("    ingrese numero de legajo de profesor a modificar");
			   String nLegajo = scan.next();
			   System.out.println("    ingrese nuevo nombre");
			   String nombre = scan.next();
			   System.out.println("    ingrese nuevo apellido");
			   String apellido = scan.next();
			   System.out.println("    ingrese nuevo dni");
			   Long dni = scan.nextLong();
			   try {
				club.modificarProfesor(nombre, apellido, dni, nLegajo);
				Archivo.mostrarProfesoresDelClubEnArchivo(club);
			   } catch (Exception e) {
				System.out.println(e.getMessage());
			  }
			   break;
			   
		case 0 : System.out.println("\n saliste del menu profesores\n");
		        break;
		
		default : System.out.println("    numero de opcion invalido");
		         break;
			}
		
		}while(opcion != 0);
//		scan.close();
	}
	
	public static void menuServicios(AdministracionClub club) {
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
		System.out.println("---- menu servicios ----\n");
		System.out.println("	1- mostrar servicios del club");
		System.out.println("	2- leer servicios con sus alquileres");
		System.out.println("	3- eliminar servicio del club");
		System.out.println("	4- eliminar alquiler de un servicio");
		System.out.println("	0- volver al menu principal");
		System.out.println("	Indique cual opcion desea realizar");
		opcion = scan.nextInt();
		scan.nextLine();
		
		switch (opcion) {
		case 1: 
			mostrarServiciosDelClub(club);
			break;
			
		case 2: 
			// se leen los servicios desde el archivo
			 try {
			 Archivo.leerServiciosClub(club);
			 Archivo.inicializarAlquileresServiciosArchivosLectura(club);
			 Archivo.leerAlquileresServicios(club);
			
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 try {
				 Archivo.mostrarServiciosDelClubEnArchivo(club);
				 }catch(Exception e) {
					 System.out.println(e.getMessage());
				 }
			 break;
			 
		case 3: System.out.println("    ingrese id de servicio a eliminar");
		        int id = scan.nextInt();
		        scan.nextLine();
		     try {// se elimina el servicio 
				 club.eliminarServicio(id);
				 Archivo.mostrarServiciosDelClubEnArchivo(club);
			 } catch (Exception e) {
				System.out.println(e.getMessage());
			 }
			 break;  
			 
		case 4 : 
			   System.out.println("    ingrese nombre del servicio");
			   String nombreServicio;
			   nombreServicio = scan.nextLine();
			   
			   System.out.println("    ingrese id del alquiler del servicio");
			   int idAlquiler;
			   idAlquiler = scan.nextInt();
			   scan.nextLine();
			   
			   try {
				 club.eliminarAlquilarServico(nombreServicio, idAlquiler);
				 Archivo.mostrarServiciosDelClubEnArchivo(club);
			   } catch (Exception e) {
				System.out.println(e.getMessage());
			  }
			   break;
			   
		case 0 : System.out.println("\n saliste del menu servicios\n");
		        break;
		
		default : System.out.println("    numero de opcion invalido");
		         break;
			}
		
		}while(opcion != 0);
//		scan.close();
	}
	
	
	public static void menuActividades(AdministracionClub club) {
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
		System.out.println("---- menu actividades ----\n");
		System.out.println("SI QUIERE LEER LAS ACTIVIDADES PRIMERO DEBERA LEER PROFESORES Y SOCIOS DEL CLUB EN SU RESPECTIVO MENU");
		System.out.println("EN CASO DE QUE NO LO HAYA HECHO, VUELVA AL MENU PRINCIPAL Y ACCEDA AL MENU CORRESPONDIENTE");
		System.out.println("	1- mostrar actividades del club");
		System.out.println("	2- leer actividades con sus respectivos alumnos y profesores");
		System.out.println("	3- modificar alguna actividad");
		System.out.println("	4- agregar socio en actividad");
		System.out.println("	5- agregar dia en actividad");
		System.out.println("	6- eliminar dia en actividad");
		System.out.println("	7- eliminar socio en actividad");
		System.out.println("	8- eliminar actividad");
		System.out.println("	0- volver al menu principal");
		System.out.println("	Indique cual opcion desea realizar");
		opcion = scan.nextInt();
		scan.nextLine();
		
		switch (opcion) {
		case 1: 
			mostrarActividadesDelClub(club);
			break;
			
		case 2: 
			// se leen las actividades desde el archivo
			 try {
			 Archivo.leerActividadesClub(club);
			 Archivo.inicializarAlumnosActividadesClubArchivosLectura(club);
			 Archivo.leerSociosActividades(club);
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 try {
			 Archivo.mostrarActividadesDelClubEnArchivo(club);
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
			 break;
			 
		case 3: 
			 int modificacion = -1;
			   
			 do {
			   System.out.println("      --- ingrese tipo de modificacion --- ");
		       System.out.println("      1. modifique unicamente el profesor de una actividad");
		       System.out.println("      2. modifique unicamente el nombre de una actividad");
		       System.out.println("      3. modifique unicamente el horario de una actividad");
		       System.out.println("      4. modifique una actividad completa");
		       System.out.println("      0. volver el menu de actividades");
		        
		       modificacion = scan.nextInt();
		       scan.nextLine();
		       if(modificacion == 1) {
		    	   System.out.println("      ingrese nombre de la actividad que desea modificar");
		    	   String nombreAct = scan.nextLine();
		    	   System.out.println("      ingrese el legajo del nuevo profesor de la actividad");
		    	   String nuevoLegajo = scan.nextLine();
		    	   try {
					club.modificarProfesorActividad(nombreAct, nuevoLegajo);
					Archivo.mostrarActividadesDelClubEnArchivo(club);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		       }
		       if(modificacion == 2) {
		    	   System.out.println("      ingrese nombre de la actividad que desea modificar");
		    	   String nombreAct = scan.nextLine();
		    	   System.out.println("      ingrese el nuevo nombre de la actividad");
		    	   String nuevoNombre = scan.nextLine();
		    	   try {
					club.modificarNombreActividad(nombreAct, nuevoNombre);
					Archivo.mostrarActividadesDelClubEnArchivo(club);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		       }
		       if(modificacion == 3) {
		    	   System.out.println("      ingrese nombre de la actividad que desea modificar");
		    	   String nombreAct = scan.nextLine();
		    	   System.out.println("      ingrese el nuevo horario de inicio de la actividad");
		    	   int desde = scan.nextInt();
		    	   scan.nextLine();
		    	   System.out.println("      ingrese el nuevo horario de finalizacion de la actividad");
		    	   int hasta = scan.nextInt();
		    	   scan.nextLine();
		    	   try {
					club.modificarHorarioActividad(nombreAct, LocalTime.of(desde, 00), LocalTime.of(hasta, 00));
					Archivo.mostrarActividadesDelClubEnArchivo(club);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		       }
		       if(modificacion == 4) {
		    	   System.out.println("      ingrese nombre de la actividad que desea modificar");
		    	   String nombreAct = scan.nextLine();
		    	   System.out.println("      ingrese el legajo del nuevo profesor de la actividad");
		    	   String nuevoLegajo = scan.nextLine();
		    	   System.out.println("      ingrese el nuevo nombre de la actividad");
		    	   String nuevoNombre = scan.nextLine();
		    	   System.out.println("      ingrese el nuevo horario de inicio de la actividad");
		    	   int desde = scan.nextInt();
		    	   scan.nextLine();
		    	   System.out.println("      ingrese el nuevo horario de finalizacion de la actividad");
		    	   int hasta = scan.nextInt();
		    	   scan.nextLine();
		    	   System.out.println("      ingrese el nuevo cupo de la actividad");
		    	   int cupo = scan.nextInt();
		    	   scan.nextLine();
		    	   try {
					club.modificarActividad(nombreAct, nuevoNombre, nuevoLegajo, LocalTime.of(desde, 00), LocalTime.of(hasta, 00), cupo);
					Archivo.mostrarActividadesDelClubEnArchivo(club);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
		       }
		       
		       
			 }while(modificacion != 0);
			 break;  
			 
		case 4 : 
			   System.out.println("    ingrese nombre de la actvidad");
			   String nombreActividad = scan.nextLine();
			   System.out.println("    ingrese nro de carnet del socio que desea agregar a la actividad");
			   String nroCarnet = scan.nextLine();
			   
			   try {
				 club.agregarSocioEnActividad(nombreActividad, nroCarnet);
				 Archivo.mostrarActividadesDelClubEnArchivo(club);
			   } catch (Exception e) {
				System.out.println(e.getMessage());
			  }
			   break;
			   
		case 5 : 
			 System.out.println("    ingrese nombre de la actvidad");
			   String nombreA = scan.nextLine();
			   scan.nextLine();
			   System.out.println("    ingrese dia que desea agregar a la actividad");
			   String dia = scan.nextLine();
			   try {
					 club.agregarDiaDeActividad(nombreA, dia);
					 Archivo.mostrarActividadesDelClubEnArchivo(club);
				   } catch (Exception e) {
					System.out.println(e.getMessage());
				  }
			break;
		
		case 6 : 
			 System.out.println("    ingrese nombre de la actvidad");
			   String nombre = scan.nextLine();
			   try {
					 club.eliminarActividad(nombre);
					 Archivo.mostrarActividadesDelClubEnArchivo(club);
				   } catch (Exception e) {
					System.out.println(e.getMessage());
				  }
			break;
		
		case 7 : 
			 System.out.println("    ingrese nombre de la actvidad");
			 String nombreAc = scan.nextLine();
			 System.out.println("    ingrese numero de carnet de socio");
			 String carnet = scan.nextLine();
			   try {
					 club.eliminarSocioEnActividad(nombreAc, carnet);
					 Archivo.mostrarActividadesDelClubEnArchivo(club);
				   } catch (Exception e) {
					System.out.println(e.getMessage());
				  }
			break;	
			
		case 8 : 
			 System.out.println("    ingrese nombre de la actvidad");
			 String nombreAct = scan.nextLine();
			 System.out.println("    ingrese dia");
			 String dia1 = scan.nextLine();
			   try {
					 club.agregarDiaDeActividad(nombreAct, dia1);
					 Archivo.mostrarActividadesDelClubEnArchivo(club);
				   } catch (Exception e) {
					System.out.println(e.getMessage());
				  }
			break;	
			
			
				   
		case 0 : System.out.println("\n saliste del menu actividades\n");
		        break;
		
		default : System.out.println("    numero de opcion invalido");
		         break;
			}
		
		}while(opcion != 0);
//		scan.close();
	}
	
	public static void menuPrincipal(AdministracionClub club) {
		Scanner scan = new Scanner(System.in);
		int opcion = -1;
		do {
		System.out.println("--------- MENU PRINCIPAL ----\n");
		System.out.println("	1- MENU PROFESORES");
		System.out.println("	2- MENU SOCIOS");
		System.out.println("	3- MENU SERVICIOS");
		System.out.println("	4- MENU ACTIVIDADES");
		System.out.println("	0- SALIR");
		System.out.println("	Indique el menu al que desea acceder");
		opcion = scan.nextInt();
		scan.nextLine();
		
		switch (opcion) {
		case 1: 
			menuProfesores(club);
			break;
			
		case 2: 
			menuSocios(club);
			 break;
			 
		case 3: 
			menuServicios(club);
			 break;  
			 
		case 4 : 
			menuActividades(club);
			 break;
			   
		case 0 : System.out.println("\n saliste del menu\n");
		        break;
		
		default : System.out.println("    numero de opcion invalido");
		         break;
			}
		
		}while(opcion != 0);
//		scan.close();
	}
	
//	---------------------------------------
//  MOSTRAR
//----------------------------------------
	
	
	public static void mostrarSociosDelClub(AdministracionClub club) {
		System.out.println("\n ----- SOCIOS DEL CLUB -----\n");
		mostrarSocios(club.getLstSocios());
		System.out.println("\n----------------------------------\n");
			
	}
	
	public static void mostrarSocios(List<Socio> socios) {
		for(Socio socio : socios) {
			System.out.println(socio.toString());
		}
	}
	
	public static void mostrarServiciosDelClub(AdministracionClub club) {
		System.out.println("\n ----- SERVICIOS DEL CLUB -----\n");
		for(Servicio servicio : club.getLstServicios()) {
	    System.out.println(servicio.toString());
	    System.out.println("--alquileres--");
		System.out.println(servicio.getLstAlquileres());
	
	
		}
		
		System.out.println("\n----------------------------------\n");
	}
	
	public static void mostrarProfesoresDelClub(AdministracionClub club) {
		System.out.println("\n ----- PROFESORES DEL CLUB -----\n");
		for(Profesor profesor : club.getLstProfesores()) {
			System.out.println(profesor.toString());
		}
	
		System.out.println("\n----------------------------------\n");
	}
	
	public static void mostrarActividadesDelClub(AdministracionClub club) {
		System.out.println("\n\n             ACTIVIDADES DEL CLUB                 ");
		for(Actividad actividad : club.getLstActividades()) {
			mostrarActividad(actividad);
	 }
	}
	
	public static void mostrarDias(List<String> dias) {
		System.out.print("\nDias [");
		for(String dia : dias) {
			System.out.print(" - "+ dia);
		}
		System.out.print(" ]\n");
	}
	
	
	public static void mostrarActividad(Actividad actividad) {
		System.out.println("\n\n-------------------------- "+ actividad.getNombreActividad().toUpperCase() +" -----------------------------\n");
		System.out.println(actividad.toString());
		mostrarDias(actividad.getLstDias());
		System.out.println("\n--- Socios de la actividad ---\n");
		mostrarSocios(actividad.getLstSocios());
		 
	}
	
	public static void mostrarClub(AdministracionClub club) {
		System.out.println("\n\n----------------------------------------------------------------------------");
		System.out.println("                          DEPORTIVO CHELO                                   ");
		System.out.println("----------------------------------------------------------------------------\n");
		System.out.println("Integrenates : ");
		System.out.println("");
		mostrarSociosDelClub(club);
		mostrarProfesoresDelClub(club);
		mostrarServiciosDelClub(club);
		mostrarActividadesDelClub(club);
	}
	

}
