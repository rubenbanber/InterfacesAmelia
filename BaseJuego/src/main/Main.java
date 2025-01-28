/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import DAO.AtributosDAO;
import DAO.InventarioDAO;
import DAO.PersonajeDAO;
import DAO.VideojuegoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import modelo.Atributos;
import modelo.ExcepcionesJuego;
import modelo.Inventarios;
import modelo.Personajes;
import modelo.Videojuegos;

/**
 *
 * @author ruben.banber
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n----- MENÚ PRINCIPAL -----");
            System.out.println("1. Gestionar Videojuegos");
            System.out.println("2. Gestionar Personajes");
            System.out.println("3. Gestionar Atributos");
            System.out.println("4. Gestionar Inventarios");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    gestionarVideojuegos(scanner);
                    break;
                case 2:
                    gestionarPersonajes(scanner);
                    break;
                case 3:
                    gestionarAtributos(scanner);
                    break;
                case 4:
                    gestionarInventarios(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }

    private static void gestionarVideojuegos(Scanner sc) throws SQLException {
        VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE VIDEOJUEGOS ---");
            System.out.println("1. Listar Videojuegos");
            System.out.println("2. Agregar Videojuego");
            System.out.println("3. Actualizar Videojuego");
            System.out.println("4. Eliminar Videojuego");
            System.out.println("5. Regresar");
            System.out.print("Selecciona una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    try {
                        List<Videojuegos> videojuegos = videojuegoDAO.listarTodosLosJuegos();
                        System.out.println("\n--- LISTA DE VIDEOJUEGOS ---");
                        for (Videojuegos v : videojuegos) {
                            System.out.println(v);
                        }
                    } catch (ExcepcionesJuego ex) {
                        System.err.println(ex);
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Desarrollador: ");
                        String desarrollador = sc.nextLine();
                        System.out.print("Fecha de lanzamiento (yyyy-MM-dd): ");
                        String fecha = sc.nextLine();
                        System.out.print("Género: ");
                        String genero = sc.nextLine();
                        System.out.print("Plataforma: ");
                        String plataforma = sc.nextLine();

                        Videojuegos nuevoVideojuego = new Videojuegos(-1, nombre, desarrollador, java.sql.Date.valueOf(fecha), genero, plataforma);
                        videojuegoDAO.insertarVideojuego(nuevoVideojuego);
                        System.out.println("Videojuego agregado con éxito.");
                    } catch (ExcepcionesJuego ex) {
                        ex.printStackTrace();
                        //System.out.println(ex);
                    }
                    break;
                case 3:
                    try {
                        System.out.print("ID del videojuego a actualizar: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo desarrollador: ");
                        String desarrollador = sc.nextLine();
                        System.out.print("Nueva fecha de lanzamiento (yyyy-MM-dd): ");
                        String fecha = sc.nextLine();
                        System.out.print("Nuevo género: ");
                        String genero = sc.nextLine();
                        System.out.print("Nueva plataforma: ");
                        String plataforma = sc.nextLine();

                        Videojuegos videojuegoActualizado = new Videojuegos(id, nombre, desarrollador, java.sql.Date.valueOf(fecha), genero, plataforma);
                        videojuegoDAO.updateVideojuego(videojuegoActualizado);
                        System.out.println("Videojuego actualizado con éxito.");
                    } catch (ExcepcionesJuego ex) {
                        System.out.println(ex);
                    }
                    break;
                case 4:
                    try {
                        System.out.print("ID del videojuego a eliminar: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        videojuegoDAO.deleteVideojuego(id);
                        System.out.println("Videojuego eliminado con éxito.");
                    } catch (ExcepcionesJuego ex) {
                        System.out.println(ex);
                    }
                    break;
                case 5:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void gestionarPersonajes(Scanner sc) {

        PersonajeDAO personajeDAO = new PersonajeDAO();
        boolean regresar = false;

        while (!regresar) {
            System.out.println("\n--- GESTIÓN DE PERSONAJES ---");
            System.out.println("1. Listar Personajes");
            System.out.println("2. Agregar Personaje");
            System.out.println("3. Actualizar Personaje");
            System.out.println("4. Eliminar Personaje");
            System.out.println("5. Regresar");
            System.out.print("Selecciona una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    try {
                        List<Personajes> personajes = personajeDAO.listarTodosLosPersonajes();
                        System.out.println("\n--- LISTA DE PERSONAJES ---");
                        for (Personajes p : personajes) {
                            System.out.println(p);
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar personajes: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Descripcion: ");
                        String descripcion = sc.nextLine();
                        System.out.print("ID del Videojuego al que pertenece: ");
                        int idVideojuego = sc.nextInt();

                        sc.nextLine();

                        Personajes nuevoPersonaje = new Personajes(-1, nombre, descripcion, idVideojuego);
                        personajeDAO.insertarPersonajes(nuevoPersonaje);
                        System.out.println("Personaje agregado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al agregar personaje: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("ID del personaje a actualizar: ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nueva descripcion: ");
                        String descripcion = sc.nextLine();
                        System.out.print("Nuevo ID del Videojuego: ");
                        int idVideojuego = sc.nextInt();

                        sc.nextLine();

                        Personajes personajeActualizado = new Personajes(id, nombre, descripcion, idVideojuego);
                        personajeDAO.updatePersonajes(personajeActualizado);
                        System.out.println("Personaje actualizado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al actualizar personaje: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("ID del personaje a eliminar: ");
                        int id = sc.nextInt();
                        sc.nextLine(); // Consumir salto de línea

                        personajeDAO.deletePersonajes(id);
                        System.out.println("Personaje eliminado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al eliminar personaje: " + e.getMessage());
                    }
                    break;
                case 5:
                    regresar = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
    }

    private static void gestionarAtributos(Scanner sc) {
        AtributosDAO atributosDAO = new AtributosDAO();
        int opcion;

        do {
            System.out.println("\n--- GESTIÓN DE ATRIBUTOS ---");
            System.out.println("1. Listar Atributos");
            System.out.println("2. Agregar Atributo");
            System.out.println("3. Actualizar Atributo");
            System.out.println("4. Eliminar Atributo");
            System.out.println("5. Regresar");
            System.out.print("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1: // Listar atributos
                    try {
                        System.out.println("\n--- LISTA DE ATRIBUTOS ---");
                        for (Atributos atributo : atributosDAO.listarTodosLosAtributos()) {
                            System.out.println("ID: " + atributo.getId_atributo()
                                    + ", Nombre: " + atributo.getNombre()
                                    + ", Valor: " + atributo.getValor()
                                    + ", ID del Personaje: " + atributo.getId_personaje());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar los atributos: " + e.getMessage());
                    }
                    break;

                case 2: // Agregar atributo
                    try {
                        System.out.println("\n--- AGREGAR ATRIBUTO ---");
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Valor: ");
                        int valor = sc.nextInt();
                        System.out.print("ID del Personaje: ");
                        int id_personaje = sc.nextInt();

                        Atributos nuevoAtributo = new Atributos(-1, nombre, valor, id_personaje);
                        atributosDAO.insertarInventario(nuevoAtributo);
                        System.out.println("Atributo agregado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al agregar el atributo: " + e.getMessage());
                    }
                    break;

                case 3: // Actualizar atributo
                    try {
                        System.out.print("ID del atributo a actualizar: ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo valor: ");
                        int valor = sc.nextInt();
                        System.out.print("Nuevo ID del Personaje: ");
                        int idPersonaje = sc.nextInt();

                        sc.nextLine();

                        Atributos atributoActualizado = new Atributos(id, nombre, valor, idPersonaje);
                        atributosDAO.updateAtributos(atributoActualizado);
                        System.out.println("Personaje actualizado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al actualizar personaje: " + e.getMessage());
                    }
                    break;

                case 4: // Eliminar atributo
                    try {
                        System.out.println("\n--- ELIMINAR ATRIBUTO ---");
                        System.out.print("ID del Atributo a eliminar: ");
                        int idAtributoEliminar = sc.nextInt();

                        atributosDAO.deleteAtributos(idAtributoEliminar);
                        System.out.println("Atributo eliminado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al eliminar el atributo: " + e.getMessage());
                    }
                    break;

                case 5: // Regresar
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 5.");
                    break;
            }
        } while (opcion != 5);
    }

    private static void gestionarInventarios(Scanner sc) {

        Scanner scanner = new Scanner(System.in);
        InventarioDAO inventariosDAO = new InventarioDAO();

        int opcion = 0;

        do {
            System.out.println("\n--- GESTIÓN DE INVENTARIOS ---");
            System.out.println("1. Listar Inventarios");
            System.out.println("2. Agregar Inventario");
            System.out.println("3. Actualizar Inventario");
            System.out.println("4. Eliminar Inventario");
            System.out.println("5. Regresar");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("\n--- LISTA DE ATRIBUTOS ---");
                        for (Inventarios inventario : inventariosDAO.listarTodosLosInventarios()) {
                            System.out.println("ID Inventario: " + inventario.getId_invetario()
                                    + ", Nombre: " + inventario.getNombre()
                                    + ", Descripcion: " + inventario.getDescripcion()
                                    + ", Cantidad: " + inventario.getCantidad()
                                    + ", ID Personaje: " + inventario.getId_personaje());
                        }
                    } catch (Exception e) {
                        System.err.println("Error al listar los inventarios: " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        // Agregar inventario
                        System.out.println("\n--- AGREGAR INVENTARIO ---");
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Descripción: ");
                        String descripcion = scanner.nextLine();
                        System.out.print("Cantidad: ");
                        int cantidad = scanner.nextInt();
                        System.out.print("ID del Personaje: ");
                        int id_personaje = scanner.nextInt();

                        Inventarios nuevoInventario = new Inventarios(-1, nombre, descripcion, cantidad, id_personaje);
                        inventariosDAO.insertarInventario(nuevoInventario);
                        System.out.println("Inventario agregado con éxito.");

                    } catch (Exception e) {
                        System.err.println("Error al agregar el inventario: " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        // Actualizar inventario
                        System.out.println("\n--- ACTUALIZAR INVENTARIO ---");
                        System.out.print("ID del Inventario a actualizar: ");
                        int idActualizar = scanner.nextInt();
                        scanner.nextLine(); // Consumir salto de línea

                        System.out.print("Nuevo Nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nueva Descripción: ");
                        String nuevaDescripcion = scanner.nextLine();
                        System.out.print("Nueva Cantidad: ");
                        int nuevaCantidad = scanner.nextInt();
                        System.out.print("Nuevo ID del Personaje: ");
                        int nuevoIdPersonaje = scanner.nextInt();

                        Inventarios inventarioActualizado = new Inventarios(idActualizar, nuevoNombre, nuevaDescripcion, nuevaCantidad, nuevoIdPersonaje);
                        inventariosDAO.updateInventario(inventarioActualizado);
                        System.out.println("Inventario actualizado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al modificar el inventario");
                    }
                    break;

                case 4:
                    try {
                        // Eliminar inventario
                        System.out.println("\n--- ELIMINAR INVENTARIO ---");
                        System.out.print("ID del Inventario a eliminar: ");
                        int idEliminar = scanner.nextInt();

                        inventariosDAO.deleteInventario(idEliminar);
                        System.out.println("Inventario eliminado con éxito.");
                    } catch (Exception e) {
                        System.err.println("Error al agregar el inventario: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Regresar
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 5);
    }

}
