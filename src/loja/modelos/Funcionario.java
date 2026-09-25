package loja.modelos;

public class Funcionario extends Pessoa {

    private String numeroFuncionario;

    public Funcionario(
        String nome,
        String contacto,
        String numeroFuncionario
    ) {
        super(nome, contacto);
        this.numeroFuncionario = numeroFuncionario;
    }

    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(
        String numeroFuncionario
    ) {
        this.numeroFuncionario = numeroFuncionario;
    }

    @Override
    public String getInfo() {
        return super.getInfo()
                + " | Número de funcionário: "
                + numeroFuncionario;
    }
}
