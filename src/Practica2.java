import java.util.Scanner;

public class Practica2
{
    static int[] idPeliculas = new int[0];
    static boolean[] disponiblePeliculas = new boolean[0];
    static String[] nombrePeliculas = new String[0];
    static int[] anioPeliculas = new int[0];
    static String[] categoriaPeliculas = new String[0];

    static int[] idClientes = new int[0];
    static boolean[] tienePeliculaClientes = new boolean[0];
    static String[] nombreClientes = new String[0];
    static int[] telefonoClientes = new int[0];

    static int[] idClientePrestamo = new int[0];
    static int[] idPeliculaPrestamo = new int[0];
    static int[] cantidadDiasPrestamo = new int[0];

    static void mostrarPeliculas()
    {
        int i = 0;
        while (i < idPeliculas.length)
        {
            System.out.println(idPeliculas[i] + nombrePeliculas[i] + anioPeliculas[i] + categoriaPeliculas[i]);
        }
    }

    static void menu1()
    {
        System.out.println("MEMORABILIA");
        System.out.println("[1] prestamo de peliculas");
        System.out.println("[2] devolucion de peliculas");
        System.out.println("[3] listar todas las peliculas");
        System.out.println("[4] ingresar una pelicula");
        System.out.println("[5] ordenar peliculas");
        System.out.println("[6] ingresar nuevo cliente");
        System.out.println("[7] listar todos los clientes");
        System.out.println("[8] resportes");
    }
}
