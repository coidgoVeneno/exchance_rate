package exachange.com.modelos;

public class HistorialDeBusqueda {


    String pais;
    String codigoModena;
    double rateLocal;
    double cantidad;
    int tipoDeConversion;
    String optionFinal;


    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoModena() {
        return codigoModena;
    }

    public void setCodigoModena(String codigoModena) {
        this.codigoModena = codigoModena;
    }

    public double getRateLocal() {
        return rateLocal;
    }

    public void setRateLocal(double rateLocal) {
        this.rateLocal = rateLocal;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipoDeConversion() {
        return tipoDeConversion;
    }

    public void setTipoDeConversion(int tipoDeConversion) {
        this.tipoDeConversion = tipoDeConversion;
    }

    public String getOptionFinal() {
        return optionFinal;
    }

    public void setOptionFinal(String optionFinal) {
        this.optionFinal = optionFinal;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
