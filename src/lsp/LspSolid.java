package lsp;

/*LSP=Liskov Substitution Principle:
 * Uma classe derivada deve ser substituível por sua classe base.
 *
 * Se S é um subtipo de T, então os objetos do tipo T, em um programa,
 * podem ser substituídos pelos objetos de tipo S sem que seja necessário alterar as propriedades deste programa.
 */
public class LspSolid {
}

class A {
    public void getName() {
        System.out.println("Class A");
    }
}

class B extends A {
    public void getName() {
        System.out.println("Class B");
    }
}

/*
 * Estamos passando como parâmetro tanto a classe pai como a classe derivada e o código continua funcionando da forma esperada.
 *
 * Exemplos de violação do LSP:
        * Sobrescrever/implementar um método que não faz nada;
        * Lançar uma exceção inesperada;
        * Retornar valores de tipos diferentes da classe base;
  *
  * Seguir o LSP nos permite usar o polimorfismo com mais confiança.
  * Podemos chamar nossas classes derivadas referindo-se à sua classe base sem preocupações com resultados inesperados.
 */
class App {
    public static void main(String[] args) {
        A classA = new A();
        B classB = new B();

        printName(classA);
        printName(classB);
    }

    private static void printName(A a) {
        a.getName();
    }
}
