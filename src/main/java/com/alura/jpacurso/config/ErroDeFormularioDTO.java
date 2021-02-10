package com.alura.jpacurso.config;

public class ErroDeFormularioDTO {
    public String campo;
    public String erro;

    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }



    public String getErro() {
        return erro;
    }


    
}
