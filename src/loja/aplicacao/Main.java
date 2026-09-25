package loja.aplicacao;

import java.util.Scanner;

import loja.modelos.Cliente;
import loja.modelos.Funcionario;
import loja.modelos.Produto;
import loja.sistema.Loja;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        Funcionario funcionario =
                new Funcionario(
                    "Funcionário Principal",
                    "900000000",
                    "F001"
                );

        int opcao;

        do {
            mostrarMenuPrincipal();

            opcao = lerInteiro(
                scanner,
                "Escolha uma opção: "
            );

            switch (opcao) {

                case 1:
                    entrarAreaCliente(
                        scanner,
                        loja
                    );
                    break;

                case 2:
                    menuFuncionario(
                        scanner,
                        loja,
                        funcionario
                    );
                    break;

                case 0:
                    System.out.println(
                        "Programa terminado."
                    );
                    break;

                default:
                    System.out.println(
                        "Opção inválida."
                    );
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void mostrarMenuPrincipal() {

        System.out.println(
            "\n=============================="
        );
        System.out.println(
            "       SISTEMA DA LOJA"
        );
        System.out.println(
            "=============================="
        );

        System.out.println("1 - Área do cliente");
        System.out.println("2 - Área do funcionário");
        System.out.println("0 - Sair");
    }

    public static void entrarAreaCliente(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- IDENTIFICAÇÃO DO CLIENTE ---"
        );

        System.out.print(
            "Código de cliente (ex. c001): "
        );

        String codigoCliente =
                scanner.nextLine()
                .trim()
                .toUpperCase();

        if (!codigoCliente.matches("C\\d{3,}")) {
            System.out.println(
                "Código de cliente inválido."
            );

            return;
        }

        Cliente cliente =
                loja.procurarClientePorCodigo(
                    codigoCliente
                );

        if (cliente == null) {
            System.out.println(
                "Não existe nenhum cliente "
                + "com esse código."
            );

            return;
        }

        menuCliente(
            scanner,
            loja,
            cliente
        );
    }

    public static void menuCliente(
        Scanner scanner,
        Loja loja,
        Cliente cliente
    ) {

        int opcao;

        do {
            System.out.println(
                "\n=============================="
            );
            System.out.println(
                "       ÁREA DO CLIENTE"
            );
            System.out.println(
                "=============================="
            );

            System.out.println(
                "Código: "
                + cliente.getCodigoCliente()
            );

            System.out.println(
                "Cliente: "
                + cliente.getNome()
            );

            System.out.println(
                "Saldo: "
                + String.format(
                    "%.2f",
                    cliente.getSaldo()
                )
                + " €"
            );

            System.out.println("1 - Listar produtos");
            System.out.println("2 - Realizar compra");
            System.out.println(
                "3 - Consultar o meu histórico"
            );
            System.out.println("4 - Adicionar saldo");
            System.out.println(
                "0 - Voltar ao menu principal"
            );

            opcao = lerInteiro(
                scanner,
                "Escolha uma opção: "
            );

            switch (opcao) {

                case 1:
                    loja.listarProdutos();
                    break;

                case 2:
                    realizarCompraCliente(
                        scanner,
                        loja,
                        cliente
                    );
                    break;

                case 3:
                    loja.consultarHistorico(
                        cliente
                    );
                    break;

                case 4:
                    adicionarSaldoCliente(
                        scanner,
                        loja,
                        cliente
                    );
                    break;

                case 0:
                    System.out.println(
                        "A sair da área do cliente."
                    );
                    break;

                default:
                    System.out.println(
                        "Opção inválida."
                    );
            }

        } while (opcao != 0);
    }

    public static void menuFuncionario(
        Scanner scanner,
        Loja loja,
        Funcionario funcionario
    ) {

        int opcao;

        do {
            System.out.println(
                "\n=============================="
            );
            System.out.println(
                "     ÁREA DO FUNCIONÁRIO"
            );
            System.out.println(
                "=============================="
            );

            System.out.println(
                "Funcionário: "
                + funcionario.getNome()
            );

            System.out.println(
                "Número: "
                + funcionario.getNumeroFuncionario()
            );

            System.out.println("1 - Registar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Registar cliente");
            System.out.println("4 - Listar clientes");
            System.out.println("5 - Aplicar desconto");
            System.out.println("6 - Ver relatório");
            System.out.println(
                "7 - Ordenar produtos por preço"
            );
            System.out.println("8 - Repor stock");
            System.out.println(
                "9 - Listar todas as compras"
            );
            System.out.println(
                "0 - Voltar ao menu principal"
            );

            opcao = lerInteiro(
                scanner,
                "Escolha uma opção: "
            );

            switch (opcao) {

                case 1:
                    registarProduto(
                        scanner,
                        loja
                    );
                    break;

                case 2:
                    loja.listarProdutos();
                    break;

                case 3:
                    registarCliente(
                        scanner,
                        loja
                    );
                    break;

                case 4:
                    loja.listarClientes();
                    break;

                case 5:
                    aplicarDesconto(
                        scanner,
                        loja
                    );
                    break;

                case 6:
                    loja.mostrarRelatorio();
                    break;

                case 7:
                    loja.ordenarProdutosPorPreco();
                    break;

                case 8:
                    reporStock(
                        scanner,
                        loja
                    );
                    break;

                case 9:
                    loja.listarCompras();
                    break;

                case 0:
                    System.out.println(
                        "A sair da área do funcionário."
                    );
                    break;

                default:
                    System.out.println(
                        "Opção inválida."
                    );
            }

        } while (opcao != 0);
    }

    public static void registarProduto(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- REGISTAR PRODUTO ---"
        );

        String nome;

        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();

            if (nome.isBlank()) {
                System.out.println(
                    "O nome é obrigatório."
                );
            }

        } while (nome.isBlank());

        double preco;

        do {
            preco = lerDouble(
                scanner,
                "Preço: "
            );

            if (preco < 0) {
                System.out.println(
                    "O preço não pode ser negativo."
                );
            }

        } while (preco < 0);

        int quantidade;

        do {
            quantidade = lerInteiro(
                scanner,
                "Quantidade em stock: "
            );

            if (quantidade < 0) {
                System.out.println(
                    "O stock não pode ser negativo."
                );
            }

        } while (quantidade < 0);

        String categoria;

        do {
            System.out.print("Categoria: ");

            categoria =
                    scanner.nextLine().trim();

            if (categoria.isBlank()) {
                System.out.println(
                    "A categoria é obrigatória."
                );
            }

        } while (categoria.isBlank());

        Produto produto = new Produto(
            nome,
            preco,
            quantidade,
            categoria
        );

        loja.registarProduto(produto);
    }

    public static void registarCliente(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- REGISTAR CLIENTE ---"
        );

        String nome;

        do {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();

            if (!nomeValido(nome)) {
                System.out.println(
                    "Nome inválido. Introduza duas "
                    + "palavras, ambas começadas "
                    + "por letra maiúscula."
                );
            }

        } while (!nomeValido(nome));

        String contacto;

        do {
            System.out.print(
                "Contacto telefónico: "
            );

            contacto =
                    scanner.nextLine().trim();

            if (!contacto.matches("9\\d{8}")) {
                System.out.println(
                    "Contacto telefónico inválido. "
                    + "Deve começar por 9 e ter "
                    + "exatamente 9 algarismos."
                );
            }

        } while (!contacto.matches("9\\d{8}"));

        double saldo;

        do {
            saldo = lerDouble(
                scanner,
                "Saldo inicial: "
            );

            if (saldo < 0) {
                System.out.println(
                    "O saldo não pode ser negativo."
                );
            }

        } while (saldo < 0);

        Cliente cliente = new Cliente(
            nome,
            contacto,
            saldo
        );

        loja.registarCliente(cliente);
    }

    public static boolean nomeValido(
        String nome
    ) {

        String formatoNome =
                "[A-ZÁÀÂÃÉÊÍÓÔÕÚÇ]"
                + "[a-záàâãéêíóôõúç]+"
                + "\\s+"
                + "[A-ZÁÀÂÃÉÊÍÓÔÕÚÇ]"
                + "[a-záàâãéêíóôõúç]+";

        return nome.matches(formatoNome);
    }

    public static void realizarCompraCliente(
        Scanner scanner,
        Loja loja,
        Cliente cliente
    ) {

        System.out.println(
            "\n--- REALIZAR COMPRA ---"
        );

        loja.listarProdutos();

        int numeroProduto = lerInteiro(
            scanner,
            "Número do produto: "
        );

        Produto produto = loja.getProduto(
            numeroProduto - 1
        );

        if (produto == null) {
            System.out.println(
                "Produto inválido."
            );

            return;
        }

        int quantidade = lerInteiro(
            scanner,
            "Quantidade: "
        );

        loja.realizarCompra(
            cliente,
            produto,
            quantidade
        );
    }

    public static void adicionarSaldoCliente(
        Scanner scanner,
        Loja loja,
        Cliente cliente
    ) {

        System.out.println(
            "\n--- ADICIONAR SALDO ---"
        );

        double valor = lerDouble(
            scanner,
            "Valor a adicionar: "
        );

        loja.adicionarSaldo(
            cliente,
            valor
        );
    }

    public static void aplicarDesconto(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- APLICAR DESCONTO ---"
        );

        loja.listarProdutos();

        int numeroProduto = lerInteiro(
            scanner,
            "Número do produto: "
        );

        Produto produto = loja.getProduto(
            numeroProduto - 1
        );

        if (produto == null) {
            System.out.println(
                "Produto inválido."
            );

            return;
        }

        double percentagem = lerDouble(
            scanner,
            "Percentagem de desconto: "
        );

        if (
            percentagem <= 0
            || percentagem > 100
        ) {
            System.out.println(
                "A percentagem deve estar "
                + "entre 1 e 100."
            );

            return;
        }

        produto.aplicarDesconto(
            percentagem
        );

        System.out.println(
            "Desconto aplicado com sucesso."
        );

        System.out.println(
            produto.getInfo()
        );
    }

    public static void reporStock(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- REPOR STOCK ---"
        );

        loja.listarProdutos();

        int numeroProduto = lerInteiro(
            scanner,
            "Número do produto: "
        );

        Produto produto = loja.getProduto(
            numeroProduto - 1
        );

        if (produto == null) {
            System.out.println(
                "Produto inválido."
            );

            return;
        }

        int quantidade = lerInteiro(
            scanner,
            "Quantidade a adicionar: "
        );

        loja.reporStock(
            produto,
            quantidade
        );
    }

    public static int lerInteiro(
        Scanner scanner,
        String mensagem
    ) {

        while (true) {
            System.out.print(mensagem);

            String valor =
                    scanner.nextLine().trim();

            try {
                return Integer.parseInt(valor);

            } catch (NumberFormatException erro) {
                System.out.println(
                    "Introduza um número "
                    + "inteiro válido."
                );
            }
        }
    }

    public static double lerDouble(
        Scanner scanner,
        String mensagem
    ) {

        while (true) {
            System.out.print(mensagem);

            String valor = scanner
                    .nextLine()
                    .trim()
                    .replace(",", ".");

            try {
                return Double.parseDouble(valor);

            } catch (NumberFormatException erro) {
                System.out.println(
                    "Introduza um número válido."
                );
            }
        }
    }
}
