// DISEÑO ANTES no cumple polimorfismo

// la asignación de puntaje depende del if/switch sobre cadenas de texto en CalculadoraPuntos

public class Ejercicio4_Polimorfismo_Antes {

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
        public void asignarPuntaje(Docente d, String rolEnTesis) {
            if (rolEnTesis.equals("Tutor")) {
                d.sumarPuntos(50);
            } else if (rolEnTesis.equals("Jurado Titular")) {
                d.sumarPuntos(30);
            } else if (rolEnTesis.equals("Jurado Suplente")) {
                d.sumarPuntos(10);
            }
        }
    }

    public static void main(String[] args) {
        Docente docente = new Docente();
        CalculadoraPuntos calc = new CalculadoraPuntos();

        calc.asignarPuntaje(docente, "Tutor");
        calc.asignarPuntaje(docente, "Jurado Titular");

        System.out.println("Puntos totales del docente: " + docente.getPuntos());
    }
}
