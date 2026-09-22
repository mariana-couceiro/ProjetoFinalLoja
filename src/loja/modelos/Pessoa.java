package loja.modelos;

public class Pessoa {

    private String nome;
    private String contacto;

    public Pessoa(String nome, String contacto) {
        this.nome = nome;
        this.contacto = contacto;
    }

    public String getNome() {
        return nome;
    }

    public String getContacto() {
        return contacto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getInfo() {
        return "Nome: " + nome
                + " | Contacto: " + contacto;
    }
}
