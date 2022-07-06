package br.com.luizgrl.projeto.domain.enums;

public enum TipoCliente {
    PESSOAFISICA(1, "pessoa fisica"),
    PESSOAJURIDICA(2, "pessoa juridica");

    private int cod;
    private String description;

    public int getCod() {
        return cod;
    }

    public String getDescription() { // SO PRECISA DO GETTER
        return description;
    }

    private TipoCliente(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invlaido: "+cod); // criando 

    }
}
