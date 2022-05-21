package srp;

/* SRP = SINGLE RESPONSIBILITY PRINCIPLE

A classe SrpSolid viola o SRP porque realiza 3 tipos distintos de tarefas.
    * Falta de coesão — uma classe não deve assumir responsabilidades que não são suas;
    * Alto acoplamento — Mais responsabilidades geram um maior nível de dependências, deixando o sistema engessado e frágil para alterações;
    * Dificuldades na implementação de testes automatizados — É difícil de “mockar” esse tipo de classe;
    * Dificuldades para reaproveitar o código;
*/

import java.util.ArrayList;
import java.util.List;

public class SrpSolid {
    // 1° Responsabilidade
    public void calculateTotalSum() {/*...*/}
    public void getItems() {/*...*/}
    public void getItemCount() {/*...*/}
    public void addItem(Object item) {/*...*/}
    public void deleteItem(Object item) {/*...*/}

    // 2° Responsabilidade
    public void printOrder() {/*...*/}
    public void showOrder() {/*...*/}

    // 3° Responsabilidade
    public void load() {/*...*/}
    public void save() {/*...*/}
    public void update() {/*...*/}
    public void delete() {/*...*/}
}

/*
 * Agora temos 3 classes, cada uma cuidando da sua responsabilidade.
 */

class Order {
    public void calculateTotalSum() {/*...*/}
    public void getItems() {/*...*/}
    public void getItemCount() {/*...*/}
    public void addItem(Object item) {/*...*/}
    public void deleteItem(Object item) {/*...*/}
}

class OrderRepository {
    public void load() {/*...*/}
    public void save() {/*...*/}
    public void update() {/*...*/}
    public void delete() {/*...*/}
}

class OrderViewer {
    public void printOrder() {/*...*/}
    public void showOrder() {/*...*/}
}

/*
    O princípio da responsabilidade única não se limita somente a classes,
    ele também pode ser aplicado em métodos, ou seja,
    tudo que é responsável por executar uma ação,
    deve ser responsável por apenas aquilo que se propõe a fazer.
 */
class SrpSolidMethod {
    private List<String> emailsLowerCasa;

    // Muitas responsabilidades no metodo
    public void emailClients(List<String> emails) {
        this.emailsLowerCasa = new ArrayList<>();
        for (String email : emails) {
            if (email.equals("ACTIVE")) {
                email.toLowerCase();
                emailsLowerCasa.add(email);
            }
        }
    }

    // Unica responsabilidade
    public void emailClientsRefactor(List<String> emails) {
        this.emailsLowerCasa = new ArrayList<>();
        emails.forEach(x -> validateEmailActive(x));
    }

    // Unica responsabilidade
    private void validateEmailActive(String email) {
        if (email.equals("ACTIVE")) {
            String emailLowerCase = transformEmailLowerCase(email);
            addEmailLowerCase(emailLowerCase);
        }
    }

    // Unica responsabilidade
    private String transformEmailLowerCase(String email) {
        return email.toLowerCase();
    }

    // Unica responsabilidade
    private void addEmailLowerCase(String email) {
        this.emailsLowerCasa.add(email);
    }
}