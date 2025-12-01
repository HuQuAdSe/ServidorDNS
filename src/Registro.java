public class Registro {
    private String tipo;   // A, MX, CNAME
    private String valor;  // IP o dominio asociado

    public Registro(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return tipo + " " + valor;
    }
}
