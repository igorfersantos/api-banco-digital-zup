package br.com.igorfersantos.bancodigitalzup.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class DateUtils {
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public static Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static boolean isMaiorDeIdade(LocalDate dataNascimento){
        Period period = Period.between(dataNascimento, LocalDate.now());
        return period.getYears() >= 18;
    }

    public static boolean isMaiorDeIdade(Date dataNascimento){
        LocalDate localDateDataNascimento = null;
        try {
            localDateDataNascimento = convertToLocalDate(dataNascimento);
        }catch (Exception e){
            e.printStackTrace();
        }
        return isMaiorDeIdade(localDateDataNascimento);
    }

}
