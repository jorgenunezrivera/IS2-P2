/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ligaprofesional;

import java.util.Date;

/**
 *
 * @author jhonf_000
 */
public class Traspasos implements Comparable<Traspasos>{
    private Date fechatraspaso;
    private Equipo equiporigen;
    private Equipo equipodestino;
    private int importe;
    private Jugador jugador;
    
            
    public Traspasos(Date fecha, Equipo equiporigen, Equipo equipodestino, Jugador jugador,int importe){
        this.fechatraspaso = fecha;
        this.equiporigen = equiporigen;
        this.equipodestino = equipodestino;
        this.jugador=jugador;
        this.importe = importe;
    }
    
    @Override 
    public String toString(){
        String a = "Jugador" + jugador.getNombre() + "\n" + "Equipo Origen:" + equiporigen.getNombreEquipo() + "\n" + "Equipo Destino:" + equipodestino.getNombreEquipo() + 
                    "\n" + "Fecha de Traspaso:" + fechatraspaso + "Importe:" + importe;
        
        return a;
    }

	@Override
	public int compareTo(Traspasos o) {
		return importe-o.importe;
	}
    
    
}
