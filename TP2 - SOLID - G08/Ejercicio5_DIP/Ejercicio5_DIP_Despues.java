// DISEÑO DESPUÉS aplicando DIP

// La clase GestorActas depende de la abstracción IRepositorioActas por inyección de dependencias. Las clases de bajo nivel implementan esta interfaz.

public class Ejercicio5_DIP_Despues {

    interface IRepositorioActas {
        void guardarActa(Acta acta);
    }

    static class BaseDatosPostgreSQL implements IRepositorioActas {
        @Override
        public void guardarActa(Acta acta) {
            System.out.println("Guardando acta en PostgreSQL...");
        }
    }

    static class NubeDynamoDB implements IRepositorioActas {
        @Override
        public void guardarActa(Acta acta) {
            System.out.println("Guardando acta en DynamoDB (Nube)...");
        }
    }

    static class GestorActas {
        private IRepositorioActas repositorio;

        public GestorActas(IRepositorioActas repositorio) {
            this.repositorio = repositorio;
        }

        public void registrarEnActa(Estudiante e, MesaExamen me) {
            Acta acta = new Acta(e, me);
            repositorio.guardarActa(acta);
        }
    }

    // Clases auxiliares de dominio
    static class Estudiante {}
    static class MesaExamen {}
    static class Acta {
        public Acta(Estudiante e, MesaExamen me) {}
    }

    public static void main(String[] args) {
        IRepositorioActas repo = new NubeDynamoDB(); // Fácil de cambiar por BaseDatosPostgreSQL
        GestorActas gestor = new GestorActas(repo);
        gestor.registrarEnActa(new Estudiante(), new MesaExamen());
    }
}
