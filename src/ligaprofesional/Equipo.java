/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ligaprofesional;

/**
 *
 * @author jhonf_000
 */
public class Equipo {
    private int idequipo;
    private String nombreEquipo = "";
    private int importecaja;
    private int abonados;
    private static int count_equipos=0;
    private int gastos_fijos;
    private int gastos_variables;
    
    //private Jugador[] jugadores = new Jugador[22];
    
    public Equipo(String nombre,int importecaja, int abonados, int gastos_fijos, int gastos_variables){
    	this.idequipo=count_equipos++;
        this.nombreEquipo = nombre;
        this.importecaja = importecaja;
        this.abonados = abonados;
        this.gastos_fijos = gastos_fijos;
        this.gastos_variables = gastos_variables;
    }
    
    public void setNombreEquipo(String nombre){
        this.nombreEquipo = nombre;
    }
    
    public String getNombreEquipo(){
        return this.nombreEquipo;
    }
    
    public void setImporte (int importecaja){
        this.importecaja = importecaja;
    }
    
    public int getImporte (){
        return this.importecaja;
    }
    
    public void setAbonados (int abonados){
        this.abonados = abonados;
    }
    
    public int getAbonados (){
        return this.abonados;
    }
    
    public void IngresoDinero(int importe){
        this.importecaja += importe;
    }
    
    
    public void RestaDinero(int importe){
        this.importecaja -= importe;    
    }
    
    @Override 
    public String toString(){
        String a = "Equipo" + idequipo + "\n" + "Nombre Equipo:" + nombreEquipo + "\n" + "Importe de Caja:" + importecaja + 
                    "\n" + "Numero de abonados:" + abonados + "\nGastos fijos anuales: " + gastos_fijos  + "\nGastos variables anuales: " + gastos_variables;
        
        return a;
    }
}
