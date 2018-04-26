
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Romulo
 */
public class Automato {

    private HashMap<Integer, Estado> estados = new HashMap<Integer, Estado>();
    private HashMap<Integer, Estado> estadosFinais = new HashMap<Integer, Estado>();
    private LinkedHashSet<Transicao> transicoes = new LinkedHashSet<Transicao>();
    private Estado estado = new Estado();
    private int estadoInicial = 0;

    public Estado getEstado(int id) {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        if (estados != null) {
            estados.put(estado.getId(), estado);
        }
    }

    public void setEstadoInicial(int id) {
        this.estadoInicial = id;
    }

    public Estado getEstadoInicial() {
        return estados.get(estadoInicial);
    }

    public void setEstadoFinal(int id) {
        estado.setId(id);
        estado.setName("q" + id);

        if (estados != null) {
            estadosFinais.put(id, estado);
        }
    }

    public Estado getEstadoFinal(int id) {
        return estadosFinais.get(id);
    }

    public void setTransicao(int origem, int destino, char simbolo) {
        Estado estadoOrigem = new Estado();
        Estado estadoDestino = new Estado();

        estadoOrigem.setId(origem);
        estadoOrigem.setName("q" + origem);

        estadoDestino.setId(destino);
        estadoDestino.setName("q" + destino);

        Transicao transicao = new Transicao(estadoOrigem, estadoDestino, simbolo);

//        transicao.setEstadoOrigem(estadoOrigem);
//        transicao.setEstadoDestino(estadoDestino);
//        transicao.setLabel(simbolo);
        transicoes.add(transicao);
    }

    public Transicao getTransicao(int origemEstado, char simbolo) {
        
        ArrayList<Transicao> t = new ArrayList<Transicao>();
        
        Iterator<Transicao> iterator = transicoes.iterator();
        
        while(iterator.hasNext()){
            t.add(iterator.next());
        }
        
        for(Transicao x: t){
            if(origemEstado == x.getEstadoOrigem().getId() && x.getLabel() == simbolo){
//                System.out.println("for: " + x.toString());
                return x;
            }
        }
        return null;
    }

    public int getTamanhoEstadoFinal() {
        return estadosFinais.size();
    }

    public boolean isEstadoInicial(int id) {
        return false;
    }

    public boolean isEstadoFinal(int id) {
        if (estadosFinais.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public void mensagem() {
        Set<Integer> mapEstados = estados.keySet();
        Set<Integer> mapEstadosFinais = estadosFinais.keySet();

        System.out.println("mapEstados: " + mapEstados);

        for (Integer i : mapEstados) {
            System.out.println(estados.get(i));
        }
        System.out.println("mapEstadosFinais: " + mapEstadosFinais);
        for (Integer i : mapEstadosFinais) {
            System.out.println(estadosFinais.get(i));
        }

        System.out.println("Transições: \n");
        Iterator<Transicao> mapTransicao = transicoes.iterator();
        while (mapTransicao.hasNext()) {
            System.out.println(mapTransicao.next());
        }

    }

}
