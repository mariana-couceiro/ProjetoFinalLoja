package loja.modelos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Compra {

    private Cliente cliente;
    private Produto produto;
    private int quantidade;
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
        this.valorTotal = produto.getPreco() * quantidade;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getInfo() {

        DateTimeFormatter formato =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Cliente: " + cliente.getNome()
                + " | Produto: " + produto.getNome()
                + " | Quantidade: " + quantidade
                + " | Total: "
                + String.format("%.2f", valorTotal) + " €"
                + " | Data: " + dataHora.format(formato);
    }
}
