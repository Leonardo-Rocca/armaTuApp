package com.example.lrocca.myapplication.modelo;

public class NormalDescriptor extends Descriptor {

	public void change(Jugador jugador) {
		jugador.setDescriptor(new NoPointsDescriptor());
	}

	public String description(Jugador jugador) {
	      return jugador.getName() + " - " + String.valueOf(jugador.getHability());
	}

}
