package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Catalogo implements Serializable, iAlmacenable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7451827360934982885L;
	
	private int id_generator;
	
	private ArrayList<Producto> productos;
	
	public Catalogo () {
		this.productos = new ArrayList<Producto>();
		this.id_generator = 1;
	}
	
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(ArrayList<Producto> productos) throws FileNotFoundException, IOException {
		this.productos = productos;
		this.almacenarObjeto("catalogo.txt");
	}
	
	public Producto getProducto (int id_producto) {
		ArrayList<Producto> prods = this.productos;
		for (Producto i:prods) {
			if (id_producto == i.getId()) {
				return i;
			}
		}
		return null;
	}
	
	public int getId_generator() {
		return this.id_generator;
	}

	public void setId_generator(int id_generator) throws FileNotFoundException, IOException {
		this.id_generator = id_generator;
		this.almacenarObjeto("catalogo.txt");
	}

	public void agregarProducto(Producto producto) throws FileNotFoundException, IOException {
		producto.setId(this.id_generator);
		this.productos.add(producto);
		id_generator++;
		this.almacenarObjeto("catalogo.txt");
	}
	

	public int cantidadProductos() {
		return this.productos.size();
	}
	
	public Mensaje editarProducto (int id, int cant_min, double precio_costo, double precio_venta) throws FileNotFoundException, IOException {
		for (Producto i: this.productos) {
			if (i.getId() == id) {
				i.setCant_min(cant_min);
				i.setPrecio_costo(precio_costo);
				i.setPrecio_venta(precio_venta);
				this.almacenarObjeto("catalogo.txt");
				return Mensaje.listo;
			}
		}
		return Mensaje.noexiste;
	}
	
	public int getIdProducto (String nombre) {
		ArrayList<Producto> prods = this.productos;
		for (Producto i: prods) {
			if (i.getNombre().equals(nombre)) {
				return i.getId();
			}
		}
		return 0;
	}
	
	public double getValor (int id) {
		if (getProducto(id) == null) {
			return 0;
		}
		return getProducto(id).getPrecio_costo();
	}
	
	public Mensaje eliminarProducto(int id) throws FileNotFoundException, IOException {
		if (this.productos.remove(this.getProducto(id))){
			this.almacenarObjeto("catalogo.txt");
			return Mensaje.listo;
		}
		else {
			return Mensaje.noexiste;
		}
	}
}
