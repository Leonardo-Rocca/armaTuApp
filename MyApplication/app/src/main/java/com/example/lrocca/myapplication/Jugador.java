package com.example.lrocca.myapplication;

import java.io.Serializable;

public class Jugador implements Serializable {

	private String name;
	private int hability;

	public Jugador(String nombre, int habilidad) {
		setName(nombre);
		setHability(habilidad);
	}

	public int getHability() {
		return hability;
	}

	public void setHability(int hability) {
		this.hability = hability;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 public String toString() {
		 if(hability==0)return "";
	      return name + " - " + String.valueOf(this.getHability());
	    }

}
