import java.util.InputMismatchException;
import java.util.Scanner;

public class MainCursos {
    public static void main(String[] args) {
        PersonData data = new PersonData();
        Scanner input = new Scanner(System.in);
        int opt = 0;
        
        do {
            System.out.println("***** CRUD PERSON *****");
            System.out.println("1 List ");
            System.out.println("2 New ");
            System.out.println("3 Delete ");
            System.out.println("4 Get byID ");
            System.out.println("5 Update ");
            System.out.println("0 Exit ");
            System.out.println("Choice option: ");
            opt = input.nextInt();
            System.out.println("You chosed: " + opt);
            input.nextLine(); // Limpiar el buffer
            switch (opt) {
                case 1:
                    System.out.println("Listado de cursos ");
                    for (Cursos d : data.list("")) {
                        System.out.println(d.getId() + "\t" + d.getNombre() + "\t" + d.getDescrpcion() + "\t" + d.getClase() + "\t" + d.getCreditos());
                    }
                    break;
                case 2:
                    System.out.println("Nuevo curso ");
                    Cursos p = new Cursos();
                    System.out.print("nombre: ");
                    p.setNombre(input.nextLine());
                    System.out.print("creditos: ");
                    p.setCreditos(input.nextLine());

                    System.out.print("creditos: ");
                    try {
                        p.setCreditos(input.nextInt());
                        data.create(p);
                    } catch (Exception e) {
                        input.nextLine(); // Limpiar el buffer
                        System.out.print("Creditos debe ser entero, no se guardo");
                    }

                    break;
                case 3:
                    System.out.println("Eliminar cursos ");
                    System.out.print("id: ");
                    data.delete(input.nextInt());
                    break;
                case 4:
                    int b4 = 1;
                    do {
                        System.out.println("get cursos ");
                        System.out.print("id: ");
                        int id = 0;
                        try {
                            b4 = 0;
                            id = input.nextInt();
                            Cursos d = data.get(id);
                            if (d != null) {
                                System.out.println("Id: " + d.getId());
                                System.out.println("Nombre: " + d.getNombre());
                            } else {
                                System.out.print("el curso no existe");
                            }
                        } catch (Exception e) {
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("id no valido, debe ser un numero");
                            b4 = 1;
                        }

                    } while (b4 != 0);

                    break;
                case 5:
                    System.out.println("update curso ");
                    System.out.print("id: ");

                    Cursos cur = data.get(input.nextInt());

                    if (cur != null) {
                        System.out.println("Nombre current: " + cur.getNombre());
                        System.out.println("Descripcion current: " + cur.getDescripcion());

                        input.nextLine(); // Limpiar el buffer
                        System.out.print("new nombre: ");
                        cur.setNombre(input.nextLine());

                        System.out.print("new descripcion: ");
                        cur.setdescripcion(input.nextLine());

                        System.out.print("creditos: ");
                        try {
                            cur.setCreditos(input.nextInt());
                            data.update(cur);
                        } catch (Exception e) {
                            // cur.setAge(0);
                            input.nextLine(); // Limpiar el buffer
                            System.out.print("Edad debe ser entero, no se guardo");
                        }

                    } else {
                        System.out.println("NO encontrado");
                    }

                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        } while (opt != 0);
    }
}
