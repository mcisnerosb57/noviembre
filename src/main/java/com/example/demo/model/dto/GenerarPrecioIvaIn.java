package com.example.demo.model.dto;

public class GenerarPrecioIvaIn {
	public double precio = 0;
	
	public GenerarPrecioIvaIn(){
		
	}

	public GenerarPrecioIvaIn(double precio) {
		super();
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "GenerarPrecioIvaIn [precio=" + precio + "]";
	}
}
