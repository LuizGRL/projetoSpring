package br.com.luizgrl.projeto.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1,"pendente"),
    QUITADO(2,"quitado"),
    CANCELADO(3,"cacelado");

    private int cod;
    private String mensage;
    
    private EstadoPagamento(int cod, String mensage) {
        this.cod = cod;
        this.mensage = mensage;
    }

    public int getCod() {
        return cod;
    }

    public String getMensage() {
        return mensage;
    }

    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(EstadoPagamento x : EstadoPagamento.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Erro, cod não encontrado");

    }
    
    
    
}
