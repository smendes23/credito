package br.com.teste.credito.infraestructure.exception;

public class ProducerException extends RuntimeException{
    public ProducerException(String mensagem){
        super("Erro ao publicar mensagem! Causa: "+mensagem);
    }
}
