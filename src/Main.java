import java.util.ArrayList;
import java.util.List;

public class Main {

    public static boolean verificaLimites(int i, String stringOriginal) {
        if (stringOriginal == null || stringOriginal.length() < 2) {
            return false; // Se a string for nula ou tiver menos de 2 caracteres, não é possível acessar i-1 e i+1.
        }
        return (i - 1) >= 0 && (i + 1) < stringOriginal.length();
    }

    public static String[] encontraTextos (String stringX, int[] posicoes){
        String[] textos = new String[posicoes.length/2]; //==> em []posicoes, indices impar = INICIOS de texto, indices
        // pares = FINS de texto

            for (int k=0; k<posicoes.length-1; k+=2){
                textos[k/2] = stringX.substring(posicoes[k], posicoes[(k+1)]);
            }//for

        return textos;
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

    public static int[] encontraPosicoes(String stringOriginal) {
        int abertura = 0;
        int fechamento = 0;
        int inicioTexto = 0;
        int fimTexto = 0;
        int [] posicoes = new int[stringOriginal.length()];
        int indicePosicoes = 0;

        for (int i = 0; i < stringOriginal.length(); i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE
                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE

                    if (stringOriginal.charAt(i - 1) != '>' /*&& stringOriginal.charAt(i - 1) != ' '*/) {
                        fimTexto = i - 1;
                        //System.out.println("Aqui tem fim de texto. Posição:" + fimTexto);
                        posicoes[indicePosicoes++] = fimTexto;
                    }//FIM DE TEXTO

                    if (stringOriginal.charAt(i+1) == '/'){     //FECHAMENTO DE TAG
                        fechamento++;
                        //System.out.println("Aqui tem tag de fechamento. Posição:" + i + " / Fechamento:" +
                        // fechamento);
                    } else {                    //SE O PROXIMO NAOOOOO FOR BARRA, QUER DIZER QUE É TAG ABERTURA
                        abertura++;
                        //System.out.println("Aqui tem tag de abertura. Posição:" + i + " / Abertura:" + abertura);
                        //ABERTURA DE TAG
                    }

                    i++;

                } //<<<<<<<<<<<<<<<<<<<<<<


                if (stringOriginal.charAt(i) == '>') {                      // SE FOR O SINAL DE MAIOR QUE

                    if (stringOriginal.charAt(i + 1) != '<') {              // OUTRA ABERTURA DE TAG EM SEQUENCIA
                        inicioTexto = i + 1;
                        //System.out.println("Aqui tem inicio de texto. Posição:" + inicioTexto);
                        i++;
                        posicoes[indicePosicoes++] = inicioTexto;
                                            }                                                           //INICIO DE TEXTO

                } //>>>>>>>>>>>>>>>>>>>>>>


            } else if (stringOriginal.charAt(i) == '<') {
                abertura++;
                //System.out.println("Aqui tem tag de abertura. Posição:" + i + " / Abertura:" + abertura);
                //primeira tag de abertura
            } else if (stringOriginal.charAt(i) == '>'){
                //System.out.println("Fim do código. Posição:" + i);     //ultimo caracter
            }
        } //for
return posicoes;
    }//metodo encontraPosicoes

    public static int contaAberturas(String stringOriginal) {
        int abertura = 0;
        int fechamento = 0;

        for (int i = 0; i < stringOriginal.length(); i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE

                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE
                    if (stringOriginal.charAt(i+1) == '/'){     //FECHAMENTO DE TAG
                        fechamento++;
                        System.out.println("Aqui tem tag de fechamento. Posição:" + i + " / Fechamento:" + fechamento);
                    } else {                    //SE O PROXIMO NAOOOOO FOR BARRA, QUER DIZER QUE É TAG ABERTURA
                        abertura++;
                        System.out.println("Aqui tem tag de abertura. Posição:" + i + " / Abertura:" + abertura);
                        //ABERTURA DE TAG
                    }

                    i++;

                } //<<<<<<<<<<<<<<<<<<<<<<

            } else if (stringOriginal.charAt(i) == '<') {
                abertura++;
                System.out.println("Aqui tem tag de abertura. Posição:" + i + " / Abertura:" + abertura);
                //primeira tag de abertura
            } else if (stringOriginal.charAt(i) == '>'){
                System.out.println("Fim do código. Posição:" + i);     //ultimo caracter
            }
        } //for
        return abertura;
    }//metodo contaAberturas

    public static int contaFechamentos(String stringOriginal) {
        int fechamento = 0;
        System.out.println("Contagem das tags de fechamento ======>");
        for (int i = 0; i < stringOriginal.length(); i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE

                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE
                    if (stringOriginal.charAt(i+1) == '/') {     //FECHAMENTO DE TAG
                        fechamento++;
                        System.out.println("Aqui tem tag de fechamento. Posição:" + i + " / Fechamento:" + fechamento);
                        i++;
                    }
                } //<<<<<<<<<<<<<<<<<<<<<<

            } else {
                System.out.println("Ultrapassagem de limite, verifique e tente novamente. ==>" + i);
            }
        } //for
        return fechamento;
    }//metodo contaFechamentos

    public static ArrayList<Trecho> encontraTrechos (String stringX, int[] posicoes){
        ArrayList<Trecho> trechosList = new ArrayList<>(); // este metodo preenche um arraylist
        //String[] textos = encontraTextos(stringX, posicoes);

        for (int i = 0; i < posicoes.length-1; i+=2) {
            String recorte = stringX.substring(posicoes[i], posicoes[i+1]);
            //System.out.println("Recorte: " + recorte);
            Trecho trecho = new Trecho(recorte, posicoes[i], posicoes[i+1]);
            trechosList.add(trecho);
        }

        return trechosList;
    }

    public static int determinaNivel (String stringOriginal, ArrayList<Trecho> trechosList){
        int nivel = 1;
        int num_aberturas = 0;

        for (Trecho t : trechosList) {
            int inicio = t.getInicio();
            int fim = t.getFim();
            for (int i = 0; i< inicio; i++){
                num_aberturas = contaAberturas(stringOriginal);
                System.out.println("Aberturas: " + num_aberturas);

            }
        }
        return num_aberturas;
    }

    public static void main(String[] args) {

//        String tagAbertura = "<";
//        String tagFechamentoTotal = "</";
//        String inicioDeTexto = ">";
//        String fimDeTexto = "<";

        //String stringOriginal = "<html> <head> coisas </head> </html>"; //===> só que a "string original" é um código
        // html!
//        String stringOriginal =
//                "<html>" +
//                        "<head>" +
//                            "<title>" +
//                                "Este é o título." +
//                            "</title>" +
//                        "</head>" +
//                        "<body>" +
//                            "Este é o corpo." +
//                        "</body>" +
//                "</html>";


        // Passando a String que será analisada.
        String stringOriginal = "<html><head><title>Este é o título.</title></head><body>Este é o corpo.</body></html>";

        int abertura = 0;
        int fechamento = 0;
        int [] posicoes;

        // Determinando em quais posições se iniciam e concluem-se os textos.
        posicoes = encontraPosicoes(stringOriginal);
        mostraVetorInt(posicoes);

        // ArrayList que vai conter os objetos do tipo Trecho
        ArrayList<Trecho> trechosList = encontraTrechos(stringOriginal, posicoes);

        //determinaNivel(stringOriginal, trechosList);
        //trecho1.setNivel(3);


         //Mostrando objetos da classe trecho
        for (Trecho t : trechosList) {
            if (!t.texto.isEmpty()) {
                System.out.println(t);
            }
        }



    }// Metodo main


}// Classe Main
