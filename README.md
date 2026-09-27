# Sistema de Estoque de Produtos

Projeto de exercício em Java aplicando conceitos de **Classes Abstratas, Herança, Interfaces, Polimorfismo, Composição e Tratamento de Exceções**.

---

## 📝 Descrição

Este projeto simula o controle de estoque de uma loja, permitindo o cadastro de produtos de tipos diferentes (**comuns** e **perecíveis**), o controle de vendas e o tratamento adequado de situações inválidas, tudo estruturado por meio de uma hierarquia de classes bem definida, sem depender de `if`s soltos.

---

## Conceitos Aplicados

- ✅ **Classes Abstratas**
- ✅ **Herança**
- ✅ **Interfaces**
- ✅ **Polimorfismo** (dinâmico e estático - sobrecarga)
- ✅ **Composição**
- ✅ **Tratamento de Exceções personalizadas**

---

## 🗂️ Estrutura do Projeto

```
📁 src/
 ├── EstoqueException.java
 ├── QuantidadeInvalidaException.java
 ├── ProdutoIndisponivelException.java
 ├── Vendavel.java
 ├── Product.java
 ├── ProdutoComum.java
 ├── ProdutoPerecivel.java
 ├── Estoque.java
 └── EstoqueApp.java
```

---

## Arquitetura das Classes

### ⚠️ Hierarquia de Exceções

```
EstoqueException (base)
 ├── QuantidadeInvalidaException
 └── ProdutoIndisponivelException
```

| Exceção | Quando ocorre |
|---|---|
| `QuantidadeInvalidaException` | Preço ou quantidade negativos ao criar um produto |
| `ProdutoIndisponivelException` | Tentativa de vender mais unidades do que há em estoque |

### Product (classe abstrata)

- Atributos **encapsulados**: `nome`, `preco`, `quantidade`
- Implementa a interface `Vendavel`
- Método abstrato: `calcularValorTotal()`
- Método concreto: `getDescricao()`
- Sobrecarga (**polimorfismo estático**):
  ```java
  void aplicarDesconto(double percentual)
  void aplicarDesconto(double percentual, double descontoMaximo)
  ```

### Subclasses (herança + polimorfismo dinâmico)

| Classe | Regra de cálculo |
|---|---|
| `ProdutoComum` | `preco × quantidade` |
| `ProdutoPerecivel` | Aplica **20% de desconto** automático quando `diasParaVencer <= 3` |

### Interface Vendavel

```java
public interface Vendavel {
    void vender(int quantidadeDesejada) throws ProdutoIndisponivelException;
}
```

### Estoque (composição)

> A classe **não herda** de `Product` — ela **possui** uma lista de produtos.

- `adicionarProduto(Product p)`
- `venderProduto(int indice, int quantidade)`
- `calcularValorTotalEstoque()` — soma polimórfica, cada produto calcula seu próprio valor

### EstoqueApp (main)

Demonstra o funcionamento completo do sistema:
1. Cadastro de produtos comuns e perecíveis
2. Tentativa de cadastro com quantidade inválida → `QuantidadeInvalidaException`
3. Venda válida + tentativa de venda acima do estoque → `ProdutoIndisponivelException`
4. Cálculo e exibição do valor total do estoque

---

## ⚠️ Ordem dos Catches

Ao capturar múltiplas exceções no mesmo `try`, a mais **genérica** deve vir **por último**:

```java
try {
    // ...
} catch (QuantidadeInvalidaException e) {
    System.out.println("Erro: " + e.getMessage());
} catch (ProdutoIndisponivelException e) {
    System.out.println("Erro: " + e.getMessage());
} catch (EstoqueException e) { // sempre por último
    System.out.println("Erro: " + e.getMessage());
}
```

> ❌ Colocar `EstoqueException` antes das específicas torna os catches seguintes **inalcançáveis** (erro de compilação).

---

## ▶️ Como Executar

```bash
# Compilar
javac src/*.java -d bin

# Executar
java -cp bin EstoqueApp
```

---

## 💻 Exemplo de Saída Esperada

```
=== Produtos Cadastrados ===
Notebook - Preço: R$ 3500.00 - Quantidade: 10
Iogurte - Preço: R$ 5.00 - Quantidade: 20 - Vence em: 2 dias

=== Tentativa de cadastro inválido ===
Erro: Quantidade não pode ser negativa.

=== Vendas ===
Venda realizada com sucesso!
Erro: Quantidade indisponível em estoque.

=== Valor Total do Estoque ===
R$ 35080.00
```
---

## 👤 Autor

- camilly Pinto de Souza
Desenvolvido como exercício de fixação para a Primeira Avaliação de Paradigmas de programação.
