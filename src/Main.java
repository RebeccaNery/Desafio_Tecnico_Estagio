public class Main {

    public static void main(String[] args) {

        String[] trechos = new String[100];
        String tagAbertura = "<";
        String tagFechamentoParcial = ">";
        String tagFechamentoTotal = "</";
        String inicioDeTexto = ">";
        String fimDeTexto = "<";

        int j = 0;
        //int nivel = 0;
        int abertura = 0;
        int fechamento = 0;

        //String stringOriginal = "<html> <head> coisas </head> </html>"; //===> só que a "string original" é um código
        // html!
        String stringOriginal =
                "<html> " +
                        "<head> " +
                            "<title> " +
                                "Este é o título. " +
                            "</title> " +
                        "</head> " +
                        "<body> " +
                            "Este é o corpo. " +
                        "</body> " +
                "</html>";

        //Encontrando o nível
        for (int i = 0; i < stringOriginal.length(); i++) { //percorre a string fornecida

            if (stringOriginal.charAt(i) == '<' && stringOriginal.charAt(i + 1) != '/') {
                abertura++;
                System.out.println("Aqui tem tag de abertura. Posição:" + i);
                i++;
            }//if

            if (stringOriginal.charAt(i) == '<' && stringOriginal.charAt(i + 1) == '/'){
                fechamento++;
                System.out.println("Aqui tem tag de fechamento. Posição:" + i);
                i++;
            }//if

            trechos[i] = stringOriginal.substring(abertura, fechamento);

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