package uniandes.dpoo.taller1.modelo;

public class Ingrediente 
{
	// ************************************************************************
	// Atributos
	// ************************************************************************
	
	private String nombre;
	
	private int costoAdicional;
	
	// ************************************************************************
	// Constructores
	// ************************************************************************
	/**
	 * Construye un nuevo ingrediente, inicializando los atributos con los valores de los
	 * parámetros.
	 * 
	 * @param nombre El nombre al que corresponde el ingrediente
	 * @param costoAdicional    El costo adicional de agregar el ingrediente
	 */
	
	public Ingrediente(String nombre, int costoAdicional)
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
	}
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public int getCostoAdicional() 
	{
		return costoAdicional;
	}
}
