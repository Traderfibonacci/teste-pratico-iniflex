package funcionario;

import funcionario.Pessoa;

import java.math.BigDecimal;

public class Funcionario  extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, String dataNascimento, double salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = BigDecimal.valueOf(salario);
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}

