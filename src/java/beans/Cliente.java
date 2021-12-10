package beans;

public class Cliente {

    int idCliente;
    String dni;
    String nombres;
    String email;
    String pass;
    String direccion;

    public Cliente() {
    }

    public Cliente(int idCliente, String dni, String Nombres, String email, String pass, String direccion) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombres = Nombres;
        this.email = email;
        this.pass = pass;
        this.direccion = direccion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String Nombres) {
        this.nombres = Nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
    
}
