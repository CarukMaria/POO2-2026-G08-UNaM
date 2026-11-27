
// DISEÑO ANTES no aplica DIP

// la clase GestorActas depende de la clase BaseDatosPostgreSQL y la instancia

public class Ejercicio5_DIP_Antes {

    static class BaseDatosPostgreSQL {
        public void guardarActa(Acta acta) {
            System.out.println("Guardando acta en PostgreSQL...");
        }
    }

    static class GestorActas {
        private BaseDatosPostgreSQL db;

        public GestorActas() {
            this.db = new BaseDatosPostgreSQL();
        }

        public void registrarEnActa(Estudiante e, MesaExamen me) {
            Acta acta = new Acta(e, me);
            db.guardarActa(acta);
        }
    }

    // Clases auxiliares de dominio
    static class Estudiante {}
    static class MesaExamen {}
    static class Acta {
        public Acta(Estudiante e, MesaExamen me) {}
    }

    public static void main(String[] args) {
        GestorActas gestor = new GestorActas();
        gestor.registrarEnActa(new Estudiante(), new MesaExamen());
    }
}
