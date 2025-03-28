package ese.triptop.features.Patterns.Factory.adapters;

import org.springframework.stereotype.Component;

@Component
public class DictionaryAdapterFactory {

    public IDictionaryAdapter getDictionaryAdapter(String dictionaryName) {
        if (dictionaryName == null) {
            return null;
        }

        try {
            String adapterClassName = "ese.triptop.features.Patterns.Factory.adapters." + dictionaryName + "Adapter";
            Class<?> adapterClass = Class.forName(adapterClassName);

            String dictionaryClassName = "ese.triptop.features.Patterns.Factory.domain." + dictionaryName + "Dictionary";
            Class<?> dictionaryClass = Class.forName(dictionaryClassName);

            Object dictionary = dictionaryClass.getDeclaredConstructor().newInstance();

            return (IDictionaryAdapter) adapterClass.getDeclaredConstructor(dictionaryClass)
                    .newInstance(dictionary);
        } catch (Exception e) {
            System.err.println("Failed to create adapter for dictionary: " + dictionaryName);
            return null;
        }
    }
}
