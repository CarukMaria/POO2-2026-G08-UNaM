import java.util.Date;

// DISEÑO DESPUÉS con bajo Acoplamiento y alta Cchesión

// ActaEgreso maneja únicamente los datos puros del egreso, mientras que
// ServicioEgreso maneja los servicios externos

public class Ejercicio5_Despues {

    static class Estudiante {}

    static class ActaEgreso {
        private Date fechaGraduacion;
        private Estudiante estudiante;

        public ActaEgreso(Estudiante e, Date fecha) {
            this.estudiante = e;
            this.fechaGraduacion = fecha;
        }

        public Date getFechaGraduacion() {
            return fechaGraduacion;
        }

        public Estudiante getEstudiante() {
            return estudiante;
        }
    }

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

    static class ServicioEgreso {
        private ServicioEmail servicioEmail;
        private ConectorSiuGuarani conectorSiu;
        private ImpresoraPDF impresoraPdf;

        public ServicioEgreso(ServicioEmail email, ConectorSiuGuarani siu, ImpresoraPDF impresora) {
            this.servicioEmail = email;
            this.conectorSiu = siu;
            this.impresoraPdf = impresora;
        }

        public ActaEgreso procesarEgreso(Estudiante e) {
            ActaEgreso acta = new ActaEgreso(e, new Date());

            servicioEmail.enviarAvisoEgreso(e);
            conectorSiu.sincronizarEgreso(acta);
            impresoraPdf.imprimirDiploma(acta);

            return acta;
        }
    }

    public static void main(String[] args) {
        ServicioEgreso servicio = new ServicioEgreso(
            new ServicioEmail(),
            new ConectorSiuGuarani(),
            new ImpresoraPDF()
        );
        servicio.procesarEgreso(new Estudiante());
    }
}
