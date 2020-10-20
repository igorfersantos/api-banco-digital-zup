package br.com.igorfersantos.bancodigitalzup.util.cepvalidator;


public class CEPValidator {
    // caso necessário
    //@Autowired
    //RestTemplate restTemplate;

    private final static String PADRAO_CEP = "^\\d{5}[-]\\d{3}$";
    //private final static String REQUEST_VIACEP = "https://viacep.com.br/ws/{0}/json/";
    private final static String MESSAGE = "Formato de CEP inválido!";

    public static boolean isFormatoValido(String cep){
        return cep.matches(PADRAO_CEP);
    }
}
