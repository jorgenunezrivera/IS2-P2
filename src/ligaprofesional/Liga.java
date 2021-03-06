package ligaprofesional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Liga {
	boolean fin=false;
	private ArrayList<Jugador> lista_jugadores=new ArrayList<Jugador>();
	private ArrayList<Equipo> lista_equipos = new ArrayList<Equipo>();
	private ArrayList<Traspasos> lista_traspasos = new ArrayList<Traspasos>();
	private String nombre_equipo = null;
	private String nombre_jugador = null;
	private Equipo equipo_origen = null;
	private Equipo equipo_destino = null;
	private Jugador jugador_a_cambiar = null;
	private Demarcacion demarcacion = null;
	int importe_caja = -1;
	int coste_anual=-1;
	int clausula = -1;
	int numero_abonados = -1;
	int n_demarcacion=-1;
        int gastos=-1;
        boolean ya_existe_equipo =true;
	boolean ya_existe_jugador = true;
	private String filename;
	PrintWriter writer = null;
	
	String line=null;
	
	public  void main(){
		Scanner main = new Scanner(System.in);
		int in=-1;
		while(!fin){
			System.out.println("1.Registrar un nuevo equipo\n2.Registrar un nuevo jugador \n3.Registrar un traspaso de jugador \n4.Listar en pantalla los datos básicos de los equipos registrados\n5.Listar los jugadores de cada equipo\n6.Mostrar los traspasos realizados\n7.Cambiar demarcacion\n8.Comprobar_fair_play\n9.Comprpobar demarcaciones\n.Salir del programa  ");
			line = main.nextLine();
			try{
			in=Integer.parseInt(line);
			}catch (Exception e){
				in =-1;
			}
			switch(in){
			case 1:
				numero_abonados=-1;
				importe_caja=-1;
				boolean ya_existe_equipo =true;
				while(ya_existe_equipo)
				{
					System.out.println("Introduce el nombre del equipo\n");
					nombre_equipo=main.nextLine(); //COMPROBAR ERRORES?comprobar si existe ya!!!
					if(existe_equipo(nombre_equipo)==null)
						ya_existe_equipo=false;
					else
						System.out.println("Ya existe un equipo con ese nombre");
				}
				while(importe_caja<0)
				{
					System.out.println("Introduce el importe de caja del equipo\n");
					//if(main.hasNextInt())
					line=main.nextLine(); //COMPROBAR ERRORES
					try{
						importe_caja=Integer.parseInt(line);
					}catch (Exception e){
						importe_caja=-1;
					}
				}
				while(numero_abonados<0)
				{
					System.out.println("Introduce el numero de abonados del equipo\n");
					line=main.nextLine();
					try{
						numero_abonados=Integer.parseInt(line);
					}catch(Exception e){
						numero_abonados=-1;
					}
						//COMPROBAR ERRORES
				}	
                                while(gastos<0)
				{
					System.out.println("Introduce los gastos fijos del equipo\n");
					line=main.nextLine();
					try{
						gastos=Integer.parseInt(line);
					}catch(Exception e){
						gastos=-1;
					}
						//COMPROBAR ERRORES
				}
                               	registrar_equipo(nombre_equipo,importe_caja,numero_abonados,gastos);
				
				break;
				
			case 2:
				if(lista_equipos.isEmpty()){
					System.out.println("No se ha creado ningun equipo. Debes crear equipos primero");
					break;
				}
				demarcacion=null;
				equipo_destino = null;
				clausula=-1;
				coste_anual=-1;
				ya_existe_jugador=true;
				while(ya_existe_jugador){
					System.out.println("Introduce el nombre del jugador\n");
					nombre_jugador=main.nextLine(); //COMPROBAR ERRORES
					if(existe_jugador(nombre_jugador)==null)
						ya_existe_jugador=false;
					else
						System.out.println("Ya existe un jugador con ese nombre");
				}
				while(demarcacion==null){
					System.out.println("Introduce la demarcacion del jugador (1:Portero 2:Defensa 3:Medio 4:Delantero\n");
					line=main.nextLine();
					try{
						n_demarcacion=Integer.parseInt(line); //COMPROBAR ERRORES
						demarcacion=comprobar_demarcacion(n_demarcacion);
					}catch(Exception e){
						demarcacion=null;
					}
				}
				while(equipo_destino==null){
					System.out.println("Introduce el nombre del equipo al que pertenece\n");
					if(main.hasNextLine()){
						nombre_equipo=main.nextLine(); //COMPROBAR ERRORES
						equipo_destino=comprobar_equipo(nombre_equipo);		
					}
				}
				while(clausula<0){
					System.out.println("Introduce la nueva clausula\n");
					line=main.nextLine();
					try{
						clausula=Integer.parseInt(line); //COMPROBAR ERRORES	
					}catch(Exception e){
						clausula=-1;
					}
				}
				while(coste_anual<0){
					System.out.println("Introduce el coste anual\n");
					line=main.nextLine();
					try{
						coste_anual=Integer.parseInt(line); //COMPROBAR ERRORES	
					}catch(Exception e){
						coste_anual=-1;
					}
				}
				registrar_jugador(nombre_jugador,demarcacion,clausula,equipo_destino,coste_anual);
				break;
				
			case 3:
				if(lista_equipos.isEmpty()){
					System.out.println("No se ha creado ningun equipo. Debes crear equipos primero");
					break;
				}
				if(lista_jugadores.isEmpty()){
					System.out.println("No se ha creado ningun jugador. Debes crear jugadores primero");
					break;
				}
				equipo_destino = null;
				jugador_a_cambiar=null;
				clausula=-1;
				while (jugador_a_cambiar==null){
					System.out.println("Introduce el nombre del jugador que quieres cambiar\n");
					nombre_jugador=main.nextLine(); //COMPROBAR ERRORES????
					jugador_a_cambiar=comprobar_jugador(nombre_jugador);
				}				
				while(equipo_destino==null){
					System.out.println("Introduce el nombre del equipo al que se cambiara\n");
					nombre_equipo=main.nextLine(); //COMPROBAR ERRORES
					equipo_destino=comprobar_equipo(nombre_equipo);
				}				
				while(clausula<0){
					System.out.println("Introduce la clausula\n");
					line=main.nextLine();
					try{
						clausula=Integer.parseInt(line);//COMPROBAR ERRORES	
					}catch (Exception e){
						clausula=-1;
					}
				}
				traspasar(new Date(),jugador_a_cambiar,equipo_destino,clausula);
				break;
		
			case 4:
				System.out.println(listar_equipos());
				break;
			case 5:

				System.out.println(listar_jugadores());
				break;
			case 6:

				System.out.println(listar_traspasos());
				break;
                                
			case 7:
                                while (jugador_a_cambiar==null){
					System.out.println("Introduce el nombre del jugador cuya demarcacion quieres cambiar\n");
					nombre_jugador=main.nextLine(); //COMPROBAR ERRORES????
					jugador_a_cambiar=comprobar_jugador(nombre_jugador);
				}				
                                while(demarcacion==null){
					System.out.println("Introduce la nueva demarcacion del jugador (1:Portero 2:Defensa 3:Medio 4:Delantero\n");
					line=main.nextLine();
					try{
						n_demarcacion=Integer.parseInt(line); //COMPROBAR ERRORES
						demarcacion=comprobar_demarcacion(n_demarcacion);
					}catch(Exception e){
						demarcacion=null;
					}
				}
                                jugador_a_cambiar.cambiarDemarcacion(demarcacion);
                                break;
               case 8: 
            	    equipo_destino=null;
            	    if(lista_equipos.isEmpty()){
   						System.out.println("No se ha creado ningun equipo. Debes crear equipos primero");
   						break;
   					}
	            	while(equipo_destino==null){
	   					System.out.println("Introduce el nombre del equipo cuyo fair play quieres comprobar\n");
	   					if(main.hasNextLine()){
	   						nombre_equipo=main.nextLine(); //COMPROBAR ERRORES
	   						equipo_destino=comprobar_equipo(nombre_equipo);		
	   					}
	   				}
	            	if(EvaluaFairPlay(equipo_destino))
	            		System.out.println("SI Cumple el fair play financiero\n");
	            	else
	            		System.out.println("No Cumple el fair play financiero\n");   					
            	   break;
               case 9: 
            	   equipo_destino=null;
           	    if(lista_equipos.isEmpty()){
  						System.out.println("No se ha creado ningun equipo. Debes crear equipos primero");
  						break;
  					}
	            	while(equipo_destino==null){
	   					System.out.println("Introduce el nombre del equipo cuyas demarcaciones quieres comprobar\n");
	   					if(main.hasNextLine()){
	   						nombre_equipo=main.nextLine(); //COMPROBAR ERRORES
	   						equipo_destino=comprobar_equipo(nombre_equipo);		
	   					}
	   				}
	            	EvaluaDemarcacion(equipo_destino);
	            	break;
               case 10:
            	   
				writer = null;
				while(writer==null){
					System.out.println("Introduce el nombre del fichero donde se guardaran los datos");
					filename=main.nextLine();
					writer=comprobar_archivo(filename);
				}
				writer.println("Equipos:\n");
				writer.println(listar_equipos());
				writer.println("Jugadores:\n");
				writer.println(listar_jugadores());
				writer.println("Traspasos:\n");
				writer.println(listar_traspasos());
				writer.close();
				fin=true;
				
				break;
			default:
				System.out.println("Opcion incorrecta\n");
			}
				
		}
		main.close();

	}
	
	void registrar_equipo(String nombre,int importecaja,int abonados,int gasto)
	{
		lista_equipos.add(new Equipo(nombre,importecaja,abonados,gasto));
			
	}
	
	void registrar_jugador(String nombre,Demarcacion demarcacion,int clausula ,Equipo equipo_destino,int coste_anual){
		lista_jugadores.add(new Jugador(nombre,demarcacion,clausula,equipo_destino,coste_anual));		
	}
		
	void traspasar(Date fecha,Jugador jugador, Equipo destino,int nueva_clausula){
		Equipo origen=jugador.getEquipo();
		int clausula_antigua=jugador.getClausula();
		jugador.transpasar(destino, nueva_clausula);
		origen.IngresoDinero(clausula_antigua);
		destino.RestaDinero(clausula_antigua);
		lista_traspasos.add(new Traspasos(fecha,origen,destino,jugador,clausula_antigua));
	}
	
	
	//FUNCIONES DE COMPROBACION
	
	Jugador comprobar_jugador(String j){
		Jugador jugador = existe_jugador(j);
		if(jugador==null)	System.out.println("Nombre de jugador incorrecto");
		return jugador;
	}
	
	Jugador existe_jugador(String j)
	{
		for (Jugador jugador : lista_jugadores)
		{	
			if(jugador.getNombre().equals(j))
				return jugador;			
		}
		return null;
	}
	
	Equipo existe_equipo(String e){
		for (Equipo equipo : lista_equipos)
		{	
			if(equipo.getNombreEquipo().equals(e))
				return equipo;
		}
		return null;
	}
	
	Equipo comprobar_equipo(String e)
	{
		Equipo equipo = existe_equipo(e);
		if(equipo==null)System.out.println("El equipo no existe");
		return equipo;	
	}
	
	
	Demarcacion comprobar_demarcacion(int n)
	{
		Demarcacion demarcacion=null;
		switch(n){
			case 1:
				demarcacion=Demarcacion.PORTERO;
				break;
			case 2:
				demarcacion=Demarcacion.DEFENSA;
				break;
			case 3:
				demarcacion=Demarcacion.MEDIO;
				break;
			case 4:
				demarcacion=Demarcacion.DELANTERO;
				break;	
			default:
				System.out.println("Valor incorrecto");
		}
		return demarcacion;
	}
	
	PrintWriter comprobar_archivo(String nombre){
		try{
			PrintWriter w =  new PrintWriter(nombre, "UTF-8");
			return w;
		}catch(FileNotFoundException e){
			System.out.println("Nombre invalido o sistema de archivos no escribible. Vuelvalo a intentar con otro nombre");
			return null;
		}catch(Exception e){
			return null;
		}
	}
	
	//FUNCIONES DE LISTADO
	
	String listar_equipos()
	{
		String s="";
		for (Equipo equipo : lista_equipos)
			s=s+equipo+"\n";
		return s;
	}
	
	String listar_jugadores()
	{
		String s = "";
		for (Equipo equipo : lista_equipos)
		{
			s=s+equipo.getNombreEquipo() + ":\n";
			for (Jugador jugador: lista_jugadores){
				if(jugador.getEquipo().equals(equipo)){
					s=s+jugador+"\n";
				}
			}
		}
		return s;
	}
	
	String listar_traspasos()
	{
		String s  = "";
		Collections.sort(lista_traspasos);
		for (Traspasos traspaso:lista_traspasos)
			s=s +traspaso + "\n"; //ordenaaaaaar
		return s;
	}
	
	public boolean EvaluaFairPlay(Equipo equipo){
    	int gasto=equipo.getGastos();
		for (Jugador jugador: lista_jugadores){
			if(jugador.getEquipo().equals(equipo)){
				gasto+=jugador.getCosteAnual()*2;
			}
		}
		if(equipo.getImporte()>gasto)
			return true;
		else
			return false;
    	
    }
	

	public void EvaluaDemarcacion(Equipo equipo){
    	int porteros=0;int minporteros=2;
    	int defensas=0;int mindefensas=4;
    	int medios=0;int minmedios=4;
    	int delanteros =0;int mindelanteros = 3;
		for (Jugador jugador: lista_jugadores){
			if(jugador.getEquipo().equals(equipo)){
				Demarcacion d =jugador.getDemarcacion();
				switch(d){
				case PORTERO:
					porteros++;
					break;
				case DEFENSA:
					defensas++;
					break;
				case MEDIO:
					medios++;
					break;
				case DELANTERO:
					delanteros++;
					break;
				}	
			}
		}
		System.out.println("El equipo "+ equipo.getNombreEquipo() + "tiene:");
		System.out.println(porteros + " porteros");
		System.out.println(defensas + " defensas");
		System.out.println(medios + " medios");
		System.out.println(delanteros + " delanteros");
		if(porteros>minporteros && defensas>mindefensas && medios>minmedios && delanteros>mindelanteros)
			System.out.println("El equipo si cumple los requisitos");	
		else
			System.out.println("El equipo no cumple los requisitos");
    }
	
}