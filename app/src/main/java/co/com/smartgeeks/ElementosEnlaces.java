package co.com.smartgeeks;

public class ElementosEnlaces {

    String imagenArticulo, descripcionArticulo, tituloArticulo, enlaceArticulo;


    public ElementosEnlaces(String imagenArticulo, String descripcionArticulo, String tituloArticulo, String enlaceArticulo) {
        this.imagenArticulo = imagenArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.tituloArticulo = tituloArticulo;
        this.enlaceArticulo = enlaceArticulo;
    }

    public String getImagenArticulo() {
        return imagenArticulo;
    }

    public void setImagenArticulo(String imagenArticulo) {
        this.imagenArticulo = imagenArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getTituloArticulo() {
        return tituloArticulo;
    }

    public void setTituloArticulo(String tituloArticulo) {
        this.tituloArticulo = tituloArticulo;
    }

    public String getEnlaceArticulo() {
        return enlaceArticulo;
    }

    public void setEnlaceArticulo(String enlaceArticulo) {
        this.enlaceArticulo = enlaceArticulo;
    }
}
