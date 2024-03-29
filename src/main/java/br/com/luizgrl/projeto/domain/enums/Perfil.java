package br.com.luizgrl.projeto.domain.enums;

public enum Perfil {
    CLIENTE(1,"ROLE_CLIENTE"),
    ADMIN(2,"ROLE_ADMIN");

    private int cod;
    private String desc;

    public int getCod() {
        return cod;
    }

    Perfil(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static Perfil toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Perfil x : Perfil.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id invlaido: "+cod);

    }
}

