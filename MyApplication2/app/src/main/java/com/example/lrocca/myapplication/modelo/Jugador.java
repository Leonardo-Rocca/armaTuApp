package com.example.lrocca.myapplication.modelo;

import com.example.lrocca.myapplication.Persistencia.FileSystemAdapter;

public class Jugador implements Persistible {
	
	private String name ="no name";
	private int hability = -1;
	private transient Descriptor descriptor;
	private int id = -1;

	public Jugador(String nombre, int habilidad) {
		initialice(nombre, habilidad);
	}

	public Jugador(String n, String h, int id) {
		int habilidad=Integer.valueOf(h);
		initialice(n, habilidad);
		setId(id);
	}

	private void initialice(String nombre, int habilidad) {
		setName(nombre);
		setHability(habilidad);
		setDescriptor(new NormalDescriptor());
		setId(FileSystemAdapter.getFS().getCurrentId());
	}

	public Jugador(String n, String h) {
		int habilidad=Integer.valueOf(h);
		initialice(n, habilidad);
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
		 if(getHability()==0)return "";
		 return getDescriptor().description(this);
	    }

	public void changeDescription() {
		getDescriptor().change(this);
		
	}

	public Descriptor getDescriptor() {
		if (descriptor ==null)this.setDescriptor( new NormalDescriptor());
			return descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStringHability() {
		return String.valueOf(getHability());
	}
}
