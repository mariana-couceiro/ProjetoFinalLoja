# Projeto Final Loja

Projeto de consola desenvolvido em Java para a UFCD 0789 Fundamentos de Linguagem Java. O programa simula a gestão de uma loja, separando as operações disponíveis para clientes e funcionários.

## Funcionalidades

### Área do cliente

- Identificação através de um código como `C001`.
- Listagem e pesquisa de produtos (com possibilidade de adicionar percentagem deo cupão de desconto).
- Realização de compras.
- Consulta do próprio histórico de compras.
- Adição de saldo.

### Área do funcionário

- Registo e listagem de produtos.
- Registo e listagem de clientes.
- Prevenção de produtos e contactos duplicados.
- Pesquisa de produtos por nome ou categoria.
- Aplicação de descontos.
- Reposição de stock.
- Listagem de todas as compras.
- Ordenação dos produtos por preço (do menor para o maior).
- Relatório de vendas, produto mais vendido e cliente que mais gastou.

## Dados de demonstração

O programa inicia com cinco produtos e três clientes. Os códigos dos clientes iniciais são:

```text
C001 - Ana Silva
C002 - João Santos
C003 - Maria Costa
```

Os dados são mantidos apenas em memória e regressam aos valores iniciais quando o programa é reiniciado.

## Validações principais

- O nome do cliente deve conter duas palavras iniciadas por maiúsculas.
- O contacto telefónico deve começar por `9` e conter nove algarismos.
- O código do cliente é atribuído apenas quando o registo é aceite.
- Preço, stock e saldo não podem ser negativos.
- Uma compra só é realizada quando existem stock e saldo suficientes.

## Sistema de descontos

O projeto possui dois tipos de desconto:

### Desconto em produtos

O funcionário pode aplicar uma percentagem de desconto a um produto. O preço do produto é atualizado e o novo valor fica disponível para todos os clientes.

- A percentagem introduzida pelo funcionário deve estar entre `0%` e `100%`.
- O valor `0%` mantém o preço atual do produto.
- O valor `100%` reduz o preço do produto para zero.
- Valores inferiores a `0%` ou superiores a `100%` são recusados.

### Cupão de desconto

Durante uma compra, o cliente pode introduzir uma percentagem de desconto:

- O valor deve estar entre `0%` e `100%`.
- O valor `0%` permite continuar a compra sem utilizar desconto.
- Valores inferiores a `0%` ou superiores a `100%` são recusados.
- O cupão aplica-se apenas à compra atual.
- O desconto é calculado sobre o subtotal da compra.
- Se o produto já tiver um desconto aplicado pelo funcionário, o cupão é calculado sobre o preço atualizado do produto.

O histórico apresenta o subtotal, a percentagem do cupão utilizada e o total final pago.