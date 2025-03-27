package oefening.factory;

import oefening.factory.adapters.DictionaryAdapterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FactoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactoryApplication.class, args);

		DictionaryAdapterFactory dictionaryAdapterFactory = new DictionaryAdapterFactory();
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
