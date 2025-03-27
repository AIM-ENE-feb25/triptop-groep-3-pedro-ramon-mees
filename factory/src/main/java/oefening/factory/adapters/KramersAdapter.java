package oefening.factory.adapters;

import oefening.factory.models.KramersDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KramersAdapter implements IDictionaryAdapter{

    private final KramersDictionary kramersDictionary;

    @Autowired
    public KramersAdapter(KramersDictionary kramersDictionary) {
        this.kramersDictionary = kramersDictionary;
    }

    @Override
    public String translate(String word) {
        return kramersDictionary.find(word);
    }

    @Override
    public String getName() {
        return kramersDictionary.getClass().getSimpleName();
    }
}
