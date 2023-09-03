package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class ProductoAjustado implements Producto
{
	private ProductoMenu ProductoBase = null;
	private ArrayList<Ingrediente> IngredientesAgregados = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> IngredientesEliminados = new ArrayList<Ingrediente>();
	
//  Constructor
	
	public ProductoAjustado(ProductoMenu pProductoBase) 
	{
		this.ProductoBase = pProductoBase;
	}
	
// Obtener nombre del producto base
	
	public String getNombre() 
	{
		return ProductoBase.getNombre();
	}
	
//	Obtener el precio del producto base con costo adicional
	
	public int getPrecio() 
	{
		int AdicionarIngredientes = 0;
		for (Ingrediente ingrediente : IngredientesAgregados)
		{
			AdicionarIngredientes += ingrediente.getCostoAdicional();
		}
		return ProductoBase.getPrecio() + AdicionarIngredientes;
	}
	
//	Generar el texto factura
	
	public String generarTextoFactura()
	{
		String TextoFactura = "\n*" + getNombre() + "\t$" + ProductoBase.getPrecio() + "\n";
		for (Ingrediente ingrediente: IngredientesAgregados)
		{
			TextoFactura += "+" + ingrediente.getNombre() + "\t$" + ingrediente.getCostoAdicional() + "\n";
		}
		for (Ingrediente ingrediente: IngredientesEliminados)
		{
			TextoFactura += "-" + ingrediente.getNombre() + "\n";
		}
		return TextoFactura;
	}
	
//	Agregar Ingredientes
	
	public void agregarIngrediente(Ingrediente Ingrediente)
	{
		IngredientesAgregados.add(Ingrediente);
	}
	
//	Agregar Ingredientes eliminados
	
	public void eliminarIngrediente(Ingrediente Ingrediente)
	{
		IngredientesEliminados.add(Ingrediente);
	}
	
//	Obtener Ingredientes agregados
	
	public ArrayList<Ingrediente> getIngredientesAgregados()
	{
		return IngredientesAgregados;
	}
	
//	Obtener Ingredientes eliminados
	
	public ArrayList<Ingrediente> getIngredientesEliminados()
	{
		return IngredientesEliminados;
	}
	
}
