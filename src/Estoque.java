import java.util.ArrayList;
import java.util.List;

public class Estoque {

    // Composição: Estoque TEM UMA lista de Product
    private List<Product> produtos = new ArrayList<>();

    public void adicionarProduto(Product p) {
        produtos.add(p);
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        produtos.get(indice).vender(quantidade); // propaga a exceção
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Product p : produtos) {
            total += p.calcularValorTotal(); // polimorfismo em ação
        }
        return total;
    }

    public void listarProdutos() {
        for (int i = 0; i < produtos.size(); i++) {
            System.out.println("[" + i + "] " + produtos.get(i).getDescricao());
        }
    }
}