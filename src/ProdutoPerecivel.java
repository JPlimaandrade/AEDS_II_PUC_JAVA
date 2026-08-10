
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto {

    private LocalDate dataValidade;

    // builders
    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataValidade) {
        super(desc, precoCusto, margemLucro);
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de validade não pode ser anterior à data atual.");
        }
        this.dataValidade = dataValidade;
    }

    public ProdutoPerecivel(String desc, double precoCusto, LocalDate dataValidade) {
        super(desc, precoCusto);
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de validade não pode ser anterior à data atual.");
        }
        this.dataValidade = dataValidade;
    }
//calcula o valor de venda, com desconto se estiver quase vencendo
    @Override 
    public double valorDeVenda(){ 
        LocalDate hoje = LocalDate.now(); 

        if (hoje.isAfter(dataValidade)){
            throw new IllegalStateException( "Não é possível vender um produto vencido." ); 
        } 

        long diasParaVencer = ChronoUnit.DAYS.between(hoje, dataValidade); 
        double valor = super.valorDeVenda(); 

        if (diasParaVencer <= 7) { 
            valor *= 0.75; 
        } 
        return valor; 
    } 
}

