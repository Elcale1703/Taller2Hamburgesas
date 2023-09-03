package uniandes.dpoo.taller1.modelo;

import java.util.ArrayList;

public class Combo implements Producto

{
    private double descuento;

    private String nombreCombo;
    
    private ArrayList<Producto> productosCombo;
    
//  Constructor

    public Combo(String nombreCombo, double descuento)
    {
    	this.nombreCombo = nombreCombo;
    	this.descuento = descuento;
    	productosCombo = new ArrayList<Producto>();
    }
    
//	Agregar producto al combo
    
    public void agregarProductoACombo(Producto productoCombo)
    {
    	productosCombo.add(productoCombo);
    }

//	Obtener precio del combo

    public int getPrecio()
    {
    	int Precio = 0;
    	int AdicionarIngredientes = 0;
    	for (Producto producto: productosCombo)
    	{
    		Precio += producto.getPrecio();
    	}
    	int Descuento = (int) (Precio*descuento);
    	
        return Precio - Descuento + AdicionarIngredientes;
    }

//  Obtener nombre del combo
    
    public String getNombre()

    {
        return nombreCombo;
    }
    
//  Obtener el descuento del combo
    
    public double getDescuento() 
    {
		return descuento;
	}

//  Obtener los productos del combo
    
	public ArrayList<Producto> getItemsCombo() 
    {
		return productosCombo;
	}

//  Generar texto factura	

	public String generarTextoFactura()
    {
        String TextoFactura = "\n*" + nombreCombo + ": \t$" + getPrecio() + "\n";
		for (Producto producto: productosCombo)
        {
        	TextoFactura += producto.getNombre() + "\n";
        	for (Ingrediente ingrediente: producto.getIngredientesAgregados())
    		{
    			TextoFactura += "+" + ingrediente.getNombre() + "\t$" + ingrediente.getCostoAdicional() + "\n";
    		}
    		for (Ingrediente ingrediente: producto.getIngredientesAgregados())
    		{
    			TextoFactura += "-" + ingrediente.getNombre() + "\n";
    		}
        }
		return TextoFactura;
    }

	@Override
	public ArrayList<Ingrediente> getIngredientesAgregados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Ingrediente> getIngredientesEliminados() {
		// TODO Auto-generated method stub
		return null;
	}
}