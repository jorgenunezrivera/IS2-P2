package ligaprofesional;

public class Jugador {
	private int idjugador;
	private String nombre;
	private Demarcacion demarcacion;
	private int clausula;
	private Equipo equipo;
	private static int count_jugadores=0;
	//Constructor de la clase jugador, recibe todos los datos (nombre, demarcacion, clausula, equipo
	
	Jugador(String nombre,Demarcacion demarcacion,int clausula,Equipo equipo){
		this.idjugador=count_jugadores++;
		this.nombre=nombre;
		this.demarcacion=demarcacion;
		this.clausula=clausula;
		this.equipo=equipo;
	}
	
	public void transpasar(Equipo dest,int nueva_clausula){
		this.equipo=dest;		
		this.clausula=nueva_clausula;
	}

	public String getNombre() {
		return nombre;
	}


	public int getClausula() {
		return clausula;
	}

	public Equipo getEquipo() {
		return equipo;
	}
	
	@Override 
    public String toString(){
        String a = "Jugador : ID " + idjugador + " Nombre: " + nombre + " Demarcacion: " + demarcacion + " Clausula: " + clausula;
        return a;
    }
}
