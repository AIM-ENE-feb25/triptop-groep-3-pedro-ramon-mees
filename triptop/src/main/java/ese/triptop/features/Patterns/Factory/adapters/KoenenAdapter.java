package ese.triptop.features.Patterns.Factory.adapters;


import ese.triptop.features.Patterns.Factory.domain.KoenenDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KoenenAdapter implements IDictionaryAdapter {

    private final KoenenDictionary koenenDictionary;

    @Autowired
    public KoenenAdapter(KoenenDictionary koenenDictionary) {
        this.koenenDictionary = koenenDictionary;
        koenenDictionary.openEnglishDutch();
    }

    @Override
    public String translate(String word) {
        return koenenDictionary.lookUp(word);
    }

    @Override
    public String getName() {
        return koenenDictionary.getClass().getSimpleName();
    }
}

