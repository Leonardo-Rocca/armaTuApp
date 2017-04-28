package com.example.lrocca.myapplication;

import java.util.ArrayList;

public class Match {
	   Equipo equipo1 = new Equipo();
       Equipo equipo2 = new Equipo();
       
        Match(Equipo e1,Equipo e2){
          equipo1 = e1;
          equipo2 = e2;
          }

		public Integer habilidadAcumulada() {
		return	Math.abs(equipo1.habilidad() - equipo2.habilidad());
		
		}

		public void imprimirEquipos() {
			System.out.printf(" \n equipo 1 \n");
			equipo1.imprimir();
			System.out.printf(" \n equipo 2 \n");
			equipo2.imprimir();
			
		}
		public ArrayList<Jugador> getJugadoresEquipo1(){
			return equipo1.jugadores;
		}

		public ArrayList<Jugador> getJugadoresEquipo2() {
			return equipo2.jugadores;
		}

		public String getHability(int i) {
			 Equipo equipo;
			 if(i==1){
				 equipo= equipo1;
			 }else{
				 equipo=equipo2;
			 }
			 
			return String.valueOf(equipo.habilidad());
		}
		
}
