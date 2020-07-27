package modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface iAlmacenable {
	public default Mensaje almacenarObjeto(String nombreArchivo) throws FileNotFoundException, IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/archivos\\" + nombreArchivo))){
			oos.writeObject(this); 
			return Mensaje.listo;
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
			return Mensaje.error;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return Mensaje.error;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return Mensaje.error;
		}
	}
	
	public static Object cargarObjeto(String nombreArchivo) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/archivos\\"+ nombreArchivo))){
			return (Object) ois.readObject();
			
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());	
			return null;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
			
	}
}
