package br.com.igorfersantos.bancodigitalzup.util.cepvalidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CEPValidatorTest {

    @Test
    void formatoValido() {
        String cep1 = "00000-000"; // correto
        String cep2 = "0000-000";
        String cep3 = "000000-000";
        String cep4 = "000000000";
        String cep5 = "00000000";
        String cep6 = "0";
        String cep7 = "00000000000000";
        String cep8 = "aaaaaaaaaaaaaaaa";
        String cep9 = "a";
        String cep10 = "a424asdasda";

        Assertions.assertEquals(true, CEPValidator.isFormatoValido(cep1));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep2));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep3));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep4));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep5));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep6));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep7));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep8));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep9));
        Assertions.assertEquals(false, CEPValidator.isFormatoValido(cep10));
    }
}