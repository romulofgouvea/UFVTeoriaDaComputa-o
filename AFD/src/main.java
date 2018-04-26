
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Romulo
 */
public class main {

    private static final Automato automato = new Automato();
    private static Transicao transicao = new Transicao();
    private static Estado estado = new Estado();
    private static Estado estadoDestino = new Estado();
    private static int tamEstados = 0;
    private static String tipoAutomatoN = "";// finaliza a aplicação caso não haja entra de simbolos 
    private static String simbolos = "";// finaliza a aplicação caso não haja entra de simbolos 
    private static int estadoInicial;// finaliza a aplicação caso não haja entra de simbolos 
    private static final List<Integer> estadosFinais = new ArrayList<Integer>();// finaliza a aplicação caso não haja entra de simbolos 
    private static ArrayList<String> saidaAutomato = new ArrayList<String>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TESTE SIMBOLOS DE ENTRADA
        for (String palavra : ManipuladorArquivo.leitorEntrada("entradaAFD")) {
            System.out.println("Lendo Palavra: " + palavra);
            execAutomato(palavra);//EXECULTA O AUTOMATO PRA CADA PALAVRA LIDA
        }
        
        if (saidaAutomato != null) {
            ManipuladorArquivo.escreSaida(tipoAutomatoN, saidaAutomato);
        }
    }

    public static void carregaAutomato(String nome) {

        ArrayList<String> arquivoLido = new ArrayList<String>();
        arquivoLido = ManipuladorArquivo.leitorAutomato(nome);

        String tipoAutomato = arquivoLido.get(0);        //formalismo
        tipoAutomatoN = tipoAutomato;
        String qtdEstados = arquivoLido.get(1);          // quantidade estados
        String[] estados = qtdEstados.split(" ");

        tamEstados = estados.length - 1;

        String simbolosTerminais = arquivoLido.get(2);   // simbolos terminais
        String[] sTerminais = simbolosTerminais.split(" ");

        String estadoInicial = arquivoLido.get(3);       //estado inicial]
        automato.setEstadoInicial(Integer.valueOf(estadoInicial));

        String qtdEstadosTerminais = arquivoLido.get(4); //quais estados sao finais
        String[] estadosTerminais = qtdEstadosTerminais.split(" ");
        for (int i = 1; i < estadosTerminais.length; i++) {
            automato.setEstadoFinal(Integer.valueOf(estadosTerminais[i]));
            estadosFinais.add(Integer.valueOf(estadosTerminais[i]));
        }

        // Definindo qual estado é Inicial e quais são estados Finais 
        // Definindo todas as transições do autômato 
        // (origem, destino, simbolo)
        try {
            for (int i = 5; i < arquivoLido.size(); i++) {
                String tranDados = arquivoLido.get(i);
                String[] arr = tranDados.split(" ");

                int eO = Integer.valueOf(arr[0]);
                int eD = Integer.valueOf(arr[2]);
                char s = arr[1].charAt(0);
                automato.setTransicao(eO, eD, s);

            }
        } catch (NumberFormatException e) {
            System.out.println("Erro: " + e.getMessage());
        }

//        System.out.println("tamEstados: " + automato.getTamanhoEstadoFinal());
//        System.out.println("tipoAutomato: " + tipoAutomato);
//        System.out.println("qtdEstados: " + qtdEstados);
//        System.out.println("simbolosTerminais: " + simbolosTerminais);
//        System.out.println("estadoInicial: " + estadoInicial);
//        System.out.println("qtdEstadosTerminais: " + qtdEstadosTerminais);
    }

    //MENSAGEM DE APRESENTAÇÃO
    public static void mensagemInicial(String simbolo, Automato automato, List<Integer> estadosFinais) {
        System.out.println("\nAFD { w | w possui um número ímpar de a e um número ímpar de b }");
        System.out.println("Verifica a entrada \"" + simbolo + "\"\n");
        System.out.println("Definição dos Estados:\n\tEstado Inicial: " + automato.getEstadoInicial().getName() + " - " + automato.getEstadoInicial().getLabel());
        for (int j = 0; j < automato.getTamanhoEstadoFinal(); j++) {
            System.out.println("\tEstado Final  : " + automato.getEstadoFinal(estadosFinais.get(j)).getName() + " - " + automato.getEstadoFinal(estadosFinais.get(j)).getLabel() + "\n");
        }// for }

    }

    // Exibe a estado final do autômato e indica o reconhecimento ou não da palavrafornecida. 
    public static void mensagemFinalEstadoAutomato(String simbolo, Automato automato, int origem) {
        if (automato.isEstadoFinal(origem)) {
            saidaAutomato.add(simbolo + " aceita");
            System.out.println("\nA entrada \"" + simbolo + "\" foi aceita !!!\n");
        } else {
            saidaAutomato.add(simbolo + " rejeita");
            System.out.println("\nA entrada \"" + simbolo + "\" foi rejeitada !!!\n");
        }
    }// class

    public static void execAutomato(String palavra) {
        carregaAutomato("formalismoAFD");
//        exit(0);
        //Define os estados do automato
        for (int i = 0; i < tamEstados; i++) {
            estado = new Estado();
            estado.setId(i);
            estado.setName("q" + i);
            estado.setLabel("Estado " + i);
            automato.setEstado(estado);
        }

        simbolos = palavra;

//        automato.mensagem();
//        exit(0);
        mensagemInicial(simbolos, automato, estadosFinais);
        //INICIANDO VERIFICAÇÃO
        int i = 0;
        int origem = automato.getEstadoInicial().getId();

        while (i < simbolos.length()) {
            if (!(simbolos.charAt(i) == ' ')) {
                try {
//                System.out.println("Origem1: " + origem);
                    transicao = automato.getTransicao(origem, simbolos.charAt(i));
                    if (transicao != null) {
                        estadoDestino = transicao.getEstadoDestino();
                        origem = estadoDestino.getId();

                        System.out.println("Leu o símbolo \"" + simbolos.charAt(i) + "\" foi para o \"" + estadoDestino.getName() + "\" - " + automato.getEstado(origem).getLabel());
                    } else {
                        System.out.println("Não Existe essa transição do estado q" + origem + " com simbolo " + simbolos.charAt(i));
                        origem = automato.getEstadoInicial().getId();
                        break;
                    }

                } catch (NullPointerException e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                i++;
            } else {
                System.out.println("Este autômato não aceita transições vazias!!!");
                System.exit(0);
            }
        }// end while 

        // Exibe o estado em que o autômato se encontra ao final da leitura da palavra. 
        mensagemFinalEstadoAutomato(simbolos, automato, origem);

    }

}
