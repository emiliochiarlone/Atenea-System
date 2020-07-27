package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public class Stock implements Serializable, iAlmacenable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4845375424214968056L;
	/**
	 * 
	 */
	private ArrayList<ProdVenta> productos;
	private ArrayList<Integer> faltasStock; 
	
	
	public Stock() {
		this.productos = new ArrayList<ProdVenta>();
		this.faltasStock = new ArrayList<Integer>();
		
	}
	
	public Mensaje NuevaCompra(int id_producto, int cantidad, int cant_min) throws FileNotFoundException, IOException {
		boolean falta = false;
		for (ProdVenta i:productos) {
			if (i.getId_producto() == id_producto) {
				i.Agregar(cantidad);
				if (this.EnFalta(i)) {
					if (cant_min >= i.getCantidad()) {
				    	 falta = true;
				   	}
				}
				if (falta) {
					this.almacenarObjeto("stock.txt");
					return Mensaje.enfalta;
				}
				this.faltasStock.remove((Object)i.getId_producto());
				this.almacenarObjeto("stock.txt");
				return Mensaje.listo;
			}
		}
		ProdVenta prod = new ProdVenta(id_producto, cantidad);
		this.productos.add(prod);
		this.almacenarObjeto("stock.txt");
		return Mensaje.listo;
	}
	

	public ArrayList<ProdVenta> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<ProdVenta> productos) throws FileNotFoundException, IOException {
		this.productos = productos;
		this.almacenarObjeto("stock.txt");
	}
	
	private boolean EnFalta(Producto producto) {
		if (this.faltasStock.contains(producto.getId())) {
			return true;
		}
		return false;
	}
	
	private boolean EnFalta(ProdVenta prodv) {
		
		for (ProdVenta i: productos) {
			if (prodv.getId_producto() == i.getId_producto()) {
				if (prodv.getId_producto()>=i.getCantidad()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public int getCantidadProducto (int id) {
		if (id<1) {
			return -1;
		}
		for (ProdVenta i: this.productos) {
			if (id == i.getId_producto()) {
				return i.getCantidad();
			}
		}
		return 0;
	}
	
	public boolean VentaEsPosible (ArrayList<ProdVenta> productosVenta) {   // Mensaje para saber si hay stock para registrar dicha venta.
		ArrayList<ProdVenta> productos_stock = this.getProductos();
		if (productosVenta.isEmpty() || productos_stock.isEmpty()) {
			return false;
		}
		for (ProdVenta i: productosVenta) {
			for (ProdVenta j: productos_stock) {
				if (i.getId_producto() == j.getId_producto()) {
					if (i.getCantidad()>j.getCantidad()) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void calcularFaltas(Catalogo catalogo) throws FileNotFoundException, IOException {
		ArrayList<Producto> prodcatalogo = catalogo.getProductos();
		ArrayList<ProdVenta> prodstock = this.productos;
		for (Producto i: prodcatalogo) {
			if (! productoRegistradoEnStock(i.getId())) {
				ProdVenta prodventa = new ProdVenta(i.getId(),0);
				prodstock.add(prodventa);
				this.faltasStock.add(i.getId());
			}
			else {
				if ((this.getCantidadProducto(i.getId())<= i.getCant_min()) && (!EnFalta(i))) {
					this.faltasStock.add(i.getId());
				}
			}
		}
		this.almacenarObjeto("stock.txt");
	}
	
	public ArrayList<Integer> getFaltasStock() {
		if (this.faltasStock == null) {
			return null;
		}
		return faltasStock;
	}

	public void setFaltasStock(ArrayList<Integer> faltasStock) throws FileNotFoundException, IOException {
		this.faltasStock = faltasStock;
		this.almacenarObjeto("stock.txt");
	}

	public double getValorTotal(Catalogo catalogo) {
		double total = 0;
		ArrayList<ProdVenta> prodstock = this.getProductos();
		for (ProdVenta i : prodstock) {
			total = total + (catalogo.getValor(i.getId_producto())*i.getCantidad());
		}
		return total;
	}
	
	public Mensaje eliminarProducto(int id) throws FileNotFoundException, IOException {
		ArrayList<ProdVenta> prods = this.productos;
		for (ProdVenta i:prods) {
			if (i.getId_producto() == id) {
				this.productos.remove(i);
				this.almacenarObjeto("stock.txt");
				return Mensaje.listo;
			}
		}
		return Mensaje.noexiste;
	}
	
	public boolean faltaStock () {
		if ((this.faltasStock == null) || (this.faltasStock.isEmpty())) {
			return false;
		}
		else return true;
	}
	
	public boolean productoRegistradoEnStock(int id) {
		if (id<1) {
			return false;
		}
		for (ProdVenta i: this.productos) {
			if (id == i.getId_producto()) {
				return true;
			}
		}
		return false;
	} 
	
	public Mensaje agregarProductoFaltaStock(ProdVenta producto) {
		if (!EnFalta(producto)) {
			this.faltasStock.add(producto.getId_producto());
			return Mensaje.listo;
		}
		return Mensaje.error;
	}
	
	public ProdVenta getProdVenta(int id) {
		if (id<1) {
			return null;
		}
		for (ProdVenta i: productos) {
			if (i.getId_producto() == id) {
				return i;
			}
		}
		return null;
	}
}
