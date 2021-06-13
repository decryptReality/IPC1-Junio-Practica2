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

    static void ingresarCliente()
    {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("ingrese el id del cliente");
        int id = scanner1.nextInt();
        System.out.println("ingrese el nombre del cliente");
        String nombre = scanner1.next();
        System.out.println("ingrese el telefono del cliente");
        int telefono = scanner1.nextInt();
        agregarCliente(id, nombre, telefono);
        System.out.println("[?] cliente agregado con exito");
    }

    static void agregarCliente(int id, String nombre, int telefono)
    {
        idClientes = agregarElementoInt(idClientes, id);
        nombreClientes = agregarElementoString(nombreClientes, nombre);
        telefonoClientes = agregarElementoInt(telefonoClientes, telefono);
        tienePeliculaClientes = agregarElementoBoolean(tienePeliculaClientes, false);
    }

    static void ingresarPelicula()
    {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("ingrese el id de la pelicula");
        int id = scanner1.nextInt();
        System.out.println("ingrese el nombre de la pelicula");
        String nombre = scanner1.next();
        System.out.println("ingrese el anio de la pelicula");
        int anio = scanner1.nextInt();
        System.out.println("ingrese la categoria de la pelicula");
        String categoria = scanner1.next();
        agregarPelicula(id, nombre, anio, categoria);
        System.out.println("[?] pelicula agregada con exito");
    }

    static void agregarPelicula(int id, String nombre, int anio, String categoria)
    {
        idPeliculas = agregarElementoInt(idPeliculas, id);
        nombrePeliculas = agregarElementoString(nombrePeliculas, nombre);
        anioPeliculas = agregarElementoInt(anioPeliculas, anio);
        categoriaPeliculas = agregarElementoString(categoriaPeliculas, categoria);
        disponiblePeliculas = agregarElementoBoolean(disponiblePeliculas, true);
    }

    static String[] agregarElementoString(String[] array1, String elemento1)
    {
        String[] array2 = new String[array1.length + 1];

        int index = 0;
        while(index < array1.length)
        {
            array2[index] = array1[index];
            index = index + 1;
        }

        array2[array1.length] = elemento1;
        return array2;
    }

    static int[] agregarElementoInt(int[] array1, int elemento1)
    {
        int[] array2 = new int[array1.length + 1];

        int index = 0;
        while(index < array1.length)
        {
            array2[index] = array1[index];
            index = index + 1;
        }

        array2[array1.length] = elemento1;
        return array2;
    }

    static boolean[] agregarElementoBoolean(boolean[] array1, boolean elemento1)
    {
        boolean[] array2 = new boolean[array1.length + 1];

        int index = 0;
        while(index < array1.length)
        {
            array2[index] = array1[index];
            index = index + 1;
        }

        array2[array1.length] = elemento1;
        return array2;
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
