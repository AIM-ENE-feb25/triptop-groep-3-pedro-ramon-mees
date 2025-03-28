package ese.triptop.features.Patterns.Factory;

import ese.triptop.features.Patterns.Factory.adapters.DictionaryAdapterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryRunner {

    private final DictionaryAdapterFactory dictionaryAdapterFactory;

    @Autowired
    public FactoryRunner(DictionaryAdapterFactory dictionaryAdapterFactory) {
        this.dictionaryAdapterFactory = dictionaryAdapterFactory;
    }

    public void run() {
        System.out.println(
                "Kramers: " +
                        dictionaryAdapterFactory
                                .getDictionaryAdapter("Kramers")
                                .translate("dog")
        );

        System.out.println(
                "Koenen: " +
                        dictionaryAdapterFactory
                                .getDictionaryAdapter("Koenen")
                                .translate("dog")
        );
    }
}
