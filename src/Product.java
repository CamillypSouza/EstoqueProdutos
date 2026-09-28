public abstract class Product implements Vendavel {

    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0) {
            throw new QuantidadeInvalidaException("Preço não pode ser negativo.");
        }
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("Quantidade não pode ser negativa.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // Cada subclasse decide como calcular (polimorfismo dinâmico)
    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("%s - Preço: R$ %.2f - Quantidade: %d", nome, preco, quantidade);
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Quantidade indisponível em estoque. Solicitado: " + quantidadeDesejada
                            + ", disponível: " + quantidade + " (" + nome + ")");
        }
        quantidade -= quantidadeDesejada;
    }

    // Sobrecarga (polimorfismo estático)
    public void aplicarDesconto(double percentual) {
        preco = preco - (preco * percentual / 100.0);
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        double percentualAplicado = Math.min(percentual, descontoMaximo);
        aplicarDesconto(percentualAplicado);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}