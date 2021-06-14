import java.util.Random;
import java.util.Scanner;

public class Practica2
{
    static int[] id_Peliculas = new int[0];
    static boolean[] disponibilidad_Peliculas = new boolean[0];
    static String[] nombre_Peliculas = new String[0];
    static int[] anio_Peliculas = new int[0];
    static String[] categoria_Peliculas = new String[0];
    static int[] incidencia_prestamo_Peliculas = new int[0];

    static int[] id_Clientes = new int[0];
    static boolean[] prestamo_Clientes = new boolean[0];
    static String[] nombre_Clientes = new String[0];
    static int[] telefono_Clientes = new int[0];

    static int[] id_cliente_Prestamos = new int[0];
    static int[] id_pelicula_Prestamos = new int[0];
    static int[] dias_Prestamos = new int[0];
    static boolean[] devolucion_Prestamos = new boolean[0];

    static String[] nombre_Categorias = new String[0];
    static int[] incidencia_Categorias = new int[0];

    static void prestarPeliculas()
    {
        System.out.println("seleccione ingresando el id de la pelicula");
        System.out.println("orden: id, nombre");
        // mostrar peliculas disponibles
        int i = 0;
        while (i < id_Peliculas.length)
        {
            if (disponibilidad_Peliculas[i])
            {
                System.out.println(id_Peliculas[i] + ", " + nombre_Peliculas[i]);
            }
            i = i + 1;
        }

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("seleccione el id de una pelicula");
        int pelicula = scanner1.nextInt();

        i = 0;
        boolean seleccionado = false;
        while (i < id_Peliculas.length && !seleccionado)
        {
            if (pelicula == id_Peliculas[i])
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
                    while (j < id_Clientes.length && !hallado)
                    {
                        if (cliente == id_Clientes[j])
                        {
                            hallado = true;
                            disponibilidad_Peliculas[i] = false;
                            prestamo_Clientes[j] = true;
                            agregarPrestamo(cliente, pelicula, dias);
                            incidencia_prestamo_Peliculas[i] = incidencia_prestamo_Peliculas[i] + 1;
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
        id_cliente_Prestamos = agregarElementoInt(id_cliente_Prestamos, cliente);
        id_pelicula_Prestamos = agregarElementoInt(id_pelicula_Prestamos, pelicula);
        dias_Prestamos = agregarElementoInt(dias_Prestamos, dias);
        devolucion_Prestamos = agregarElementoBoolean(devolucion_Prestamos, false);
    }

    static void devolverPelicula()
    {
        System.out.println("seleccione ingresando el id de una pelicula");
        System.out.println("orden: idPelicula, nombrePelicula, nombreCliente");
        // mostrar peliculas prestadas
        // indice de presatamo: para obtener id de cliente y pelicula
        // cada id se debe buscar en clientes y en peliculas
        // con indice de clientes y peliculas: cambiar estados en peliculas y clientes
        int[] indice_peliculas_Prestamos = new int[id_pelicula_Prestamos.length];
        int[] indice_clientes_Prestamo = new int[id_cliente_Prestamos.length];

        int i = 0;

        while (i < id_pelicula_Prestamos.length)
        {
            boolean halladoJ = false;
            int j = 0;
            while (j < id_Peliculas.length && !halladoJ)
            {
                if (id_pelicula_Prestamos[i] == id_Peliculas[j])
                {
                    halladoJ = true;

                    int k = 0;
                    boolean halladoK = false;
                    while (k < id_Clientes.length && !halladoK)
                    {
                        if (id_cliente_Prestamos[i] == id_Clientes[k])
                        {
                            halladoK = true;

                            indice_peliculas_Prestamos[i] = j;
                            indice_clientes_Prestamo[i] = k;

                            if (!disponibilidad_Peliculas[j] && prestamo_Clientes[k] && !devolucion_Prestamos[i])
                            {
                                System.out.println(id_Peliculas[j] + ", " + nombre_Peliculas[j] + ", " + nombre_Clientes[k]);
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

        Scanner scanner1 = new Scanner(System.in);
        int respuestaIDPelicula = scanner1.nextInt();

        i = 0;
        boolean halladoI1 = false;
        while (i < id_pelicula_Prestamos.length && !halladoI1)
        {
            if (id_pelicula_Prestamos[i] == respuestaIDPelicula)
            {
                halladoI1 = true;
                devolucion_Prestamos[i] = true;
                disponibilidad_Peliculas[indice_peliculas_Prestamos[i]] = true;
                prestamo_Clientes[indice_clientes_Prestamo[i]] = false;
                System.out.println("[?] devolucion exitosa");
            }
            i = i + 1;
        }
        menu1();
    }

    static void mostrarPeliculas()
    {
        int i = 0;
        while (i < id_Peliculas.length)
        {
            System.out.println(id_Peliculas[i] + ", " + nombre_Peliculas[i] + ", " + anio_Peliculas[i] + ", " + categoria_Peliculas[i]);
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
        id_Clientes = agregarElementoInt(id_Clientes, id);
        nombre_Clientes = agregarElementoString(nombre_Clientes, nombre);
        telefono_Clientes = agregarElementoInt(telefono_Clientes, telefono);
        prestamo_Clientes = agregarElementoBoolean(prestamo_Clientes, false);
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
        String categoria1 = scanner1.next();
        String categoria2 = categoria1.toLowerCase();
        agregarPelicula(id, nombre, anio, categoria2);
        System.out.println("[?] pelicula agregada con exito");

        // si despues de terminado el ciclo no se encuentra categoria
        // crear nueva categoria
        int i = 0;
        while (i < nombre_Categorias.length)
        {
            if (categoria2.equals(nombre_Categorias[i]))
            {
                incidencia_Categorias[i] = incidencia_Categorias[i] + 1;
                i = nombre_Categorias.length + 1;
            }
            else
            {
                i = i + 1;
            }
        }
        if (i != nombre_Categorias.length + 1)
        {
            nombre_Categorias = agregarElementoString(nombre_Categorias, categoria2);
            incidencia_Categorias = agregarElementoInt(incidencia_Categorias, 1);
        }

        menu1();
    }

    static void ingresarPeliculaAUTO(int id, String nombre, int anio, String categoria)
    {
        String categoria2 = categoria.toLowerCase();
        agregarPelicula(id, nombre, anio, categoria2);
        System.out.println("[?] pelicula agregada con exito");

        // si despues de terminado el ciclo no se encuentra categoria
        // crear nueva categoria

        int i = 0;
        while (i < nombre_Categorias.length)
        {
            if (categoria2.equals(nombre_Categorias[i]))
            {
                incidencia_Categorias[i] = incidencia_Categorias[i] + 1;
                i = nombre_Categorias.length + 1;
            }
            else
            {
                i = i + 1;
            }
        }
        if (i != nombre_Categorias.length + 1)
        {
            nombre_Categorias = agregarElementoString(nombre_Categorias, categoria2);
            incidencia_Categorias = agregarElementoInt(incidencia_Categorias, 1);
        }
        // menu(); se elimino porque se ejecuta por cada pelicula ingresada
    }

    static void agregarPelicula(int id, String nombre, int anio, String categoria)
    {
        id_Peliculas = agregarElementoInt(id_Peliculas, id);
        nombre_Peliculas = agregarElementoString(nombre_Peliculas, nombre);
        anio_Peliculas = agregarElementoInt(anio_Peliculas, anio);
        categoria_Peliculas = agregarElementoString(categoria_Peliculas, categoria);
        disponibilidad_Peliculas = agregarElementoBoolean(disponibilidad_Peliculas, true);
        incidencia_prestamo_Peliculas = agregarElementoInt(incidencia_prestamo_Peliculas, 0);
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
        while (i < id_Clientes.length)
        {
            System.out.println(id_Clientes[i] + ", " + nombre_Clientes[i] + ", " + telefono_Clientes[i]);
            i = i + 1;
        }
        menu1();
    }

    static void reportes()
    {
        System.out.println("[+] cantidad de peliculas por categoria");
        System.out.println("    orden: categoria, incidencia");
        int i = 0;
        while (i < nombre_Categorias.length)
        {
            System.out.println("    " + nombre_Categorias[i] + ", " + incidencia_Categorias[i]);
            i = i + 1;
        }

        System.out.println("[+] peliculas por categoria");
        System.out.println("    orden: categoria: pelicula1, pelicula2, ...");
        i = 0;
        while (i < nombre_Categorias.length)
        {
            System.out.print("    " + nombre_Categorias[i] + ": ");
            int j = 0;
            while (j < categoria_Peliculas.length)
            {
                if (nombre_Categorias[i].equals(categoria_Peliculas[j]))
                {
                    System.out.print(nombre_Peliculas[j] + ", ");
                }
                j = j + 1;
            }
            System.out.println();
            i = i + 1;
        }

        System.out.println("[+] incidencia de prestamo de peliculas");
        System.out.println("    orden: idPelicula, nombrePelicula: incidenciaPrestamo");
        i = 0;
        while (i < id_Peliculas.length)
        {
            System.out.println("    " + id_Peliculas[i] + ", " + nombre_Peliculas[i] + ": " + incidencia_prestamo_Peliculas[i]);
            i = i + 1;
        }

        int mayor = incidencia_prestamo_Peliculas[0];
        System.out.println("[+] pelicula mayormente prestada");
        i = 0;
        int mayorI = 0;
        while (i < incidencia_prestamo_Peliculas.length)
        {
            if (mayor <= incidencia_prestamo_Peliculas[i])
            {
                mayor = incidencia_prestamo_Peliculas[i];
                mayorI = i;
            }
            i = i + 1;
        }
        System.out.println("    " + id_Peliculas[mayorI] + ", " + nombre_Peliculas[mayorI] + ": " + incidencia_prestamo_Peliculas[mayorI]);

        int menor = incidencia_prestamo_Peliculas[0];
        System.out.println("[+] pelicula menormente prestada");
        i = 0;
        int menorI = 0;
        while (i < incidencia_prestamo_Peliculas.length)
        {
            if (incidencia_prestamo_Peliculas[i] <= menor)
            {
                menor = incidencia_prestamo_Peliculas[i];
                menorI = i;
            }
            i = i + 1;
        }
        System.out.println("    " + id_Peliculas[menorI] + ", " + nombre_Peliculas[menorI] + ": " + incidencia_prestamo_Peliculas[menorI]);
        menu1();
    }

    static void ordenar()
    {
        System.out.println("nombre de peliculas en orden ascendente");
        String[] nombresOrdenados = nombre_Peliculas;

        for(int i = 0; i < nombresOrdenados.length - 1; i++)
        {
            for (int j = i + 1; j < nombresOrdenados.length; j++)
            {
                if(nombresOrdenados[i].compareTo(nombresOrdenados[j]) > 0)
                {
                    String temp = nombresOrdenados[i];
                    nombresOrdenados[i] = nombresOrdenados[j];
                    nombresOrdenados[j] = temp;
                }
            }
        }
        for (String nombre : nombresOrdenados) {
            System.out.println(nombre);
        }
        menu1();
    }

    static void menu1()
    {
        System.out.println("M E M O R A B I L I A");
        System.out.println("[1] prestamo de peliculas");
        System.out.println("[2] devolucion de peliculas");
        System.out.println("[3] ingresar una pelicula");
        System.out.println("[4] ingresar nuevo cliente");
        System.out.println("[5] listar todas las peliculas");
        System.out.println("[6] listar todos los clientes");
        System.out.println("[7] ordenar peliculas");
        System.out.println("[8] resportes");
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
                ordenar();
                break;
            case 8:
                reportes();
                break;
        }
    }

    public static void main(String[] args)
    {
        // agregamos algunos clientes y peliculas
        Random random1 = new Random();
        int i = 9; // alternativa2: int i = 1;
        while (i > 0) // alternativa2: while (i < 10)
        {
            int id = i * 100 + i * 10 + i;
            int num1 = random1.nextInt(5) + 1;
            int num2 = num1 * 100 + num1 * 10 + num1;
            int num3 = random1.nextInt(42) + 1980;
            String categoria = "categoria" + num2;
            agregarCliente(id, "cliente" + id, id * 100);
            ingresarPeliculaAUTO(id, "pelicula" + id, num3, categoria);
            i = i - 1;
        }
        menu1();
    }
}
