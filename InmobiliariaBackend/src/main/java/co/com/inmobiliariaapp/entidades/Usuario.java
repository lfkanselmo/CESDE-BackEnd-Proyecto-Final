package co.com.inmobiliariaapp.entidades;

public class Usuario {

    private Long id;
    private String usuario;
    private String contrasenha;
    private Rol rol;

    public Usuario(Long id, String usuario, String contrasenha, Rol rol) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.rol = rol;
    }

    public Usuario(String usuario, String contrasenha, Rol rol) {
        this.usuario = usuario;
        this.contrasenha = contrasenha;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
