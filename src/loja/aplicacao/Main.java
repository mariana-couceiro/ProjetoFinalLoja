package loja.aplicacao;

import java.util.Scanner;

import loja.modelos.Cliente;
import loja.modelos.Produto;
import loja.sistema.Loja;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Loja loja = new Loja();

        int opcao;

        do {
            mostrarMenu();

            opcao = lerInteiro(
                scanner,
                "Escolha uma opção: "
            );

            switch (opcao) {

                case 1:
                    registarProduto(scanner, loja);
                    break;

                case 2:
                    loja.listarProdutos();
                    break;

                case 3:
                    registarCliente(scanner, loja);
                    break;

                case 4:
                    loja.listarClientes();
                    break;

                case 5:
                    realizarCompra(scanner, loja);
                    break;

                case 6:
                    consultarHistorico(scanner, loja);
                    break;

                case 7:
                    aplicarDesconto(scanner, loja);
                    break;

                case 8:
                    loja.mostrarRelatorio();
                    break;

                case 9:
                    loja.ordenarProdutosPorPreco();
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

    public static void mostrarMenu() {

        System.out.println(
            "\n=============================="
        );

        System.out.println(
            "    SISTEMA DE GESTÃO DE LOJA"
        );

        System.out.println(
            "=============================="
        );

        System.out.println("1 - Registar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Registar cliente");
        System.out.println("4 - Listar clientes");
        System.out.println("5 - Realizar compra");
        System.out.println("6 - Consultar histórico");
        System.out.println("7 - Aplicar desconto");
        System.out.println("8 - Ver relatório");
        System.out.println(
            "9 - Ordenar produtos por preço"
        );
        System.out.println("0 - Sair");
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
            categoria = scanner.nextLine().trim();

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
                    "Nome inválido. Introduza duas palavras, "
                    + "ambas começadas por letra maiúscula."
                );
            }

        } while (!nomeValido(nome));

        String contacto;

        do {
            System.out.print(
                "Contacto telefónico: "
            );

            contacto = scanner.nextLine().trim();

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

    public static void realizarCompra(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- REALIZAR COMPRA ---"
        );

        loja.listarClientes();

        int numeroCliente = lerInteiro(
            scanner,
            "Número do cliente: "
        );

        Cliente cliente = loja.getCliente(
            numeroCliente - 1
        );

        if (cliente == null) {
            System.out.println(
                "Cliente inválido."
            );

            return;
        }

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

    public static void consultarHistorico(
        Scanner scanner,
        Loja loja
    ) {

        System.out.println(
            "\n--- CONSULTAR HISTÓRICO ---"
        );

        loja.listarClientes();

        int numeroCliente = lerInteiro(
            scanner,
            "Número do cliente: "
        );

        Cliente cliente = loja.getCliente(
            numeroCliente - 1
        );

        if (cliente == null) {
            System.out.println(
                "Cliente inválido."
            );

            return;
        }

        loja.consultarHistorico(cliente);
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

    public static int lerInteiro(
        Scanner scanner,
        String mensagem
    ) {

        while (true) {
            System.out.print(mensagem);

            String valor = scanner
                    .nextLine()
                    .trim();

            try {
                return Integer.parseInt(
                    valor
                );

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
                return Double.parseDouble(
                    valor
                );

            } catch (NumberFormatException erro) {
                System.out.println(
                    "Introduza um número válido."
                );
            }
        }
    }
}