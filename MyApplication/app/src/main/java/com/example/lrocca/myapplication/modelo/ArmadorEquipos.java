package com.example.lrocca.myapplication.modelo;

import java.util.ArrayList;
import java.util.Comparator;
//import java.util.stream.Collectors;

public class ArmadorEquipos {
	
	
	
	
public	ArrayList<Match> armarEquipo(ArrayList<Jugador> jugadores){
	int sobrantes = 10-jugadores.size();
	for(int i =0;i<Math.abs(sobrantes);i++){
		jugadores.add(new Jugador("...", 0));
	}
	
	ArrayList<String> combS = CombinatoriaNumeros.devolverCombinatoriaString();
	ArrayList<Equipo> es1 = armarCombinacionesEquiposString(jugadores,combS);
	
	return armarMatchConRivales(es1,jugadores);
		
	}

//E------------------
private ArrayList<Equipo> armarCombinacionesEquiposString(ArrayList<Jugador> jugadores, ArrayList<String> comb) {
	int cantidadPlayers = comb.get(0).length();
	ArrayList<Equipo> es1 = new ArrayList<Equipo>();
	Equipo e1;
	int indiceJugador;
	for(int i = 0;i<comb.size();i++){
		e1 = new Equipo();
		for(int j = 0;j<cantidadPlayers;j++){
			indiceJugador =  Integer.parseInt(comb.get(i).substring(j, j+1) );
			e1.agregar(jugadores.get(indiceJugador));
		}
		es1.add(e1);
	}

	return es1;
}

private ArrayList<Match> armarMatchConRivales(ArrayList<Equipo> es1, ArrayList<Jugador> jugadores) {

	ArrayList<Match> matcheos= new ArrayList<Match>();

	for (int i=0;i<es1.size();i++){

		Equipo equipoArmado= es1.get(i);
		matcheos.add(new Match(equipoArmado, this.rivalDe(equipoArmado,jugadores)));

	}
	//es1.forEach(equipoArmado -> {
  //		matcheos.add(new Match(equipoArmado, this.rivalDe(equipoArmado,jugadores)));
  //					});
	return matcheos;
}


private Equipo rivalDe(Equipo equipoArmado, ArrayList<Jugador> jugadores) {
	ArrayList<Jugador> aux = jugadores;
	aux = this.filtrarContainPlayers(equipoArmado,jugadores);//(ArrayList<Jugador>) aux.stream().filter(j -> !equipoArmado.jugadores.contains(j)).collect(Collectors.toList());
	return new Equipo(aux);
}

	private ArrayList<Jugador> filtrarContainPlayers(Equipo equipoArmado, ArrayList<Jugador> jugadores) {
		ArrayList<Jugador> aux = new ArrayList<Jugador>();
		for(int i =0;i<jugadores.size();i++){
			if(!equipoArmado.jugadores.contains(jugadores.get(i)))
				aux.add(jugadores.get(i));
		}
		return aux;
	}

public Match armarEquipoMasParejo(ArrayList<Jugador> jugadores) {

	   ArrayList<Match> m = this.armarEquipo(jugadores);
		 ordenarPorHabilidad(m);

		return m.get(0);
}

private void ordenarPorHabilidad(ArrayList<Match> m) {
	Comparator<Match> c = new Comparator<Match>() {
		@Override
		public int compare(Match o1, Match o2) {
			return o1.habilidadAcumulada();
		}
	}; //(s1, s2) -> s1.habilidadAcumulada().compareTo(s2.habilidadAcumulada());
	//new Ordenador().orderList(m);
	//		m.sort(c);                //<---------descomentar !!!!!!!!!! y arreglar

}

public ArrayList<Match> armarEquipoConJugadores(ArrayList<Jugador> jugadores, ArrayList<Jugador> jugadoresEq1, ArrayList<Jugador> jugadoresEq2) {
	
	ArrayList<Match> matcheos = this.armarEquipo(jugadores);
	matcheos = this.filtrarTieneJugadores(matcheos,jugadoresEq1,jugadoresEq2);
			//(ArrayList<Match>) matcheos.stream().filter(m -> m.equipo1.tiene(jugadoresEq1) &&  m.equipo2.tiene(jugadoresEq2)).collect(Collectors.toList());
	this.ordenarPorHabilidad(matcheos);
	return matcheos;
}

	private ArrayList<Match> filtrarTieneJugadores(ArrayList<Match> matcheos, ArrayList<Jugador> jugadoresEq1, ArrayList<Jugador> jugadoresEq2) {
		 ArrayList<Match> aux =new ArrayList<Match>();
		for(int i =0;i<matcheos.size();i++){
			if(matcheos.get(i).equipo1.tiene(jugadoresEq1) &&  matcheos.get(i).equipo2.tiene(jugadoresEq2))
				aux.add(matcheos.get(i));
		}
		return aux;
	}

	public ArrayList<Match> armarEquipoConJugadores(ArrayList<Jugador> jugadores, ArrayList<Jugador> jugadoresEquipo) {
	return this.armarEquipoConJugadores(jugadores, jugadoresEquipo,new ArrayList<Jugador>());
}	
	
	
	//version anterior
private ArrayList<Equipo> armarCombinacionesEquipos(ArrayList<Jugador> jugadores, ArrayList<Integer> comb) {

	int cantidadPlayers = Integer.toString(comb.get(0)).length();
	ArrayList<Equipo> es1 = new ArrayList<Equipo>();
	Equipo e1;
	int indiceJugador;
	for(int i = 0;i<comb.size();i++){
		e1 = new Equipo();
		for(int j = 0;j<cantidadPlayers;j++){
			indiceJugador =  Integer.parseInt(			Integer.toString(comb.get(i)).substring(j, j+1) );
			e1.agregar(jugadores.get(indiceJugador));
		}
		es1.add(e1);
	}
	
	return es1;
}


}
