package uniandes.dpoo.taller1.consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller1.modelo.ProductoMenu;
import uniandes.dpoo.taller1.modelo.Combo;
import uniandes.dpoo.taller1.modelo.Ingrediente;
import uniandes.dpoo.taller1.modelo.Pedido;
import uniandes.dpoo.taller1.modelo.Producto;
import uniandes.dpoo.taller1.modelo.ProductoAjustado;
import uniandes.dpoo.taller1.procesamiento.Restaurante;

public class Aplicacion 
{
	
	
	Restaurante Restaurante;
	/**
	 * Ejecuta la aplicaci�n: le muestra el men� al usuario y la pide que ingrese
	 * una opci�n, y ejecuta la opci�n seleccionada por el usuario. Este proceso se
	 * repite hasta que el usuario seleccione la opci�n de abandonar la aplicaci�n.
	 */
	
	public void ejecutarAplicacion()
	{
		Restaurante = new Restaurante();
		File archivoMenu = new File ("./data/menu.txt");
		File archivoIngredientes = new File ("./data/ingredientes.txt");;
		File archivoCombos = new File ("./data/combos.txt");
		Restaurante.cargarInformacionRestaurante(archivoMenu, archivoIngredientes, archivoCombos);
		
		System.out.println("      �BIENVENIDOS A BURGESILLAS!      ");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				System.out.println("\nOPCIONES\n");
				System.out.println("\t1. Mostrar menu");
				System.out.println("\t2. Crear un nuevo pedido");
				System.out.println("\t3. Anadir elemento a un pedido");
				System.out.println("\t4. Cerrar pedido y guardar factura");
				System.out.println("\t5. Consultar pedido segun id");
				System.out.println("\t6. Cerrar aplicaci�n");
				System.out.println();
				int opcion_seleccionada = Integer.parseInt(input("Seleccione una opcion"));
				if (opcion_seleccionada < 6 && opcion_seleccionada > 0) 
					ejecutarOpcion(opcion_seleccionada);
				
				else if (opcion_seleccionada == 6)
				{
					System.out.println("Saliendo de la aplicaci�n - Gracias por su visita");
					continuar = false;
				}
				else
				{
					System.out.println("\nSeleccione una opci�n v�lida.\n");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nSeleccione un n�mero de las opciones..\\n");
			}
		}
		
	}
	
	public void ejecutarOpcion(int opcionSeleccionada)
	{
		if (opcionSeleccionada == 1)
			ejecutarMostrarMenu();
		else if (opcionSeleccionada == 2)
			ejecutarNuevoPedido();
		else if (opcionSeleccionada == 3)
			ejecutarAgregarElementoPedido();
		else if (opcionSeleccionada == 4)
			ejecutarCerrarPedido();
		else if (opcionSeleccionada == 5)
			ejecutarConsultarPedido();
	}

	private void ejecutarMostrarMenu()
	
	{
		System.out.println("\n MENU DE HAMBURGUESILLAS \n");
		System.out.println("Productos ------------------------ \n");
		ArrayList<Producto> MenuBase = Restaurante.getMenuBase();
		int i = 1;
		
		for (Producto item: MenuBase)
		{
			System.out.println(i + ". " + item.getNombre() + "\t $" + item.getPrecio());
			i ++;
		}
		
		System.out.println("\n Adiciones ------------------------ \n");
		i = 1;
		ArrayList<Ingrediente> Ingredientes = Restaurante.getIngredientes();
		
		for (Ingrediente item: Ingredientes)
		{
			System.out.println(i + ". " + item.getNombre() + "\t $" + item.getCostoAdicional());
			i ++;
		}
		
		System.out.println("\n Combos ------------------------ \n");
		i = 1;
		ArrayList<Combo> Combos = Restaurante.getCombos();
		
		for (Combo item: Combos)
		{
			System.out.println(i + ". " + item.getNombre() + "\n" +
							   "\tIncluye: ");
			ArrayList<Producto> Combo = item.getItemsCombo();
			for (Producto cosa: Combo)
			{
				System.out.println("\t-" + cosa.getNombre());
				
			}
			System.out.println("\tTodo por: $" + item.getPrecio() + "(Descuento: $" + item.getDescuento() + ")");
			i ++;
		}
	}
	
	private void ejecutarNuevoPedido()
	
	{	
		String nombreCliente = input("Ingrese su nombre: ");
		String direccionCliente = input("Ingrese su direccion: ");
		int idPedido = Restaurante.iniciarPedido(nombreCliente, direccionCliente);
		System.out.println("\n" + " PEDIDO CREADO " + "\n");
		System.out.println("\n" + " EL ID DE SU PEDIDO ES: " + idPedido + "\n");
		
	}
		
	private void ejecutarAgregarElementoPedido() 
	{
		if (Restaurante.getPedidoEnCurso() != null)
		{
			try
			{	
				System.out.println("Seleccione una opci�n para ordenar: ");
				System.out.println("1. Producto del Menu");
				System.out.println("2. Combo");
				int opcion = Integer.parseInt(input("Por favor seleccione el numero de la opcion que desea: "));
				if (opcion == 1)
				{
					ejecutarAgregarProducto();
				}
				else if (opcion == 2)
				{
					ejecutarAgregarCombo();
				}
				else
				{
					System.out.println("Seleccione una opcion valida");
				}
			}
			catch (NumberFormatException e)
			{
				
				System.out.println("Debe seleccionar una opcion valida");
			}
		}
		else
			System.out.println("Debe iniciar un pedido primero");
		
	}
	
	
	private void ejecutarAgregarProducto()
	
	{
		ArrayList<Integer> Agregados = new ArrayList<Integer>();
		ArrayList<Integer> Eliminados = new ArrayList<Integer>();
		ArrayList<Producto> MenuBase = Restaurante.getMenuBase();		
		System.out.println("\n Productos ------------------------");
		int i = 1;
		for (Producto item: MenuBase)
		{
			System.out.println(i + ". " + item.getNombre() + "\t $" + item.getPrecio());
			i ++;
		}
		System.out.print("\n" + "Agregar producto numero: ");
		int opcion = Integer.parseInt(input(""));
		opcion = opcion-1;
		if (opcion < MenuBase.size())
		{
			ProductoMenu Producto = (ProductoMenu) MenuBase.get(opcion);
			System.out.println("1. Para a�adir o quitar un ingrediente");
			System.out.println("2. Para continuar con el pedido");
			int opcion2 = Integer.parseInt(input("Seleccione una opcion: "));
			if (opcion2 == 1) 
			{
				ejecutarManejoIngredientesP(Producto);
			}			
			else if(opcion2 == 2)
			{
				Restaurante.getPedidoEnCurso().agregarProducto(Producto);
			}
		}
		System.out.print("\nPRODUCTO AGREGADO\n");
	}

	private void ejecutarAgregarCombo()
	
	{
		ArrayList<Combo> Combos = Restaurante.getCombos();		
		System.out.println("\n Combos ------------------------");
		int i = 1;
		for (Combo item: Combos)
		{
			int descuento = (int) (item.getPrecio()*item.getDescuento());
			System.out.println(i + ". " + item.getNombre() + "\t$" + item.getPrecio() + " (Ahorras: $" + descuento + ")");
			i ++;
		}
		System.out.print("\n" + "Agregar combo n�mero: ");
		int opcion = Integer.parseInt(input(""));
		opcion = opcion-1;
		if (opcion < Combos.size())
		{
			Combo Combo = Combos.get(opcion);
			System.out.println("1. Para a�adir o quitar un ingrediente");
			System.out.println("2. Para continuar con el pedido");
			int opcion2 = Integer.parseInt(input("Seleccione una opci�n"));
			if (opcion2 == 1) 
			{
				ejecutarManejoIngredientesC(Combo);
				Restaurante.getPedidoEnCurso().agregarProducto(Combo);
			}			
			else if(opcion2 == 2)
			{
				Restaurante.getPedidoEnCurso().agregarProducto(Combo);
			}
		}
		System.out.print("\nCOMBO AGREGADO\n");
	}
	
	private void ejecutarManejoIngredientesP(ProductoMenu Producto) 
	{
		System.out.println("1. Anadir ingrediente");
		System.out.println("2. Quitar ingrediente");
		System.out.println("3. Para continuar con el pedido.");
		int opcion = Integer.parseInt(input("Seleccione una opcion"));
		if(opcion == 1)
		{
			System.out.println("\n Ingredientes ------------------------ \n");
			int i = 1;
			ArrayList<Ingrediente> Ingredientes = Restaurante.getIngredientes();
			for (Ingrediente item: Ingredientes)
			{
				System.out.println(i + ". " + item.getNombre() + "\t $" + item.getCostoAdicional());
				i ++;
			}
			int numIngrediente = Integer.parseInt(input("Seleccione una opci�n"));
			Producto.agregarIngrediente(Ingredientes.get(numIngrediente-1));//.add(numIngrediente - 1);
			ejecutarManejoIngredientesP(Producto);
		}
		else if(opcion == 2)
		{	
			System.out.println("\n Ingredientes ------------------------ \n");
			int i = 1;
			ArrayList<Ingrediente> Ingredientes = Restaurante.getIngredientes();
			for (Ingrediente item: Ingredientes)
			{
				System.out.println(i + ". " + item.getNombre() + "\t $" + item.getCostoAdicional());
				i ++;
			}
			int numIngrediente = Integer.parseInt(input("Seleccione una opcion"));
			Producto.eliminarIngrediente(Ingredientes.get(numIngrediente-1));
			ejecutarManejoIngredientesP(Producto);
		}
		else if(opcion == 3)
		{
			ProductoAjustado ProductoAjustado = new ProductoAjustado(Producto);
			for (int Ingr=0; Ingr<= Producto.getIngredientesAgregados().size(); Ingr++)
			{
				Ingrediente Ingrediente = Restaurante.getIngredientes().get(Ingr);
				ProductoAjustado.agregarIngrediente(Ingrediente);
			}
			for (int Ingr=0; Ingr<= Producto.getIngredientesEliminados().size(); Ingr++)
			{
				Ingrediente Ingrediente = Restaurante.getIngredientes().get(Ingr);
				ProductoAjustado.eliminarIngrediente(Ingrediente);
			}
			Restaurante.getPedidoEnCurso().agregarProducto(ProductoAjustado);
		}
		else
		{
			System.out.println("\nSeleccione una opcion valida.\n");
			ejecutarManejoIngredientesP(Producto);
		}
			
	}

	private void ejecutarManejoIngredientesCP(ProductoMenu Producto) 
	{
		System.out.println("1. A�adir ingrediente");
		System.out.println("2. Quitar ingrediente");
		System.out.println("3. Para continuar con el pedido");
		int opcion = Integer.parseInt(input("Seleccione una opci�n"));
		if (opcion == 1) 
		{
			System.out.println("\n Ingredientes------------------------ \n");
			int i = 1;
			ArrayList<Ingrediente> Ingredientes = Restaurante.getIngredientes();
			for (Ingrediente item : Ingredientes) 
			{
				System.out.println(i + ". " + item.getNombre() + "\t $" + item.getCostoAdicional());
				i++;
			}
			int numIngrediente = Integer.parseInt(input("Seleccione una opci�n"));
			Producto.agregarIngrediente(Ingredientes.get(numIngrediente-1));
			Ingrediente Ingrediente = Restaurante.getIngredientes().get(numIngrediente - 1);
			Producto.agregarIngrediente(Ingrediente);
			ejecutarManejoIngredientesCP(Producto);
		} 
		else if (opcion == 2) 
		{
			System.out.println("\n Ingredientes ------------------------ \n");
			int i = 1;
			ArrayList<Ingrediente> Ingredientes = Restaurante.getIngredientes();
			for (Ingrediente item : Ingredientes) {
				System.out.println(i + ". " + item.getNombre() + "\t $" + item.getCostoAdicional());
				i++;
			}
			int numIngrediente = Integer.parseInt(input("Seleccione una opci�n"));
			Producto.eliminarIngrediente(Ingredientes.get(numIngrediente-1));
			Ingrediente Ingrediente = Restaurante.getIngredientes().get(numIngrediente - 1);
			Producto.eliminarIngrediente(Ingrediente);
			ejecutarManejoIngredientesCP(Producto);
		} 
		else if (opcion == 3) 
		{
			;
		} 
		else
		{
			System.out.println("\nSeleccione una opcion valida.\n");
			ejecutarManejoIngredientesCP(Producto);
		}

	}
	
	private void ejecutarManejoIngredientesC(Combo Combo)
	{
		ArrayList<Producto> itemsCombo = Combo.getItemsCombo();
		for (Producto item: itemsCombo)
		{
			System.out.println("\nAgregar o quitar un ingrediente a " + item.getNombre() + ":\n");
			ejecutarManejoIngredientesCP((ProductoMenu) item);
		}
	}

	
	private void ejecutarCerrarPedido()
	
	{
		System.out.println("Id PEDIDO: " + Restaurante.getPedidoEnCurso().getIdPedido());
		
//		System.out.println("Ingredientes extra: "+ '\n');
//		for(int i =0; i<= Restaurante.getPedidoEnCurso().getItemsPedido().size();i++)
//			{
//				for(int j=0; j<= Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesAgregados().size() && Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesAgregados().size()!=0; j++)
//				{
//					System.out.println(j-1+") "+Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesAgregados().get(i).getNombre()+ '\n');
//				}
//				System.out.println("No hay ingredientes extras"+ '\n');
//			}
//		System.out.println("Ingredientes eliminados: "+ '\n');
//		for(int i =0; i<= Restaurante.getPedidoEnCurso().getItemsPedido().size();i++)
//		{
//			for(int j=0; j<= Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesEliminados().size() && Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesEliminados().size()!=0; j++)
//			{
//				System.out.println(j-1+") "+Restaurante.getPedidoEnCurso().getItemsPedido().get(i).getIngredientesAgregados().get(i).getNombre()+ '\n');
//			}
//			System.out.println("No hay ingredientes eliminados"+ '\n');
//		}
		System.out.println("\nPEDIDO CERRADO\n");
		Restaurante.cerrarYGuardarPedido();
		
		
	}
	
	private void ejecutarConsultarPedido()
	
	{
		
		int idPedido = Integer.parseInt(input("Ingrese el Id del pedido: "));
		Pedido PedidoBuscado = Restaurante.getPedidos().get(idPedido);
		if (PedidoBuscado != null) 
		{
			ejecutarInformacionPedido(PedidoBuscado);
		}
		else
			System.out.println("ID no encontrado");
	}
	
	
	private void ejecutarInformacionPedido(Pedido PedidoBuscado)
	
	{
		System.out.println("\nPEDIDO #" + PedidoBuscado.getIdPedido() + "\n");
		int i = 1;
		for(Producto item: PedidoBuscado.getItemsPedido())
		{
			System.out.println(i + ") "+ item.getNombre() + "\t$" + item.getPrecio());
			i++;
		}
	}
	/**
	 * M�todo para imprimir un mensaje en consola pidiendo
	 * informaci�n al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje Mensaje que se le mostrar� al usuario
	 * @return Cadena de caracteres que el usuario escriba como respuesta.
	 */
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + " ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		Aplicacion consola = new Aplicacion();
		consola.ejecutarAplicacion();
	}
}
