package com.example.lrocca.myapplication.modelo;

import java.util.ArrayList;

public class Equipo {
	String nombre;
    ArrayList<Jugador> jugadores =  new ArrayList<Jugador>();
    
    public Equipo(ArrayList<Jugador> players) {
    	jugadores =players;
	}

	public Equipo() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre(){
        return this.nombre;
    }
    
    void agregar(Jugador j){
        jugadores.add(j);
    }

	public int habilidad() {
		int acum  =0;
		for(int i = 0;i< jugadores.size();i++){
			acum = acum + jugadores.get(i).getHability();
		}
		return acum;
	}

	public void imprimir() {
		System.out.printf(" \n Habilidad %d  \n",this.habilidad());
		
		for(int i = 0;i< jugadores.size();i++){
			System.out.printf("%s ",jugadores.get(i).getName());

			System.out.printf("%s \n",jugadores.get(i).getHability());
		}
		
	}

	public Boolean tiene(ArrayList<Jugador> jugadoresEq1) {
		
		return jugadores.containsAll(jugadoresEq1);
	}
    
}
