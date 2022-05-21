package isp;

/* ISP=Interface Segregation Principle:
 * Uma classe não deve ser forçada a implementar interfaces e métodos que não irão utilizar.
 * Esse princípio basicamente diz que é melhor criar interfaces mais específicas ao invés de termos uma única interface genérica.
 */
public class IspSolid {
}

interface Bird {
    void setLocalization(Object longitude, Object latitude);

    void setAltitude(Object altitude);

    void render();
}

class Parrot implements Bird {
    @Override
    public void setLocalization(Object longitude, Object latitude) {
        //Faz alguma coisa
    }

    @Override
    public void setAltitude(Object altitude) {
        //Faz alguma coisa
    }

    @Override
    public void render() {
        //Faz alguma coisa
    }
}

class Penguin implements Bird {
    @Override
    public void setLocalization(Object longitude, Object latitude) {
        //Faz alguma coisa
    }

    /* A Interface Bird está forçando a Classe Penguin a implementar esse método.
     Isso viola o príncipio ISP */
    @Override
    public void setAltitude(Object altitude) {
        //Não faz nada...  Penguin são aves que não voam!
    }

    @Override
    public void render() {
        //Faz alguma coisa
    }
}

/*
 * Aplicando na pratica LSP
 */

interface BirdRefactor {
    void setLocalization(Object longitude, Object latitude);

    void render();
}

interface BirdFly extends BirdRefactor {
    void setAltitude(Object altitude);
}

class ParrotRefactor implements BirdFly {
    @Override
    public void setLocalization(Object longitude, Object latitude) {
        //Faz alguma coisa
    }

    @Override
    public void setAltitude(Object altitude) {
        //Faz alguma coisa
    }

    @Override
    public void render() {
        //Faz alguma coisa
    }
}

class PenguinRefactor implements BirdRefactor {
    @Override
    public void setLocalization(Object longitude, Object latitude) {
        //Faz alguma coisa
    }

    @Override
    public void render() {
        //Faz alguma coisa
    }
}