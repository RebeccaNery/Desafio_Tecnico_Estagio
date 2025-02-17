public class Main {

    public static void main(String[] args) {

        String[] trechos = new String[100];
        String tagAbertura = "<";
        //String tagFechamentoParcial = ">";
        String tagFechamentoTotal = "</";
        String inicioDeTexto = ">";
        String fimDeTexto = "<";

        int j = 0;
        //int nivel = 0;
        int abertura = 0;
        int fechamento = 0;
        int inicioTexto = 0;
        int fimTexto = 0;

        //String stringOriginal = "<html> <head> coisas </head> </html>"; //===> só que a "string original" é um código
        // html!
        String stringOriginal =
                "<html>" +
                        "<head>" +
                            "<title>" +
                                "Este é o título." +
                            "</title>" +
                        "</head>" +
                        "<body>" +
                            "Este é o corpo." +
                        "</body>" +
                "</html>";

        //Encontrando o nível
        for (int i = 0; i < stringOriginal.length(); i++) { //percorre o codigo html

            if (stringOriginal.charAt(i) == '<') {                          // SE FOR O SINAL DE MENOR QUE
                if ((i - 1) >= 0 && (i + 1) < stringOriginal.length()) {                      // SE A POSIÇÃO EXISTE
                    if (stringOriginal.charAt(i+1) == '/'){     //FECHAMENTO DE TAG
                        fechamento++;
                        System.out.println("Aqui tem tag de fechamento. Posição:" + i);
                        i++;
                    } else if (stringOriginal.charAt(i - 1) != '>' && stringOriginal.charAt(i - 1) != ' ') {
                        fimTexto = i - 1;
                        System.out.println("Aqui tem fim de texto. Posição:" + fimTexto);
                        i++;                // FIM DE TEXTO
                    } else if(stringOriginal.charAt(i + 1) != '/') {    //SE O PROXIMO NAOOOOO FOR BARRA, QUER DIZER QUE É TAG ABERTURA
                            abertura++;
                            System.out.println("Aqui tem tag de abertura. Posição:" + i);
                            i++;                //ABERTURA DE TAG
                    }
                }else{
                    System.out.println("Não há caractere antes/depois. Posição :" + i);
                } //FORA DOS LIMITES


//<<<<<<<<<<<<<<<<<<<<<<

            } else if (stringOriginal.charAt(i) == '>') {                     // SE FOR O SINAL DE MAIOR QUE
                if ((i - 1) >= 0 && (i + 1) < stringOriginal.length()) {
                    if (stringOriginal.charAt(i + 1) != '<') {           // OUTRA ABERTURA DE TAG EM SEQUENCIA
                        inicioTexto = i + 1;
                        System.out.println("Aqui tem inicio de texto. Posição:" + inicioTexto);
                        i++;
                    } //INICIO DE TEXTO
                } else {
                    System.out.println("Não há mais caracteres. Posição final:" + i);
                }
            } // >>>>>>>>>>>>>>>>>

                //trechos[i] = stringOriginal.substring(abertura, fechamento);

        }//for


        //fazer uma substring entre >AQUI</ para mostrar na tela a frase mais profunda

        String resultado = "Hello world!";
        //System.out.println("Nível:" + nivel);
        System.out.println(stringOriginal);
        System.out.println(resultado);

    }// Metodo main


}// Classe Main

/*

 */