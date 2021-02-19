package net.alepuzio.spring.model;

public class Upper {

	private String origin;

	public Upper(String newInput) {
		this.origin = newInput;
	}

	public String value() {
		return this.origin.toUpperCase();
	}

}
