package helpers;

import io.qameta.allure.Step;
import pojo.AdditionGet;
import pojo.GetEntity;

import java.util.Random;

public class RandomGenerators {
    static Random r = new Random();
    @Step("Создание случайного заголовка")
    public static String generateRandomTitle(){
        return "Entity Title " + r.nextInt(1000, 10000);
    }

    @Step("Создание случайных важных чисел")
    public static int[] generateRandomImportantNumbers(){
        return new int[]{r.nextInt(100), r.nextInt(100), r.nextInt()};
    }

    @Step("Создание случайного поля verifed")
    public static boolean generateRandomVerifed(){
        return r.nextInt(0,2)==1;
    }

    @Step("Создание случайной дополнительной информации")
    public static String generateRandomAdditionalInfo(){
        return "Extra info № "+r.nextInt(1000,10000);
    }

    @Step("Создание случайного дополнительного числа")
    public static int generateRandomAdditionalNumber(){
        return r.nextInt(0,100);
    }

    @Step("Создание случайного Additional")
    public static AdditionGet generateRandomAddition(){
        return AdditionGet.builder()
                .additional_info(generateRandomAdditionalInfo())
                .additional_number(generateRandomAdditionalNumber())
                .build();
    }

    @Step("Создание случайного Entity")
    public static GetEntity generateRandomEntity(){
        return GetEntity.builder()
                .addition(generateRandomAddition())
                .important_numbers(generateRandomImportantNumbers())
                .title(generateRandomTitle())
                .verified(generateRandomVerifed())
                .build();
    }
}
