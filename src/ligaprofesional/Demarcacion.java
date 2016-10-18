package ligaprofesional;

public enum Demarcacion {
	PORTERO("Portero"),DEFENSA("Defensa"),MEDIO("Medio"),DELANTERO("Delantero");
	private final String tipo;
	
	Demarcacion(String tipo){
		this.tipo=tipo;
	}
	
	public String toString(){
		return tipo;
	}
	
}
