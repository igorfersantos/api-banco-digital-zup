package br.com.igorfersantos.bancodigitalzup.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DateUtilsTest {


    private static int yearDiff(LocalDate date){
        return Period.between(date, LocalDate.now()).getYears();
    }

    @Test
    void isMaiorDeIdade() {
        List<LocalDate> dates = new ArrayList<>();
        List<String> datesString = Arrays.asList("1981-07-07",
                "1983-07-18",
                "1985-03-30",
                "1985-06-24",
                "1985-11-07",
                "1992-11-25",
                "2002-03-23",
                "2013-02-24",
                "2019-02-25",
                "2020-05-10");

        datesString.forEach(date -> dates.add(LocalDate.parse(date)));

        Assertions.assertEquals(39, yearDiff(dates.get(0)), "A idade correta é 39 anos mais velho!");
        Assertions.assertEquals(37, yearDiff(dates.get(1)), "A idade correta é 37 anos mais velho!");
        Assertions.assertEquals(35 , yearDiff(dates.get(2)), "A idade correta é 35 anos mais velho!");
        Assertions.assertEquals(7 , yearDiff(dates.get(7)), "A idade correta é 7 anos mais velho!");
        Assertions.assertEquals(1 , yearDiff(dates.get(8)), "A idade correta é 1 ano mais velho!");

        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(dates.get(0)), "É maior de idade!");
        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(dates.get(1)),"É maior de idade!");
        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(dates.get(2)),"É maior de idade!");
        Assertions.assertEquals(false, DateUtils.isMaiorDeIdade(dates.get(7)),"Não é maior de idade!");
        Assertions.assertEquals(false, DateUtils.isMaiorDeIdade(dates.get(8)),"Não é maior de idade!");

        List<Date> datesAsData = new ArrayList<>();

        dates.forEach(date -> datesAsData.add(Date.valueOf(date)));

        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(datesAsData.get(0)), "É maior de idade!");
        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(datesAsData.get(1)),"É maior de idade!");
        Assertions.assertEquals(true, DateUtils.isMaiorDeIdade(datesAsData.get(2)),"É maior de idade!");
        Assertions.assertEquals(false, DateUtils.isMaiorDeIdade(datesAsData.get(7)),"Não é maior de idade!");
        Assertions.assertEquals(false, DateUtils.isMaiorDeIdade(datesAsData.get(8)),"Não é maior de idade!");

    }
}