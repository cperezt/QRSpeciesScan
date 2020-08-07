package co.com.smartgeeks;

public class ElementosLista {

    int id;
    String imagen;
    String especieCodigo;

    public ElementosLista() {
    }

    public ElementosLista(int id, String imagen, String especieCodigo) {
        this.id = id;
        this.imagen = imagen;
        this.especieCodigo = especieCodigo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEspecieCodigo() {
        return especieCodigo;
    }

    public void setEspecieCodigo(String especieCodigo) {
        this.especieCodigo = especieCodigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
