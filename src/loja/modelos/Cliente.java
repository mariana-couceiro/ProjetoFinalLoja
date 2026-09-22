package loja.modelos;

import java.util.Objects;

public class Cliente extends Pessoa {

    private double saldo;

    public Cliente(
        String nome,
        String contacto,
        double saldo
    ) {
        super(nome, contacto);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {

        if (saldo >= 0) {
            this.saldo = saldo;
        }
    }

    public boolean temSaldoSuficiente(
        double valor
    ) {
        return valor >= 0 && saldo >= valor;
    }

    public void descontarSaldo(
        double valor
    ) {

        if (valor > 0 && valor <= saldo) {
            saldo = saldo - valor;
        }
    }

    @Override
    public String getInfo() {

        return super.getInfo()
                + " | Saldo: "
                + String.format("%.2f", saldo)
                + " €";
    }

    @Override
    public boolean equals(Object objeto) {

        if (this == objeto) {
            return true;
        }

        if (
            objeto == null
            || getClass() != objeto.getClass()
        ) {
            return false;
        }

        Cliente outroCliente =
                (Cliente) objeto;

        return getContacto().equals(
            outroCliente.getContacto()
        );
    }

    @Override
    public int hashCode() {

        return Objects.hash(
            getContacto()
        );
    }
}
