void main() {
    Estoque estoque = new Estoque();

    // 1) Cadastro de produtos válidos
    try {
        estoque.adicionarProduto(new ProdutoComum("Notebook", 3500.00, 10));
        estoque.adicionarProduto(new ProdutoComum("Mouse", 80.00, 25));
        estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 5.00, 20, 2));   // <= 3 dias
        estoque.adicionarProduto(new ProdutoPerecivel("Queijo", 30.00, 10, 10));
    } catch (QuantidadeInvalidaException e) {
        IO.println("Erro ao cadastrar: " + e.getMessage());
    }

    IO.println("=== Produtos Cadastrados ===");
    estoque.listarProdutos();
    System.out.printf("Valor total do estoque: R$ %.2f%n", estoque.calcularValorTotalEstoque());

    // 2) Cadastro inválido (quantidade negativa)
    IO.println("\n=== Tentativa de cadastro inválido ===");
    try {
        estoque.adicionarProduto(new ProdutoComum("Caneta", 2.50, -5));
    } catch (QuantidadeInvalidaException e) {
        IO.println("Erro: " + e.getMessage());
    }

    // 3) Vendas
    IO.println("\n=== Vendas ===");
    try {
        estoque.venderProduto(0, 3);
        IO.println("Venda realizada com sucesso! (3 Notebooks)");

        estoque.adicionarProduto(new ProdutoComum("Teclado", -10, 5)); // lança QuantidadeInvalidaException
    } catch (QuantidadeInvalidaException e) {
        IO.println("Quantidade inválida: " + e.getMessage());
    } catch (ProdutoIndisponivelException e) {
        IO.println("Erro: " + e.getMessage());
    } catch (EstoqueException e) {
        IO.println("Erro de estoque: " + e.getMessage());
    }

    // 4) Valor total final
    IO.println("\n=== Estoque Após as Vendas ===");
    estoque.listarProdutos();
    System.out.printf("Valor total do estoque: R$ %.2f%n", estoque.calcularValorTotalEstoque());
}