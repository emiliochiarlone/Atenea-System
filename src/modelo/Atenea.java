package modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingUtilities;

import vista.VistaMenuPrincipal;

public class Atenea {


	private HistorialVentas historialVentas;
	private Catalogo catalogo;
	private Stock stock;
	private ListaReservas listaReservas;
	
	public Atenea() {
		this.stock = new Stock();
		this.catalogo = new Catalogo();
		this.historialVentas =  new HistorialVentas();
		this.listaReservas = new ListaReservas();
	}
	
	public ListaReservas getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(ListaReservas listaReservas) {
		this.listaReservas = listaReservas;
	}

	public void setStock(Stock stock) {  //BORRARR SOLO DE PRUEBA
		this.stock = stock;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}


	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}


	public HistorialVentas getHistorialVentas() {
		return historialVentas;
	}
	
	public void setHistorialVentas(HistorialVentas historialventas) {
		this.historialVentas = historialventas;
	}

	public Stock getStock() {
		return stock;
	}

	public String getNombreProducto (int id) {
		if (id<1) {
			return "";
		}
		ArrayList<Producto> productos = catalogo.getProductos();
		for (Producto i: productos) {
			if (id == i.getId()) {
				return i.getNombre();
			}
		}
		return "";
	}
	
	
	public Mensaje NuevaVenta(Venta venta) throws FileNotFoundException, IOException {   //Registro de nueva venta
		if (!this.stock.VentaEsPosible(venta.getProductos())) {
			return Mensaje.nostock;
		}
		ArrayList<ProdVenta> productos_stock = stock.getProductos();
		ArrayList<ProdVenta> productosVenta = venta.getProductos();
		for (ProdVenta i: productosVenta) {
			for (ProdVenta j: productos_stock) {
				if (i.getId_producto() == j.getId_producto()) {
					if (j.Descontar(i.getCantidad()) == Mensaje.nostock){
						return Mensaje.nostock;
					}
				}
			}
		}
		stock.setProductos(productos_stock);
		historialVentas.AgregarVenta(venta);
		stock.calcularFaltas(this.catalogo);
		historialVentas.almacenarObjeto("historialVentas.txt");
		stock.almacenarObjeto("stock.txt");
		return Mensaje.listo;
	}
	
	public Mensaje NuevaReserva(Reserva reserva) throws FileNotFoundException, IOException {
		if (!this.stock.VentaEsPosible(reserva.getProductos())) {
			return Mensaje.nostock;
		}
		ArrayList<ProdVenta> productos_stock = stock.getProductos();
		ArrayList<ProdVenta> productosReserva = reserva.getProductos();
		for (ProdVenta i: productosReserva) {
			for (ProdVenta j: productos_stock) {
				if (i.getId_producto() == j.getId_producto()) {
					if (j.Descontar(i.getCantidad()) == Mensaje.nostock){
						return Mensaje.nostock;
					}
				}
			}
		}
		stock.setProductos(productos_stock);
		listaReservas.AgregarReserva(reserva);
		stock.calcularFaltas(this.catalogo);
		listaReservas.almacenarObjeto("listaReservas.txt");
		stock.almacenarObjeto("stock.txt");
		return Mensaje.listo;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		Atenea atenea = new Atenea();
		HistorialVentas historialVentas = (HistorialVentas) iAlmacenable.cargarObjeto("historialVentas.txt");
		Stock stock = (Stock) iAlmacenable.cargarObjeto("stock.txt");
		Catalogo catalogo = (Catalogo) iAlmacenable.cargarObjeto("catalogo.txt");
		ListaReservas listaReservas = (ListaReservas) iAlmacenable.cargarObjeto("listaReservas.txt");
	
//		HistorialVentas historialVentas = new HistorialVentas();
//		Stock stock = new Stock();
//		Catalogo catalogo = new Catalogo();
//		ListaReservas listaReservas = new ListaReservas();
		
		atenea.setCatalogo(catalogo);
		atenea.setHistorialVentas(historialVentas);
		atenea.setStock(stock);
		atenea.setListaReservas(listaReservas);
		
		
//		Venta venta = new Venta();
//		Producto producto = new Producto();
//		Reserva reserva = new Reserva();
//		venta.setcliente("alo");
//		venta.setMonto(200);
//		venta.setFecha(new Date());
//		producto.setNombre("aleeu");
//		reserva.setCliente("alee");
//		reserva.setFecha(new Date());
//		reserva.setMonto(200);
//		for (int i = 1; i<=20000; i++) {
//			
//			atenea.getCatalogo().agregarProducto(producto);
//			atenea.getHistorialVentas().AgregarVenta(venta);
//			atenea.getListaReservas().AgregarReserva(reserva);
//		}
		
//		atenea.getCatalogo().almacenarObjeto("catalogo.txt");
//		atenea.getHistorialVentas().almacenarObjeto("historialVentas.txt");
//		atenea.getListaReservas().almacenarObjeto("listaReservas.txt");
//		atenea.getStock().almacenarObjeto("stock.txt");
		//
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	           try {
				new VistaMenuPrincipal(atenea);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Instancia de ventana
	        }
	     });
	}
		
}

