package com.example.demo.model.func;



import java.util.function.Function;



import com.example.demo.model.dto.GenerarPrecioIvaIn;

public class AplicarIva implements Function<GenerarPrecioIvaIn, String>  {

	private static final String VAR_IVA = "poc.java.var.iva";
	private static final String VALID_COST = "Precio generado con IVA: ";
	private static final String INVALID_COST = "El precio introducido es menor que 0 o nulo";
	
	public String env_iva = System.getenv(VAR_IVA);
	public double iva = 0;
	public double precioConIva = 0;
	public double precioFinal = 0;
@Override
	public String apply(GenerarPrecioIvaIn precio){
		if (env_iva == null) {
			iva = 21;
		} else {
			iva = Double.parseDouble(env_iva);
		}

		String response = "";
		if (precio.getPrecio() > 0 && precio != null) {
			precioConIva = (precio.getPrecio() * iva) / 100;
			precioFinal = precio.getPrecio() + precioConIva;
			response = VALID_COST + precioFinal;
		} 
		else
			throw new RuntimeException("INVALID_COST");

		return response;
	}
}