package co.com.smartgeeks;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Especies {
    @PrimaryKey(autoGenerate = true)
    Long id;
    @ColumnInfo(name = "idEspecie")
    int idEspecie;
    @ColumnInfo(name = "especie")
    String especie;
    @ColumnInfo(name = "codigo")
    String codigo;
    @ColumnInfo(name = "fotoHoja")
    String fotoHoja;
    @ColumnInfo(name = "fotoFruto")
    String fotoFruto;
    @ColumnInfo(name = "fotoFrutoVerde")
    String fotoFrutoVerde;
    @ColumnInfo(name = "fotoPlanta")
    String fotoPlanta;
    @ColumnInfo(name = "fotoSensoriales")
    String fotoSensoriales;
    @ColumnInfo(name = "fotoGrano")
    String fotoGrano;
    @ColumnInfo(name = "condiciones")
    String condiciones;
    @ColumnInfo(name = "indices")
    String indices;
    @ColumnInfo(name = "variables")
    String variables;
    @ColumnInfo(name = "bromatologicos")
    String bromatologicos;
    @ColumnInfo(name = "fisicos")
    String fisicos;
    @ColumnInfo(name = "sensoriales")
    String sensoriales;
    @ColumnInfo(name = "edaficos")
    String edaficos;

    public Especies() {
    }


    public String getFotoGrano() {
        return fotoGrano;
    }

    public void setFotoGrano(String fotoGrano) {
        this.fotoGrano = fotoGrano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFotoHoja() {
        return fotoHoja;
    }

    public void setFotoHoja(String fotoHoja) {
        this.fotoHoja = fotoHoja;
    }

    public String getFotoFruto() {
        return fotoFruto;
    }

    public void setFotoFruto(String fotoFruto) {
        this.fotoFruto = fotoFruto;
    }

    public String getFotoFrutoVerde() {
        return fotoFrutoVerde;
    }

    public void setFotoFrutoVerde(String fotoFrutoVerde) {
        this.fotoFrutoVerde = fotoFrutoVerde;
    }

    public String getFotoPlanta() {
        return fotoPlanta;
    }

    public void setFotoPlanta(String fotoPlanta) {
        this.fotoPlanta = fotoPlanta;
    }

    public String getFotoSensoriales() {
        return fotoSensoriales;
    }

    public void setFotoSensoriales(String fotoSensoriales) {
        this.fotoSensoriales = fotoSensoriales;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getIndices() {
        return indices;
    }

    public void setIndices(String indices) {
        this.indices = indices;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public String getBromatologicos() {
        return bromatologicos;
    }

    public void setBromatologicos(String bromatologicos) {
        this.bromatologicos = bromatologicos;
    }

    public String getFisicos() {
        return fisicos;
    }

    public void setFisicos(String fisicos) {
        this.fisicos = fisicos;
    }

    public String getSensoriales() {
        return sensoriales;
    }

    public void setSensoriales(String sensoriales) {
        this.sensoriales = sensoriales;
    }

    public String getEdaficos() {
        return edaficos;
    }

    public void setEdaficos(String edaficos) {
        this.edaficos = edaficos;
    }
}
