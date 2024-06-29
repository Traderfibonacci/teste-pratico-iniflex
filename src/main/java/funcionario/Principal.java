package funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        //lista de funcionarios
        System.out.println("-----------------");
        funcionarios.add(new Funcionario("Maria", "18/10/2000", 2009.44, "Operador"));
        funcionarios.add(new Funcionario("João", "12/05/1990", 2284.38, "Operador"));
        funcionarios.add(new Funcionario("Caio", "02/05/1961", 9836.14, "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", "14/10/1988", 19119.88, "Diretor"));
        funcionarios.add(new Funcionario("Alice", "05/01/1995", 2234.68, "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", "19/11/1999", 1582.72, "Operador"));
        funcionarios.add(new Funcionario("Arthur", "31/03/1993", 4071.84, "Contador"));
        funcionarios.add(new Funcionario("Laura", "08/07/1994", 3017.45, "Gerente"));
        funcionarios.add(new Funcionario("Helóisa", "24/05/2003", 1606.85, "Eletricista"));
        funcionarios.add(new Funcionario("Helena", "02/09/1996", 2799.93, "Gerente"));

        // remove o funcionário "João"
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getNome().equals("João")) {
                funcionarios.remove(i);
                break;
            }
        }



        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //  todos os funcionários
        for (Funcionario f : funcionarios) {
            System.out.println("Nome: " + f.getNome());
            System.out.println("Data Nascimento: " + f.getDataNascimento().format(formatter));
            System.out.println("Salário: " + f.getSalario().setScale(2).toString().replace(".", ","));
            System.out.println("Função: " + f.getFuncao());
            System.out.println();
        }

        // aumentar salário em 10%
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10))));

        // imprimir os salários atualizados
        System.out.println("\nSalários após aumento de 10%:");
        funcionarios.forEach(f -> System.out.println(f.getNome() + ": R$" +
                f.getSalario().setScale(2, RoundingMode.HALF_UP).toString().replace(".", ",")));

        System.out.println();

        // agrupando por funcao
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // imprimir funcionários agrupados por funcao
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("  - " + f.getNome()));
            System.out.println();
        });
        // colaboradores com aniversário em outubro e dezembro
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

            // imprimir aniversariantes em outubro e dezembro
            aniversariantes.forEach(f -> System.out.println("aniversariantes de outubro e dezembro: " + f.getNome()));

            System.out.println();

            // func mais velho da lista

            Funcionario maisVelho = null;
            int idadeMaisVelho = 0;

            for (Funcionario f : funcionarios) {
                int idade = LocalDate.now().getYear() - f.getDataNascimento().getYear();
                if (idade > idadeMaisVelho) {
                    idadeMaisVelho = idade;
                    maisVelho = f;
                }
            }
            if (maisVelho != null) {
                System.out.println
                        ("Funcionario Mais velho: "
                                + maisVelho.getNome() + ", idade: "
                                + idadeMaisVelho + " anos");
            }
             // ordenando por nome
            List<Funcionario> funcionariosOrdenados = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

            // imprimindo a lista ordenada
             System.out.println("\n Funcionários por ordem alfabética: ");
             funcionariosOrdenados.forEach(f -> System.out.println(f.getNome()));

             // calculando o total dos salários
            BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            // imprimindo o total dos salários
            System.out.println("\n Total dos salários: R$" + totalSalarios.setScale(2, RoundingMode.HALF_UP).toString().replace(".", ","));

            // salário mínimo
             BigDecimal salarioMinimo = new BigDecimal("1212.00");

            // calculando, quantos salários mínimos ganha cada um dos funcionarios
             System.out.println("\n salarios mínimos por funcionário:");
             funcionarios.forEach(f -> {BigDecimal numeroSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + numeroSalariosMinimos + " salários mínimos");
        });


    }
}
