package loja.sistema;

import loja.modelos.Cliente;
import loja.modelos.Compra;
import loja.modelos.Produto;

public class Loja {

    private Produto[] produtos;
    private Cliente[] clientes;
    private Compra[] compras;

    private int totalProdutos;
    private int totalClientes;
    private int totalCompras;

    public Loja() {

        produtos = new Produto[100];
        clientes = new Cliente[100];
        compras = new Compra[500];

        totalProdutos = 0;
        totalClientes = 0;
        totalCompras = 0;

        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {

        produtos[totalProdutos] = new Produto(
            "Arroz",
            1.49,
            30,
            "Alimentação"
        );
        totalProdutos++;

        produtos[totalProdutos] = new Produto(
            "Detergente",
            3.99,
            20,
            "Limpeza"
        );
        totalProdutos++;

        produtos[totalProdutos] = new Produto(
            "Auscultadores",
            29.90,
            10,
            "Eletrónica"
        );
        totalProdutos++;

        produtos[totalProdutos] = new Produto(
            "Caderno",
            2.50,
            25,
            "Papelaria"
        );
        totalProdutos++;

        produtos[totalProdutos] = new Produto(
            "Champô",
            4.75,
            15,
            "Higiene"
        );
        totalProdutos++;

        clientes[totalClientes] = new Cliente(
            "Ana Silva",
            "912345678",
            100.00
        );
        totalClientes++;

        clientes[totalClientes] = new Cliente(
            "João Santos",
            "923456789",
            75.00
        );
        totalClientes++;

        clientes[totalClientes] = new Cliente(
            "Maria Costa",
            "934567890",
            150.00
        );
        totalClientes++;
    }

    public boolean registarProduto(
        Produto produto
    ) {

        if (produto == null) {
            System.out.println(
                "Produto inválido."
            );

            return false;
        }

        if (totalProdutos >= produtos.length) {
            System.out.println(
                "Não existe espaço para "
                + "registar mais produtos."
            );

            return false;
        }

        produtos[totalProdutos] = produto;
        totalProdutos++;

        System.out.println(
            "Produto registado com sucesso."
        );

        return true;
    }

    public boolean registarCliente(
        Cliente cliente
    ) {

        if (cliente == null) {
            System.out.println(
                "Cliente inválido."
            );

            return false;
        }

        for (int i = 0; i < totalClientes; i++) {

            if (clientes[i].equals(cliente)) {
                System.out.println(
                    "Já existe um cliente com esse "
                    + "contacto telefónico."
                );

                return false;
            }
        }

        if (totalClientes >= clientes.length) {
            System.out.println(
                "Não existe espaço para "
                + "registar mais clientes."
            );

            return false;
        }

        clientes[totalClientes] = cliente;
        totalClientes++;

        System.out.println(
            "Cliente registado com sucesso."
        );

        System.out.println(
            "Código de cliente atribuído: "
            + cliente.getCodigoCliente()
        );

        return true;
    }

    public void listarProdutos() {

        if (totalProdutos == 0) {
            System.out.println(
                "Não existem produtos registados."
            );

            return;
        }

        System.out.println(
            "\n--- PRODUTOS ---"
        );

        for (int i = 0; i < totalProdutos; i++) {
            System.out.println(
                (i + 1)
                + " - "
                + produtos[i].getInfo()
            );
        }
    }

    public void listarClientes() {

        if (totalClientes == 0) {
            System.out.println(
                "Não existem clientes registados."
            );

            return;
        }

        System.out.println(
            "\n--- CLIENTES ---"
        );

        for (int i = 0; i < totalClientes; i++) {
            System.out.println(
                (i + 1)
                + " - "
                + clientes[i].getInfo()
            );
        }
    }

    public void listarCompras() {

        if (totalCompras == 0) {
            System.out.println(
                "Não existem compras registadas."
            );

            return;
        }

        System.out.println(
            "\n--- TODAS AS COMPRAS ---"
        );

        for (int i = 0; i < totalCompras; i++) {
            System.out.println(
                (i + 1)
                + " - "
                + compras[i].getInfo()
            );
        }
    }

    public Produto getProduto(
        int indice
    ) {

        if (
            indice < 0
            || indice >= totalProdutos
        ) {
            return null;
        }

        return produtos[indice];
    }

    public Cliente getCliente(
        int indice
    ) {

        if (
            indice < 0
            || indice >= totalClientes
        ) {
            return null;
        }

        return clientes[indice];
    }

    public Cliente procurarClientePorCodigo(
        String codigoCliente
    ) {

        for (int i = 0; i < totalClientes; i++) {

            if (
                clientes[i]
                .getCodigoCliente()
                .equalsIgnoreCase(codigoCliente)
            ) {
                return clientes[i];
            }
        }

        return null;
    }

    public boolean realizarCompra(
        Cliente cliente,
        Produto produto,
        int quantidade
    ) {

        if (
            cliente == null
            || produto == null
        ) {
            System.out.println(
                "Cliente ou produto inválido."
            );

            return false;
        }

        if (quantidade <= 0) {
            System.out.println(
                "A quantidade tem de ser "
                + "superior a zero."
            );

            return false;
        }

        if (
            !produto.temStockSuficiente(
                quantidade
            )
        ) {
            System.out.println(
                "Compra não realizada: "
                + "stock insuficiente."
            );

            return false;
        }

        double valorTotal =
                produto.getPreco()
                * quantidade;

        if (
            !cliente.temSaldoSuficiente(
                valorTotal
            )
        ) {
            System.out.println(
                "Compra não realizada: "
                + "saldo insuficiente."
            );

            return false;
        }

        if (totalCompras >= compras.length) {
            System.out.println(
                "Não existe espaço para "
                + "registar mais compras."
            );

            return false;
        }

        Compra compra = new Compra(
            cliente,
            produto,
            quantidade
        );

        produto.reduzirStock(quantidade);
        cliente.descontarSaldo(valorTotal);

        compras[totalCompras] = compra;
        totalCompras++;

        System.out.println(
            "Compra realizada com sucesso."
        );

        System.out.println(
            compra.getInfo()
        );

        return true;
    }

    public void consultarHistorico(
        Cliente cliente
    ) {

        if (cliente == null) {
            System.out.println(
                "Cliente inválido."
            );

            return;
        }

        boolean encontrouCompra = false;

        System.out.println(
            "\n--- HISTÓRICO DE "
            + cliente.getNome().toUpperCase()
            + " ---"
        );

        for (int i = 0; i < totalCompras; i++) {

            if (
                compras[i]
                .getCliente()
                .equals(cliente)
            ) {
                System.out.println(
                    compras[i].getInfo()
                );

                encontrouCompra = true;
            }
        }

        if (!encontrouCompra) {
            System.out.println(
                "Este cliente ainda não "
                + "realizou compras."
            );
        }
    }

    public void mostrarRelatorio() {

        if (totalCompras == 0) {
            System.out.println(
                "Ainda não existem compras registadas."
            );

            return;
        }

        Produto produtoMaisVendido = null;
        int maiorQuantidadeVendida = 0;

        for (int i = 0; i < totalProdutos; i++) {

            int quantidadeVendida = 0;

            for (int j = 0; j < totalCompras; j++) {

                if (
                    compras[j].getProduto()
                    == produtos[i]
                ) {
                    quantidadeVendida =
                            quantidadeVendida
                            + compras[j].getQuantidade();
                }
            }

            if (
                quantidadeVendida
                > maiorQuantidadeVendida
            ) {
                maiorQuantidadeVendida =
                        quantidadeVendida;

                produtoMaisVendido =
                        produtos[i];
            }
        }

        Cliente clienteQueMaisGastou = null;
        double maiorValorGasto = 0;

        for (int i = 0; i < totalClientes; i++) {

            double valorGasto = 0;

            for (int j = 0; j < totalCompras; j++) {

                if (
                    compras[j]
                    .getCliente()
                    .equals(clientes[i])
                ) {
                    valorGasto =
                            valorGasto
                            + compras[j].getValorTotal();
                }
            }

            if (valorGasto > maiorValorGasto) {
                maiorValorGasto = valorGasto;

                clienteQueMaisGastou =
                        clientes[i];
            }
        }

        double receitaTotal = 0;
        int unidadesVendidas = 0;

        for (int i = 0; i < totalCompras; i++) {

            receitaTotal =
                    receitaTotal
                    + compras[i].getValorTotal();

            unidadesVendidas =
                    unidadesVendidas
                    + compras[i].getQuantidade();
        }

        System.out.println(
            "\n--- RELATÓRIO DA LOJA ---"
        );

        System.out.println(
            "Número de compras: "
            + totalCompras
        );

        System.out.println(
            "Unidades vendidas: "
            + unidadesVendidas
        );

        System.out.println(
            "Receita total: "
            + String.format(
                "%.2f",
                receitaTotal
            )
            + " €"
        );

        if (produtoMaisVendido != null) {
            System.out.println(
                "Produto mais vendido: "
                + produtoMaisVendido.getNome()
            );

            System.out.println(
                "Quantidade vendida: "
                + maiorQuantidadeVendida
            );
        }

        if (clienteQueMaisGastou != null) {
            System.out.println(
                "Cliente que mais gastou: "
                + clienteQueMaisGastou.getNome()
            );

            System.out.println(
                "Total gasto: "
                + String.format(
                    "%.2f",
                    maiorValorGasto
                )
                + " €"
            );
        }
    }

    public void ordenarProdutosPorPreco() {

        if (totalProdutos == 0) {
            System.out.println(
                "Não existem produtos registados."
            );

            return;
        }

        for (
            int i = 0;
            i < totalProdutos - 1;
            i++
        ) {

            for (
                int j = 0;
                j < totalProdutos - 1 - i;
                j++
            ) {

                if (
                    produtos[j].getPreco()
                    > produtos[j + 1].getPreco()
                ) {
                    Produto temporario =
                            produtos[j];

                    produtos[j] =
                            produtos[j + 1];

                    produtos[j + 1] =
                            temporario;
                }
            }
        }

        System.out.println(
            "Produtos ordenados por preço."
        );

        listarProdutos();
    }

    public boolean reporStock(
        Produto produto,
        int quantidade
    ) {

        if (produto == null) {
            System.out.println(
                "Produto inválido."
            );

            return false;
        }

        if (!produto.adicionarStock(quantidade)) {
            System.out.println(
                "A quantidade tem de ser "
                + "superior a zero."
            );

            return false;
        }

        System.out.println(
            "Stock reposto com sucesso."
        );

        System.out.println(
            produto.getInfo()
        );

        return true;
    }

    public boolean adicionarSaldo(
        Cliente cliente,
        double valor
    ) {

        if (cliente == null) {
            System.out.println(
                "Cliente inválido."
            );

            return false;
        }

        if (!cliente.adicionarSaldo(valor)) {
            System.out.println(
                "O valor tem de ser "
                + "superior a zero."
            );

            return false;
        }

        System.out.println(
            "Saldo adicionado com sucesso."
        );

        System.out.println(
            cliente.getInfo()
        );

        return true;
    }
}
