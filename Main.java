import java.util.ArrayList;  // Importa ArrayList
import java.util.Scanner;    // Importa Scanner

public class Main {
    public static void main(String[] args) {
        HashTable<String, Producto> catalogo = new HashTable<>(5);
        ArrayList<String> listaProductos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar catálogo");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Modificar producto");
            System.out.println("5. Mostrar lista de productos ingresados");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarProducto(catalogo, listaProductos, scanner);
                    break;
                case 2:
                    mostrarCatalogo(catalogo);
                    break;
                case 3:
                    eliminarProducto(catalogo, listaProductos, scanner);
                    break;
                case 4:
                    modificarProducto(catalogo, scanner);
                    break;
                case 5:
                    mostrarListaProductos(listaProductos);
                    break;
                case 6:
                    System.out.println("¡Gracias por usar el sistema!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    public static void agregarProducto(HashTable<String, Producto> catalogo, ArrayList<String> listaProductos, Scanner scanner) {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());
        Producto producto = new Producto(precio);
        catalogo.insert(nombre, producto);
        listaProductos.add(nombre + ": $" + precio);
        System.out.println("Producto agregado.");
    }

    public static void mostrarCatalogo(HashTable<String, Producto> catalogo) {
        System.out.println("Catálogo de productos:");
        for (int i = 0; i < catalogo.getCapacity(); i++) {
            System.out.println("Índice " + i + ": " + catalogo.getBucket(i));
        }
    }

    public static void eliminarProducto(HashTable<String, Producto> catalogo, ArrayList<String> listaProductos, Scanner scanner) {
        System.out.print("Nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();
        catalogo.remove(nombre);
        listaProductos.removeIf(p -> p.startsWith(nombre + ":"));
        System.out.println("Producto eliminado.");
    }

    public static void modificarProducto(HashTable<String, Producto> catalogo, Scanner scanner) {
        System.out.print("Nombre del producto a modificar: ");
        String nombre = scanner.nextLine();
        Producto producto = catalogo.get(nombre);
        if (producto != null) {
            System.out.print("Nuevo precio: ");
            double precio = Double.parseDouble(scanner.nextLine());
            producto.setPrecio(precio);
            System.out.println("Producto modificado.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public static void mostrarListaProductos(ArrayList<String> listaProductos) {
        System.out.println("Lista de productos ingresados:");
        listaProductos.forEach(System.out::println);
    }
}
