package beans;


public class Pago {

    int idpago;
    double monto;

    public Pago() {
    }

    public Pago(int idpago, double monto) {
        this.idpago = idpago;
        this.monto = monto;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
