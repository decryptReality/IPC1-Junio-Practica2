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

    static void prestarPeliculas()
    {
        System.out.println("seleccione ingresando el id de la pelicula");
        System.out.println("orden: id, nombre");
        // mostrar peliculas disponibles
        int i = 0;
        while (i < idPeliculas.length)
        {
            if (disponiblePeliculas[i])
            {
                System.out.println(idPeliculas[i] + ", " + nombrePeliculas[i]);
            }
            i = i + 1;
        }

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("seleccione el id de una pelicula");
        int pelicula = scanner1.nextInt();

        i = 0;
        boolean seleccionado = false;
        while (i < idPeliculas.length && !seleccionado)
        {
            if (pelicula == idPeliculas[i])
            {
                seleccionado = true;
                System.out.println("[S/N] guardar en tabla de prestamo?");
                String respuesta2 = scanner1.next();
                if (respuesta2.equals("S"))
                {
                    System.out.println("ingrese id de cliente");
                    int cliente = scanner1.nextInt();
                    System.out.println("ingrese la cantidad de dias del prestamo");
                    int dias = scanner1.nextInt();

                    int j = 0;
                    boolean hallado = false;
                    while (j < idClientes.length && !hallado)
                    {
                        if (cliente == idClientes[j])
                        {
                            hallado = true;
                            disponiblePeliculas[i] = false;
                            tienePeliculaClientes[j] = true;
                            agregarPrestamo(cliente, pelicula, dias);
                            System.out.println("[?] prestamo exitoso");
                        }
                        j = j + 1;
                    }
                }
            }
            i = i + 1;
        }
        menu1();
    }

    static void agregarPrestamo(int cliente, int pelicula, int dias)
    {
        idClientePrestamo = agregarElementoInt(idClientePrestamo, cliente);
        idPeliculaPrestamo = agregarElementoInt(idPeliculaPrestamo, pelicula);
        cantidadDiasPrestamo = agregarElementoInt(cantidadDiasPrestamo, dias);
    }

    static void devolverPelicula()
    {
        System.out.println("seleccione ingresando el id de una pelicula");
        System.out.println("orden: idPelicula, nombrePelicula, nombreCliente");
        // mostrar peliculas prestadas
        int[] indicePeliculaPrestamo = new int[idPeliculaPrestamo.length];
        int[] indiceClientePrestamo = new int[idClientePrestamo.length];

        int i = 0;

        while (i < idPeliculaPrestamo.length)
        {
            boolean halladoJ = false;
            int j = 0;
            while (j < idPeliculas.length && !halladoJ)
            {
                if (idPeliculaPrestamo[i] == idPeliculas[j])
                {
                    halladoJ = true;

                    int k = 0;
                    boolean halladoK = false;
                    while (k < idClientes.length && !halladoK)
                    {
                        if (idClientePrestamo[i] == idClientes[k])
                        {
                            halladoK = true;

                            indicePeliculaPrestamo[i] = j;
                            indiceClientePrestamo[i] = k;

                            if (!disponiblePeliculas[j] && tienePeliculaClientes[k])
                            {
                                System.out.println(idPeliculas[j] + ", " + nombrePeliculas[j] + ", " + nombreClientes[k]);
                            }
                        }
                        k = k + 1;
                    }
                }
                j = j + 1;
            }
            i = i + 1;
        }

        // verificar si el id en respuesta pertenece a alguna opcion de los enlistados
        // si asi es, con el indice del prestamo verificar cada id de ese prestamo
        // para poder cambiear el estado en ese cliente y pelicula

        // indice de presatamo: para obtener id de cliente y pelicula
        // cada id se debe buscar en clientes y en peliculas
        // con indice de clientes y peliculas: cambiar estados en peliculas y clientes

        Scanner scanner1 = new Scanner(System.in);
        int respuestaIDPelicula = scanner1.nextInt();

        i = 0;
        boolean halladoI1 = false;
        while (i < idPeliculaPrestamo.length && !halladoI1)
        {
            if (idPeliculaPrestamo[i] == respuestaIDPelicula)
            {
                halladoI1 = true;
                disponiblePeliculas[indicePeliculaPrestamo[i]] = true;
                tienePeliculaClientes[indiceClientePrestamo[i]] = false;
                System.out.println("[?] devolucion exitosa");
            }
            i = i + 1;
        }
        menu1();
    }

    static void mostrarPeliculas()
    {
        int i = 0;
        while (i < idPeliculas.length)
        {
            System.out.println(idPeliculas[i] + ", " + nombrePeliculas[i] + ", " + anioPeliculas[i] + ", " + categoriaPeliculas[i]);
            i = i + 1;
        }
        menu1();
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
        menu1();
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
        menu1();
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

    static void mostrarClientes()
    {
        int i = 0;
        while (i < idClientes.length)
        {
            System.out.println(idClientes[i] + ", " + nombreClientes[i] + ", " + telefonoClientes[i]);
            i = i + 1;
        }
        menu1();
    }

    static void menu1()
    {
        System.out.println("MEMORABILIA");
        System.out.println("[1] prestamo de peliculas");
        System.out.println("[2] devolucion de peliculas");
        System.out.println("[3] ingresar una pelicula");
        System.out.println("[4] ingresar nuevo cliente");
        System.out.println("[5] listar todas las peliculas");
        System.out.println("[6] listar todos los clientes");
        System.out.println("[7]* ordenar peliculas");
        System.out.println("[8]* resportes");
        Scanner scanner1 = new Scanner(System.in);
        int seleccion = scanner1.nextInt();
        switch (seleccion)
        {
            case 1:
                prestarPeliculas();
                break;
            case 2:
                devolverPelicula();
                break;
            case 3:
                ingresarPelicula();
                break;
            case 4:
                ingresarCliente();
                break;
            case 5:
                mostrarPeliculas();
                break;
            case 6:
                mostrarClientes();
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
        }
    }

    public static void main(String[] args) {
        menu1();
    }
}
