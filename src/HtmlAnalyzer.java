import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlAnalyzer {

    public static boolean verificaLimites(int i, String stringOriginal) {
        if (stringOriginal == null || stringOriginal.length() < 2) {
            return false; // Se a string for nula ou tiver menos de 2 caracteres, não é possível acessar i-1 e i+1.
        }
        return (i - 1) >= 0 && (i + 1) < stringOriginal.length();
    }

    public static void mostraVetorInt (int[] vetor){
        for (int i=0; i< vetor.length; i++){
            if (vetor[i] != 0){
                System.out.println("Posição[" + i + "] = " + vetor[i]);
            }
        }//for
    }

    public static void mostraVetorString (String[] vetor){
        for (int i=0; i< vetor.length; i++){
            if (!(vetor[i].isEmpty())) {
                System.out.println("Vetor[" + i + "]: " + vetor[i]);
            }
        }//for
    }

    public static String removeEspacos(String stringOriginal) {
        if (stringOriginal == null) {
            return null;
        }
        stringOriginal = stringOriginal.replaceAll("[\\n\\r\\t]+", "");
        stringOriginal = stringOriginal.replaceAll(">\\s+<", "><") // Remove espaços entre as tags
                .replaceAll("\\s+", " ")   // Reduz múltiplos espaços para um único
                .trim();
        return stringOriginal;
    }

    public static int[] encontraPosicoes(String stringOriginal) {
        int inicioTexto = 0;
        int fimTexto = 0;
        int [] posicoes = new int[stringOriginal.length()];
        int indicePosicoes = 0;
        for (int i = 0; i < stringOriginal.length(); i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE
                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE
                    if (stringOriginal.charAt(i - 1) != '>') {
                        fimTexto = i;
                        posicoes[indicePosicoes++] = fimTexto;
                    }//FIM DE TEXTO
                }
                if (stringOriginal.charAt(i) == '>') {                      // SE FOR O SINAL DE MAIOR QUE
                    if (stringOriginal.charAt(i + 1) != '<') {              // OUTRA ABERTURA DE TAG EM SEQUENCIA
                        inicioTexto = i + 1;
                        posicoes[indicePosicoes++] = inicioTexto;
                    }//INICIO DE TEXTO
                }
            }
        } //for
    return posicoes;
    }//metodo encontraPosicoes

    public static int contaAberturas(String stringOriginal, int posInicio) {
        int abertura = 0;
        for (int i = 0; i < posInicio; i++) {                               //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE
                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE
                    if (stringOriginal.charAt(i + 1) != '/') {     //SE O PROXIMO NAOOOOO FOR BARRA, QUER DIZER QUE É TAG ABERTURA
                        abertura++;                                         //ABERTURA DE TAG
                    }
                }
            }
        }
        return abertura;
    }//metodo contaAberturas

    private static int contaFechamentos(String stringOriginal, int posInicio) {
        int fechamento = 0;
        for (int i = 0; i < posInicio; i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE
                if (stringOriginal.charAt(i+1) == '/'){     //FECHAMENTO DE TAG
                    fechamento++;
                }
           }
        } //for
        return fechamento;
    }

    public static ArrayList<Trecho> encontraTrechos (String stringX, int[] posicoes){
        ArrayList<Trecho> trechosList = new ArrayList<>(); // este metodo preenche um arraylist
        int i = 0;
        while (posicoes[i] != 0){
            String recorte = stringX.substring(posicoes[i], posicoes[i+1]);
            Trecho trecho = new Trecho(recorte, posicoes[i], posicoes[i+1]);
            trechosList.add(trecho);
            i += 2;
        }
        return trechosList;
    }

    public static void determinaNivel (String stringOriginal, ArrayList<Trecho> trechosList){
        // determinar o nível significa contar quantas tags de abertura existem até a posição de início daquele texto
        // nivel = num_aberturas - num_fechamentos + 1
        int num_aberturas = 0;
        int num_fechamentos = 0;
        for (Trecho t : trechosList) {
            int inicio = t.getInicio();
            num_aberturas = contaAberturas(stringOriginal, inicio);
            num_fechamentos = contaFechamentos(stringOriginal, inicio);
            t.setNivel(num_aberturas - num_fechamentos + 1); // +1 por conta da tag de abertura inicial <html>que não
            // aparece pois ultrapassa os limites
            t.setTagsDeAbertura(num_aberturas);
            t.setTagsDeFechamento(num_fechamentos);
        }
    }

    public static Trecho comparaNiveis (ArrayList<Trecho> trechosList){
        Trecho trechoMaisProfundo = new Trecho("", 0, 0);
        int nivel = 0;
        for (Trecho t : trechosList) {
            if (t.getNivel() > nivel){
                nivel = t.getNivel();
                trechoMaisProfundo = t;
            }
        }
        return trechoMaisProfundo;
    }

    public static void main(String[] args) {

        String saida = null;

        try {
            // Definindo a URL
            String stringURL = "http://hiring.axreng.com/internship/example1.html";
            URL url = new URL(stringURL);

            // Estabelecendo a conexão
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            // Lendo o conteúdo da página
            BufferedReader ent = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String entrada;
            StringBuilder conteudo = new StringBuilder();

            while ((entrada = ent.readLine()) != null) {
                conteudo.append(entrada);
            }

            // Fecha o reader
            ent.close();

            // Agora o código HTML está na string "conteudo"
            String conteudoHtml = conteudo.toString();

            // Copiando o conteúdo para outra variável
            saida = conteudoHtml;

            // Exibindo as duas variáveis para garantir que o conteúdo foi copiado
            System.out.println("Conteúdo HTML original:");
            System.out.println(conteudoHtml);
            System.out.println("\nConteúdo copiado para outra variável:");
            System.out.println(saida);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("URL connection error.");
        }


        // Passando a String que será analisada.
        //String stringOriginal = "<html><head><title>Exemplo Completo</title></head><body><h1>Bem-vindo!</h1><p>Este
        // é um parágrafo de exemplo.</p><ul><li>Item 1</li><li>Item 2</li><li>Item 3</li></ul><table><tr><th>Nome</th><th>Idade</th></tr><tr><td>Ana</td><td>25</td></tr><tr><td>Lucas</td><td>30</td></tr></table><button>Clique Aqui</button></body></html>";

        String stringOriginal = saida;

        String stringLimpa = removeEspacos(stringOriginal);
        System.out.println(" ");
        System.out.println("String pos limpeza: " + stringLimpa);

        int abertura = 0;
        int fechamento = 0;
        int [] posicoes;

        // Determinando em quais posições se iniciam e concluem-se os textos.
        posicoes = encontraPosicoes(stringLimpa);

        // ArrayList que vai conter os objetos do tipo Trecho
        ArrayList<Trecho> trechosList = encontraTrechos(stringLimpa, posicoes);

        //Calculando o nivel e dando set.Nivel
        System.out.println(" ");
        determinaNivel(stringLimpa, trechosList);

        System.out.println(" ");
        System.out.println("***** Mostrando objetos da classe Trecho: *****");
        for (Trecho t : trechosList) {
            if (!t.texto.isEmpty()) {
                System.out.println(t);
            }
        }

        System.out.println(" ");
        System.out.println(" O texto de nível mais profundo é ==> ");
        System.out.println(comparaNiveis(trechosList));

    }// Metodo main
}// Classe Main
