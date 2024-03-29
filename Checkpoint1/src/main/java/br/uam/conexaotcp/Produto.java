package br.uam.conexaotcp;

import javax.persistence.*;

@Entity
@Table(name = "TDS_TB_PRODUTOCS")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "preco")
    private double preco;

    @Column(name = "validade")
    private String validade;

    @Column(name = "tamanho")
    private int tamanho;

    @Column(name = "descricao")
    private String descricao;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Preço: R$ ").append(preco).append("\n");
        sb.append("Validade: ").append(validade).append("\n");
        sb.append("Tamanho: ").append(tamanho).append(" ml\n");
        sb.append("Descrição: ").append(descricao).append("\n");
        return sb.toString();
    }
}
