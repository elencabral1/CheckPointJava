package br.uam.conexaotcp;

import javax.persistence.*;

@Entity
@Table(name = "TDS_TB_PRODUTOCS")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Verificar a estratégia de geração de chave primária
	private int id;

	@Column(name = "nome") // Mapeamento para a coluna "nome" na tabela
	private String nome;

	@Column(name = "preco") // Mapeamento para a coluna "preco" na tabela
	private double preco;

	@Column(name = "validade") // Mapeamento para a coluna "validade" na tabela
	private String validade;

	@Column(name = "tamanho") // Mapeamento para a coluna "tamanho" na tabela
	private int tamanho;

	@Column(name = "descricao") // Mapeamento para a coluna "descricao" na tabela
	private String descricao;

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
