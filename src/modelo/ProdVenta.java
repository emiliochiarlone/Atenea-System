package modelo;

import java.io.Serializable;

public class ProdVenta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1287380804885439201L;
	private int id_producto;
	private int cantidad;
	

	public ProdVenta(int id_producto, int cantidad) {
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}


	public int getId_producto() {
		return id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}
	
	// Se usa cuando se compra X cantidad del producto a un proveedor. Parámetro: cantidad comprada del producto
	public Mensaje Agregar(int can) {
		if (can<1) {
			return Mensaje.error;
		}
		this.cantidad = this.cantidad + can;
		
		return Mensaje.listo;
	}
	
	public Mensaje Descontar(int can) { //Se usa cuando se vende una cantidad del producto al cliente
		if (this.cantidad<can) {
			return Mensaje.nostock;
		}
		this.cantidad = this.cantidad-can;
		return Mensaje.listo;
	}
	
	@Override
	public String toString() {
		return this.id_producto + " " + this.cantidad;
	}

}
