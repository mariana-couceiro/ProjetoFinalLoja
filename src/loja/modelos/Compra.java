package loja.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import loja.interfaces.Descontavel;

public class Compra implements Descontavel {

    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private double valorSemDesconto;
    private double percentagemDesconto;
    private double valorTotal;
    private LocalDateTime dataHora;

    public Compra(
        Cliente cliente,
        Produto produto,
        int quantidade
    ) {
        this.cliente = cliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorSemDesconto = produto.getPreco() * quantidade;
        this.percentagemDesconto = 0;
        this.valorTotal = valorSemDesconto;
        this.dataHora = LocalDateTime.now();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorSemDesconto() {
        return valorSemDesconto;
    }

    public double getPercentagemDesconto() {
        return percentagemDesconto;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public void aplicarDesconto(
        double percentagem
    ) {

        if (percentagem > 0 && percentagem <= 100) {
            percentagemDesconto = percentagem;

            valorTotal = valorSemDesconto
                    - (valorSemDesconto * percentagem / 100);
        }
    }

    public String getInfo() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String informacao = "Cliente: " + cliente.getNome()
                + " | Produto: " + produto.getNome()
                + " | Quantidade: " + quantidade
                + " | Subtotal: "
                + String.format("%.2f", valorSemDesconto) + " €";

        if (percentagemDesconto > 0) {
            informacao = informacao
                    + " | Cupão: "
                    + String.format("%.2f", percentagemDesconto)
                    + "%";
        }

        return informacao
                + " | Total: "
                + String.format("%.2f", valorTotal) + " €"
                + " | Data: " + dataHora.format(formato);
    }
}
