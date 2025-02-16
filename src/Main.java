public class Main {

    public static void main(String[] args) {

        int j = 0;
        int nivel = 0;
        String tagAbertura = "<";
        String tagFechamento = "</";
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

        //Encontrando o nível mais profundo
        for (int i = 0; i < stringOriginal.length(); i++) { //percorre
            if (stringOriginal.charAt(i) == '<' && stringOriginal.charAt(i + 1) != '/') {
                nivel++;
                if (stringOriginal.charAt(i) != '/') {
                    System.out.println("Aqui tem tag." + i);
                    i++;
                }else{
                    System.out.println("Aqui tem tag de fechamento." + i);
                    i++;
                }
            }
        }

        //fazer uma substring entre >AQUI</ para mostrar na tela a frase mais profunda

        String resultado = "Hello world!";
        System.out.println("Nível:" + nivel);
        System.out.println(stringOriginal);
        System.out.println(resultado);

    }// Metodo main







}// Classe Main