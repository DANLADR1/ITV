package itv.vehiculos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Vehiculo {

    private static final char PERIODICA = 'P';
    private static final char REVISION = 'R';
    private static TIPOS[] tipos = TIPOS.values();

    public enum TIPOS {
        MOTOCICLETA, TURISMO, CAMION, AUTOBUS;

        public static TIPOS porDefecto(){
            return TIPOS.TURISMO;
        }
        public static String cadenaItems(){
            StringBuilder resultado = new StringBuilder("(");
            TIPOS[] t= tipos;
            for (int i=0;i<t.length;i++){
                resultado.append(i);
                resultado.append(".-");
                resultado.append(t[i]);
                resultado.append(" ");
            }
            resultado.append("):");
            return resultado.toString();
        }
    }

    private String matricula;
    private TIPOS tipo;
    private Date fecha;
    private char inspeccion; // 'P' periodica- 'R' revisión


    public Vehiculo(String matricula) {
        this(matricula, PERIODICA);
    }

    public Vehiculo(String matricula, char inspeccion) { this(matricula,TIPOS.porDefecto().ordinal(), inspeccion); }

    public Vehiculo(String matricula, int tipo, char inspeccion) {
        matricula=matricula.toUpperCase();
        inspeccion= Character.toUpperCase(inspeccion);

        if(!(matricula.matches("^\\d{4}([BCDFGHJKLMN" + PERIODICA + REVISION + "STVWXYZ]{3})$") ||
                matricula.matches("^([A-Z]{1,2})\\d{4}([A-Z]{1,2})$")))
            throw  new IllegalArgumentException("¡Error!:Formato de Matrícula no válido.");

        TIPOS t=TIPOS.porDefecto();
        if (tipo>=0 && tipo< tipos.length)
            t= tipos[tipo];

        char i= PERIODICA;
        if (inspeccion== REVISION)
            i= REVISION;

        this.matricula = matricula;
        this.tipo = t;
        this.fecha = new Date();
        this.inspeccion = i;
    }

    public String getMatricula() {
        return matricula;
    }

    public char getInspeccion() {
        return inspeccion;
    }

    public TIPOS getTipo() {
        return tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTipo(int tipo) {
        if (tipo>=0 && tipo< tipos.length) // Si no dejamos el valor cargado
            this.tipo= tipos[tipo];
    }

    public void setInspeccion(char inspeccion) {
        inspeccion= Character.toUpperCase(inspeccion);
        if (inspeccion== PERIODICA || inspeccion== REVISION) // Si no dejamos el valor cargado
            this.inspeccion =inspeccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return matricula.equals(vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    public String toString(){
        StringBuilder resultado = new StringBuilder("Matrícula: ");
        resultado.append(this.getMatricula());
        resultado.append("\nTipo de vehículo: ");
        resultado.append(this.getTipo());
        resultado.append("\nInspección: ");
        resultado.append(this.getInspeccion()== PERIODICA ? PERIODICA + "eriódica" : REVISION + "evisión");
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        resultado.append("\nFecha y hora de llegada: ");
        resultado.append(formateador.format(this.getFecha()));
        return resultado.toString();
    }
}
