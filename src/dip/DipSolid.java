package dip;

/*
 * DIP=Dependency Inversion Principle
 * Dependa de abstrações e não de implementações.
 *
 * Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender da abstração.
 * Abstrações não devem depender de detalhes. Detalhes devem depender de abstrações.
 *
 * É comum que as pessoas confundam a Inversão de Dependência com a Injeção de Dependência,
 * porém são coisas distintas, mas que relacionam entre si com um proposito em comum, deixar o código desacoplado.
 *
 * Importante: Inversão de Dependência não é igual a Injeção de Dependência, fique ciente disso!
 * A Inversão de Dependência é um princípio (Conceito) e a Injeção de Dependência é um padrão de projeto (Design Pattern).
 */
public class DipSolid {
}

class MySQLConnection {

}


/*
 * Nesse pequeno trecho de código temos um alto nível de acoplamento
 * Isso acontece porque a classe PasswordReminder tem a responsabilidade de criar uma instância da classe MySQLConnection
 * Se quiséssemos reaproveitar essa classe em outro sistema, teriamos obrigatoriamente de levar a classe MySQLConnection junto,
 * portanto, temos um forte acoplamento aqui.
 */

class PasswordReminder {
    private Object connection;

    public PasswordReminder() {
        this.connection = new MySQLConnection();
    }
}

/*
 * Para resolver esse problema de acoplamento, podemos refatorar nosso código da seguinte forma.
 * Com o código refatorado, a criação do objeto MySQLConnection deixa de ser uma responsabilidade da classe PasswordReminderRefactor
 * A classe MySQLConnection virou uma dependência que deve ser injetada via método construtor.
 *
 * Apesar de termos usado a injeção de dependência para melhorar o nível de acoplamento do nosso código, ele ainda viola o princípio da inversão de dependência!
 *
 * Além de violar o DIP, se você prestar atenção na forma que o exemplo foi codificado irá perceber que ele também viola o Open-Closed Principle.
 * Por exemplo, se precisarmos alterar o banco de dados de MySQL para Oracle teríamos que editar a classe PasswordReminderRefactor.
 *
 * Por que nosso exemplo refatorado viola o Dependency Inversion Principle?
     - Porque estamos dependendo de uma implementação e não de uma abstração, simples assim.
     *
 * Primeira coisa que precisamos fazer é identificar no nosso código qual é o módulo de alto nível e qual é o módulo de baixo nível.
 * Módulo de alto nível é um módulo que depende de outros módulos.
 * PasswordReminderRefactor depende da classe MySQLConnection.
 * Sendo assim, PasswordReminderRefactor é o módulo de alto nível e MySQLConnection é o módulo de baixo nível.
 */

class PasswordReminderRefactor {
    private Object connection;

    public PasswordReminderRefactor(MySQLConnection mySQLConnection) {
        this.connection = mySQLConnection;
    }
}

/*
 * Vamos refatorar nosso exemplo para utilizar o DIP
 */

interface DBConnection {
    void connect();
}

class MySQLConnectionRefactor implements DBConnection {
    @Override
    public void connect() {
        // FAZ ALGUMA COISA
    }
}

class OracleConnection implements DBConnection {
    @Override
    public void connect() {
        // FAZ ALGUMA COISA
    }
}

/*
 * Agora a classe PasswordReminderRefactorUsingAbstraction não tem a mínima ideia de qual banco de dados a aplicação irá utilizar.
 * Dessa forma, não estamos mais violando o DIP, ambas as classes estão desacopladas e dependendo de uma abstração.
 * Além disso, estamos favorecendo a reusabilidade do código e como “bônus” também estamos respeitando o SRP e o OCP.
 */
class PasswordReminderRefactorUsingAbstraction {
    private DBConnection connection;

    public PasswordReminderRefactorUsingAbstraction(DBConnection dbConnection) {
        this.connection = dbConnection;
    }
}