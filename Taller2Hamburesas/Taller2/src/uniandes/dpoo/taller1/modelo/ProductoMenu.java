package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class ProductoMenu implements Producto
{
	private String nombre;
	
	private int precioBase;
	
	private ArrayList<Ingrediente> IngredientesAgregados = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> IngredientesEliminados = new ArrayList<Ingrediente>();
	
	public ProductoMenu(String nombre, int precioBase) 
	{
		this.nombre = nombre;
		this.precioBase = precioBase;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public int getPrecio()
	{
		int AdicionarIngredientes = 0;
		for (Ingrediente ingrediente : IngredientesAgregados)
		{
			AdicionarIngredientes += ingrediente.getCostoAdicional();
		}
		return precioBase + AdicionarIngredientes;
	}
	
	public ArrayList<Ingrediente> getIngredientesAgregados()
	{
		return IngredientesAgregados;
	}
	
	public ArrayList<Ingrediente> getIngredientesEliminados()
	{
		return IngredientesEliminados;
	}

	public String generarTextoFactura()
	{
		return "\n*" + nombre + " \t$" + precioBase;
	}

	public void agregarIngrediente(Ingrediente Ingrediente) 
	{
		IngredientesAgregados.add(Ingrediente);
	}
	
	public void eliminarIngrediente(Ingrediente Ingrediente) 
	{
		IngredientesEliminados.add(Ingrediente);
	}
}
  