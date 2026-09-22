package loja.modelos;

import loja.interfaces.Descontavel;

public class Produto implements Descontavel {

    private String nome;
    private double preco;
    private int quantidadeEmStock;
    private String categoria;

    public Produto(
        String nome,
        double preco,
        int quantidadeEmStock,
        String categoria
    ) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmStock = quantidadeEmStock;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEmStock() {
        return quantidadeEmStock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidadeEmStock(int quantidadeEmStock) {
        this.quantidadeEmStock = quantidadeEmStock;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void aplicarDesconto(double percentagem) {

        if (percentagem > 0 && percentagem <= 100) {
            preco = preco - (preco * percentagem / 100);
        }
    }

    public String getInfo() {
        return "Produto: " + nome
                + " | Preço: " + String.format("%.2f", preco) + " €"
                + " | Stock: " + quantidadeEmStock
                + " | Categoria: " + categoria;
    }

    public boolean temStockSuficiente(int quantidade) {
        return quantidade > 0
                && quantidadeEmStock >= quantidade;
    }

    public void reduzirStock(int quantidade) {

        if (temStockSuficiente(quantidade)) {
            quantidadeEmStock =
                    quantidadeEmStock - quantidade;
        }   
    }
}
