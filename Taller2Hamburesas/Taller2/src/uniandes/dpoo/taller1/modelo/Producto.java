package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

//Interface de un producto

public interface Producto 
{
	public int getPrecio();
	
	public String getNombre();
	
	public String generarTextoFactura();
	
	//Obtener los ingredientes agregados al producto
	public ArrayList<Ingrediente> getIngredientesAgregados();
	//Obtener los ingredientes eliminados al producto
	public ArrayList<Ingrediente> getIngredientesEliminados();
}
