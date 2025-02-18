public class Main {

    public static boolean verificaLimites(int i, String stringOriginal) {
        if (stringOriginal == null || stringOriginal.length() < 2) {
            return false; // Se a string for nula ou tiver menos de 2 caracteres, não é possível acessar i-1 e i+1.
        }
        return (i - 1) >= 0 && (i + 1) < stringOriginal.length();
    }

    public static String[] encontraTextos (String stringX, int[] posicoes){
        String[] trechos = new String[posicoes.length/2]; //==> indices impar = INICIOS de texto, indices pares = FINS
        // de texto

            for (int k=0; k<posicoes.length-1; k+=2){
                trechos[k/2] = stringX.substring(posicoes[k], posicoes[(k+1)]);
            }//for

        return trechos;
    }

    public static void mostraVetorInt (int[] vetor){
        for (int i=0; i< vetor.length; i++){
            if (vetor[i] != 0){
                System.out.println("Posição: " + vetor[i] + " / I:" + i);
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
        //String[] trechos = new String[100];

        for (int i = 0; i < stringOriginal.length(); i++) {                 //percorre o codigo html
            if (verificaLimites(i, stringOriginal)) {                       // SE A POSIÇÃO EXISTE
                if (stringOriginal.charAt(i) == '<') {                      // SE FOR O SINAL DE MENOR QUE

                    if (stringOriginal.charAt(i - 1) != '>' /*&& stringOriginal.charAt(i - 1) != ' '*/) {
                        fimTexto = i - 1;
                        System.out.println("Aqui tem fim de texto. Posição:" + fimTexto);
                        posicoes[indicePosicoes++] = fimTexto;
                    }//FIM DE TEXTO

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


                if (stringOriginal.charAt(i) == '>') {                      // SE FOR O SINAL DE MAIOR QUE

                    if (stringOriginal.charAt(i + 1) != '<') {              // OUTRA ABERTURA DE TAG EM SEQUENCIA
                        inicioTexto = i + 1;
                        System.out.println("Aqui tem inicio de texto. Posição:" + inicioTexto);
                        i++;
                        posicoes[indicePosicoes++] = inicioTexto;
                    }                                                           //INICIO DE TEXTO

                } //>>>>>>>>>>>>>>>>>>>>>>


            } else if (stringOriginal.charAt(i) == '<') {
                abertura++;
                System.out.println("Aqui tem tag de abertura. Posição:" + i + " / Abertura:" + abertura);
                //primeira tag de abertura
            } else if (stringOriginal.charAt(i) == '>'){
                System.out.println("Fim do código. Posição:" + i);     //ultimo caracter
            }
        } //for
return posicoes;
    }//metodo encontraPosicoes

    public static ArrayList<Trecho> encontraTrechos (String stringX, int[] posicoes){
        ArrayList<Trecho> trechosList = new ArrayList<>();
        //String[] textos = encontraTextos(stringX, posicoes);

        for (int i = 0; i < posicoes.length-1; i+=2) {
            String recorte = stringX.substring(posicoes[i], posicoes[i+1]);
            //System.out.println("Recorte: " + recorte);
            Trecho trecho = new Trecho(recorte, posicoes[i], posicoes[i+1]);
            trechosList.add(trecho);
        }

        return trechosList;
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

        // Determinando em quais posições se iniciam e concluem-se os textos.
        int [] posicoes = encontraPosicoes(stringOriginal);
        mostraVetorInt(posicoes);

        // Guardando no vetor "trechos" os textos encontrados de acordo com as posições do vetor "posicoes".
        String[] trechos = encontraTextos(stringOriginal, posicoes);
        mostraVetorString(trechos);




        //fazer uma substring entre >AQUI</ para mostrar na tela a frase mais profunda

        String resultado = "Hello world!";
        //System.out.println("Nível:" + nivel);
        System.out.println(stringOriginal);
        System.out.println(resultado);

    }// Metodo main


}// Classe Main
