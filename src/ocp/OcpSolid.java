package ocp;

/* OCP = Open-Closed Principle
 * Objetos ou entidades devem estar abertos para extensão, mas fechados para modificação
 * Quando novos comportamentos e recursos precisam ser adicionados no software,devemos estender e não alterar o código fonte original.
 */
public class OcpSolid {
}

class ContractClt {
    public void salary() {/*...*/}
}

class Trainee {
    public void aidScholarship() {/*...*/}
}

/*
 * A classe Payroll precisa verificar o funcionário para aplicar a regra de negócio correta na hora do pagamento.
 * Supondo que a empresa cresceu e resolveu trabalhar com funcionários PJ, obviamente seria necessário modificar essa classe!
 * Sendo assim, estaríamos quebrando o princípio Open-Closed do SOLID.
 *---------------------------------------
 * Não seria mais fácil apenas acrescentar mais um IF e verificar o novo tipo de funcionário PJ aplicando as respectivas regras?
 * Sim, e provavelmente essa seria a solução que programadores menos experientes iriam fazer.
 * Mas, esse é exatamente o problema! Alterar uma classe já existente para adicionar um novo comportamento,
 * corremos um sério risco de introduzir bugs em algo que já estava funcionando.
 *
 * Lembre-se: OCP preza que uma classe deve estar fechada para alteração e aberta para extensão.
 */
class Payroll {
    private Double balance;

    public void calculate(Object employee) {
        if (employee instanceof ContractClt) {
            ((ContractClt) employee).salary();
        } else if (employee instanceof Trainee) {
            ((Trainee) employee).aidScholarship();
        }
    }
}

/*
 *Aplicando OCP na prática
 */
interface Remunerable {
    double remuneration(double salary);
}

class ContractCltRefactor implements Remunerable {
    @Override
    public double remuneration(double salary) {
        return salary * 0.10;
    }
}

class TraineeRefactor implements Remunerable {
    @Override
    public double remuneration(double salary) {
        return salary * 0.8;
    }
}


/*
 * Agora a classe PayrollRefactor não precisa mais saber quais métodos chamar para calcular.
 * Ela será capaz de calcular o pagamento corretamente de qualquer novo tipo de funcionário que seja criado no futuro
 * (ContratoPJ) — desde que ele implemente a interface Remunerable — sem qualquer necessidade de alteração do seu código fonte.
 *  Dessa forma, acabamos de implementar o princípio de Aberto-Fechado do SOLID em nosso código!
 */
class PayrollRefactor {
    private Double balance;

    public double calculate(Remunerable remunerable, double salary) {
        return remunerable.remuneration(salary);
    }
}

class App {
    public static void main(String[] args) {
        PayrollRefactor payrollRefactor = new PayrollRefactor();

        double salaryClt = payrollRefactor.calculate(new ContractCltRefactor(), 1500);
        System.out.println(salaryClt);

        double salaryTrainee = payrollRefactor.calculate(new TraineeRefactor(), 1200);
        System.out.println(salaryTrainee);
    }
}