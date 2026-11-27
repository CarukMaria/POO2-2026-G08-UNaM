// DISEÑO DESPUÉS aplicando polimorfismo

// se elimina condicional basado en Strings y las subclases de RolTesis
// encapsula y calcula su propio puntaje con polimorfismo

public class Ejercicio4_Polimorfismo_Despues {

    static abstract class RolTesis {
        public abstract int calcularPuntos();
    }

    static class Tutor extends RolTesis {
        @Override
        public int calcularPuntos() {
            return 50;
        }
    }

    static class JuradoTitular extends RolTesis {
        @Override
        public int calcularPuntos() {
            return 30;
        }
    }

    static class JuradoSuplente extends RolTesis {
        @Override
        public int calcularPuntos() {
            return 10;
        }
    }

    static class Docente {
        private int puntos = 0;

        public void sumarPuntos(int puntos) {
            this.puntos += puntos;
        }

        public int getPuntos() {
            return puntos;
        }
    }

    static class CalculadoraPuntos {
        public void asignarPuntaje(Docente d, RolTesis rol) {
            int puntos = rol.calcularPuntos();
            d.sumarPuntos(puntos);
        }
    }

    public static void main(String[] args) {
        Docente docente = new Docente();
        CalculadoraPuntos calc = new CalculadoraPuntos();

        calc.asignarPuntaje(docente, new Tutor());
        calc.asignarPuntaje(docente, new JuradoTitular());

        System.out.println("Puntos totales del docente: " + docente.getPuntos());
    }
}
