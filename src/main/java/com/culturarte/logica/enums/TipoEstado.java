package com.culturarte.logica.enums;

public enum TipoEstado {
    INGRESADA,
    PUBLICADA,
    ENFINANCIACION{
        @Override
    public String toString(){
    return "EN FINANCIACION";
    }
    },
    FINANCIADA,
    NOFINANCIADA{
        @Override
    public String toString(){
    return "NO FINANCIADA";
    }
    },
    CANCELADA;
}
