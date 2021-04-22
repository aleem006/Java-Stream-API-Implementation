package com.OTTR.org;

public class Minion {
	
	private String name;
	private String nEyes;
	private String item;
	
	public Minion(String name, String nEyes, String item) {
		this.name = name;
		this.nEyes = nEyes;
		this.item = item;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getnEyes() {
		return nEyes;
	}

	public void setnEyes(String nEyes) {
		this.nEyes = nEyes;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
		return "  Minion [name=" + name + ", nEyes=" + nEyes + ", item=" + item + "]";
	}
	
	public String toStringFileFormate() {
		return name + ", " + nEyes + ", " + item ;
	}
	
}