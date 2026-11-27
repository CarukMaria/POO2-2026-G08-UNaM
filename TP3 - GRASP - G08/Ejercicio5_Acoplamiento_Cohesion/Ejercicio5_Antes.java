import java.util.Date;

// DISEÑO ANTES con alto acoplamiento y baja cohesión

// ActaEgreso tiene los datos propios de la graduación y la invocación juntos
// directa a los servicios externos 

public class Ejercicio5_Antes {

    static class Estudiante {}

    static class ServicioEmail {
        public void enviarAvisoEgreso(Estudiante e) {
            System.out.println("Enviando aviso de egreso por email...");
        }
    }

    static class ConectorSiuGuarani {
        public void sincronizarEgreso(ActaEgreso acta) {
            System.out.println("Sincronizando egreso con SIU Guaraní...");
        }
    }

    static class ImpresoraPDF {
        public void imprimirDiploma(ActaEgreso acta) {
            System.out.println("Imprimiendo diploma en PDF...");
        }
    }

    static class ActaEgreso {
        private Date fechaGraduacion;
        private Estudiante estudiante;

        public ActaEgreso(Estudiante estudiante, Date fechaGraduacion) {
            this.estudiante = estudiante;
            this.fechaGraduacion = fechaGraduacion;
        }

        public Date getFechaGraduacion() {
            return fechaGraduacion;
        }

        public Estudiante getEstudiante() {
            return estudiante;
        }

        public void registrarEgreso() {
            ServicioEmail email = new ServicioEmail();
            email.enviarAvisoEgreso(this.estudiante);

            ConectorSiuGuarani siu = new ConectorSiuGuarani();
            siu.sincronizarEgreso(this);

            ImpresoraPDF impresora = new ImpresoraPDF();
            impresora.imprimirDiploma(this);
        }
    }

    public static void main(String[] args) {
        ActaEgreso acta = new ActaEgreso(new Estudiante(), new Date());
        acta.registrarEgreso();
    }
}
