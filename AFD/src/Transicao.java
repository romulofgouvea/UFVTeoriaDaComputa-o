/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Romulo
 */
public class Transicao {

    private Estado estadoOrigem;
    private Estado estadoDestino;
    private char label;

    public Transicao() {
    }

    public Transicao(Estado estadoOrigem, Estado estadoDestino, char label) {
        //super();
        this.estadoOrigem = estadoOrigem;
        this.estadoDestino = estadoDestino;
        this.label = label;
    }

    public Estado getEstadoOrigem() {
        return estadoOrigem;
    }

    public Estado getEstadoDestino() {
        return estadoDestino;
    }

    public char getLabel() {
        return label;
    }

    public void setEstadoOrigem(Estado estadoOrigem) {
        this.estadoOrigem = estadoOrigem;
    }

    public void setEstadoDestino(Estado estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public String toString() {
        return "Origem: " + estadoOrigem + " -> Destino:" + estadoDestino + " [label=" + label + "]";
    }
}
