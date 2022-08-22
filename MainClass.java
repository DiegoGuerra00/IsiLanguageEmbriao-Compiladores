import java.util.Scanner;

public class MainClass {
  public static void main(String args[]) {
    Scanner _key = new Scanner(System.in);
    int a;
    String teste;
    int b;
    double numero;
    String t;
    a = _key.nextInt();
    b = _key.nextInt();
    numero = _key.nextDouble();
    b = 90;
    System.out.println("Teste de escrita");
    System.out.println(0);
    if (a == 0) {
      b = 1;
    } else {
      b = 100;
    }

    if (numero == 0) {
      b = 1;
    } else {
      b = 100;
    }

    while (b < 0) {
      System.out.println(a);
      b = a - 5;
    }

    t = "string";
    switch (t) {
      case "error":
        t = _key.nextLine();
      case "hello":
        System.out.println("world");
      default:
        System.out.println(t);

    }

    a = 1;
    b = 1;
    numero = 2.543;
    t = "123";
    a = _key.nextInt();
    b = _key.nextInt();
    System.out.println(b);
    a = b * 2;
    _key.close();
  }
}