package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicios.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {
	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando la aplicación...");
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicación finalizada.");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	public void zonaFitApp(){
		logger.info("** Aplicacion zona fit **");
		Boolean salir = false;
		Scanner teclado = new Scanner(System.in);
		while(!salir){
			try {
				var opciones = mostrarMenu(teclado);
				salir = ejecutarOpciones(teclado,opciones);
			}catch(Exception e){
				logger.error("Error en la ejecución: " + e.getMessage());
			}
		}
	}

	public int mostrarMenu(Scanner teclado){
		while (true){
			try{
				System.out.print("""
				****** Menu zona_fit ******
                1. Mostar lista de clientes
                2. Buscar cliente por ID
                3. Agregar nuevo cliente
                4. Eliminar cliente por ID
                5. Salir
                Seleccione una opcion:""");
				return Integer.parseInt(teclado.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Error, Debe ingresar un número válido.");
			}
		}
	}

	public boolean ejecutarOpciones(Scanner teclado,int opcion){
		Boolean salir = false;
		switch(opcion){
			case 1 ->{
				logger.info("****** Lista de clientes ******");
				var clientes = clienteServicio.listarClientes();
				clientes.forEach(System.out::println);
			}
			case 2 ->{
				Integer id = 0;
				Boolean validarEntrada = false;
				logger.info("****** Buscar cliente por id ******");
				do {
					try {
						logger.info("Ingrese el ID del cliente a consultar");
						id =  Integer.parseInt(teclado.nextLine());
						if(id > 0){
							validarEntrada = true;
						}else{
							logger.info("Error, ingrese un valor mayor que 0.");
						}
					}catch(NumberFormatException e){
						logger.info("Error, formato no valido " + e.getMessage());
					}
				}while(!validarEntrada);
				var clienteEncontrado = clienteServicio.buscarClientePorId(id);
				if (clienteEncontrado != null)
					logger.info("Cliente encontrado: " + clienteEncontrado);
				else
					logger.info("Cliente NO encontrado con ID: " + id);
			}
			case 3 ->{
				String nombre;
				String apellido;
				Integer membresia = 0;
				Boolean validarEntrada = false;
				logger.info("****** Agregar un nuevo cliente ******");

				do {
					logger.info("Ingrese el nombre del nuevo cliente");
					nombre = teclado.nextLine();
					if(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
						logger.info("Error: El nombre no puede estar vacío ni contener números o símbolos.");
					}
				}while(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));

				do {
					logger.info("Ingrese el apellido del nuevo cliente");
					apellido = teclado.nextLine();
					if(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
						logger.info("Error: El apellido no puede estar vacío ni contener números o símbolos.");
					}
				}while(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));

				do {
					try{
						logger.info("Ingrese el valor de la membresia del nuevo cliente");
						membresia = Integer.parseInt(teclado.nextLine());
						if(membresia > 0){
							validarEntrada = true;
						}else{
							logger.info("Error, ingrese un valor mayor que 0.");
						}
					}catch (NumberFormatException e){
						logger.info("Error, formato no valido " + e.getMessage());
					}
				}while(!validarEntrada);

				var clienteNuevo = new Cliente(null,nombre,apellido,membresia);
				clienteNuevo.setNombre(nombre);
				clienteNuevo.setApellido(apellido);
				clienteNuevo.setMembresia(membresia);
				clienteServicio.guardarCliente(clienteNuevo);
				logger.info("Cliento nuevo se creo correctamente... " + clienteNuevo);
			}
			case 4 ->{
				Integer id = 0;
				Boolean validarEntrada = false;
				logger.info("****** Eliminar cliente por id ******");
				do {
					try {
						logger.info("Ingese el ID del cliente que desea eliminar");
						id = Integer.parseInt(teclado.nextLine());
						if(id > 0){
							validarEntrada = true;
						}else {
							logger.info("Error, ingrese un valor mayor que 0.");
						}
					}catch(NumberFormatException e){
						logger.info("Error, formato no valido " + e.getMessage());
					}
				}while(!validarEntrada);
				var cliente = clienteServicio.buscarClientePorId(id);
				if(cliente != null){
					clienteServicio.eliminarCliente(cliente);
					logger.info("Se elimino el cliente correctamente...");
				}else {
					logger.info("No se puede eliminar: Cliente no existe");
				}
			}
			case 5 ->{
				logger.info("Hasta pronto....");
				salir = true;
			}
			default -> logger.info("Error, opcion no encontrada " + opcion);
		}
		return salir;
	}
}
