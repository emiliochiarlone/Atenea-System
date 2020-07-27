package modelo;

import java.io.Serializable;

public class Producto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3517932917890973544L;
	/**
	 * 
	 */
	private int cant_min;
	private int id;
	private String nombre;
	private double precio_costo;
	private double precio_venta;
	
	public Producto() {
	}
	
	@Override
	public String toString() {
		return (this.nombre);
		
	}
	

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio_costo() {
		return precio_costo;
	}

	public void setPrecio_costo(double precio_costo) {
		this.precio_costo = precio_costo;
	}

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setCant_min(int cant_min) {
		this.cant_min = cant_min;
	}

	public int getCant_min() {
		return cant_min;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
}
