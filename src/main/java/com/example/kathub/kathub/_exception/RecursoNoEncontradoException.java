package com.example.kathub.kathub._exception;

public class RecursoNoEncontradoException extends RuntimeException {
    
    private final String codigoError;

    // Constructor básico con mensaje
    public RecursoNoEncontradoException(String mensaje) {
        super(mensaje);
        this.codigoError = "RECURSO_NO_ENCONTRADO"; // identificador para la excepción
    }

    // Constructor con causa (útil para trazar errores encadenados)
    public RecursoNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = "RECURSO_NO_ENCONTRADO";
    }

    public String getCodigoError() {
        return codigoError;
    }
}
