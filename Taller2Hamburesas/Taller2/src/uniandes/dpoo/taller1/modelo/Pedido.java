package uniandes.dpoo.taller1.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido {

	private static int numeroPedidos;
	private int idPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> productosPedido;
	
//	Constructor
	
	public Pedido(String nombreCliente, String direccionCliente) 
	{
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		productosPedido = new ArrayList<Producto>();
		idPedido = numeroPedidos;
	}
	
//	Obtener el id del pedido

	public int getIdPedido() 
	{
		return idPedido;
	}
	
//	Agregar producto al pedido

	public void agregarProducto(Producto producto) 
	{
		productosPedido.add(producto);
	}
	
//	Obtener el precio neto del pedido
	
	private int getPrecioNetoPedido()
	{
		int PrecioNeto = 0;
		for (Producto producto: productosPedido)
		{
			PrecioNeto += producto.getPrecio();
		}
		return PrecioNeto;
	}

//	Obtener el precio con iva del pedido
	
	private int getPrecioIvaPedido()
	{
		return (int) (getPrecioNetoPedido()*0.19);
	}
	
//	Obtener el precio total del pedido
	
	private int getPrecioTotalPedido()
	{
		return getPrecioNetoPedido() + getPrecioIvaPedido();
	}
	
//	Dar id a pedido
	
	public static void setNumeroPedidos() 
	{
		Pedido.numeroPedidos ++;
	}

//	Obtener el productos del pedido
	
	public ArrayList<Producto> getItemsPedido()
	{
		return productosPedido;
	}
	
//	Genera el texto de la factura
	
	private String generarTextoFactura()
	{	
		String TextoFactura = "Id: " + getIdPedido() + "\n" + 
				   "Cliente: " + nombreCliente + "\n" +
				   "Direccion Cliente: " + direccionCliente + "\n" + 
				   "\nProductos:\n";
		
		for (Producto producto: productosPedido)
		{
			TextoFactura += producto.generarTextoFactura() + "\n";
		}
		
		TextoFactura += "\n\nPrecio Neto: $" + getPrecioNetoPedido() + "\n" +
						"IVA: $" + getPrecioIvaPedido() + "\n" +
						"Precio Total: $" + getPrecioTotalPedido();
		
		return TextoFactura;
		
	}
	
//	Guardar la factura en un archivo txt
	
	public void guardarFactura(File archivo) 
	{
		try 
		{
            FileWriter fw = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(generarTextoFactura());
            bw.close();
        } 
		catch (IOException e) 
		{
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}	
}
