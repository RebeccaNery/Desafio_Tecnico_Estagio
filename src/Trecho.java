public class Trecho {

    public String texto;
    public int inicio;
    public int fim;
    public int nivel;

    public Trecho(String texto, int inicio, int fim) {
        this.texto = texto;
        this.inicio = inicio;
        this.fim = fim;
        this.nivel = 1;
    }

    public String getTexto() {
        return texto;
    }

    public int getInicio() {
        return inicio;
    }

    public int getFim() {
        return fim;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public void setFim(int fim) {
        this.fim = fim;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public String toString() {
        return "Texto: " + texto + " ==> Inicio: " + inicio + " | Fim: " + fim + " | Nivel: " + nivel;
    }
}
