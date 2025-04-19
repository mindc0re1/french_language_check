package rk;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import rk.daxtra.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class JunitSimpleTest {

    static Stream<Arguments> buttonsForGivenLocale() {

        return Stream.of(

                Arguments.of(Locale.FR, List.of("Accueil", "Produits", "Pourquoi DaXtra", "Ressources", "Contactez nous"))

        );
    }


    @MethodSource
    @ParameterizedTest(name = "For locale {0} display buttons {1}")
    @Tag("BLOCKER")
    void buttonsForGivenLocale(
            Locale locale,
            List<String> buttons

        ) {
             open ("https://www.daxtra.com");
             $$("a.nav__link").findBy(text("Language")).shouldBe(visible).hover();

             $$("a[href='https://fr.daxtra.com/']").find(text(locale.name())).click();

        $$("#menu-main > li > a").shouldHave(CollectionCondition.textsInAnyOrder(buttons));


    }
}

