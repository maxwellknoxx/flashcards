package com.maxwell.flashcards;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;


@SpringBootApplication
public class Tests {

	@Autowired
	private DictionaryServiceImpl dictionaryService;

	public void addDictionary(DictionaryEntity dictionary) {
		try {
			dictionaryService.addDictionary(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<DictionaryEntity> listAllDic() {
		List<DictionaryEntity> list = dictionaryService.findAll();
		return list;
	}

	//public void addWord(ExpressionEntity word) {
		//expressionService.addWord(word);
	//}

	//public List<ExpressionEntity> listAll() {
	//	List<ExpressionEntity> list = expressionService.findAll();
	//	return list;
	//}

	public static void main(String[] args) {

		DictionaryEntity dictionary = new DictionaryEntity();
		dictionary.setDictionaryName("English");

		ExpressionEntity expressionEntity = new ExpressionEntity();
		expressionEntity.setExpression("Wind");
		expressionEntity.setMeaning("Vento");

		List<ExpressionEntity> expression = new ArrayList<>();

		expression.add(expressionEntity);

		SpringApplication.run(Tests.class, args);

		Tests t = new Tests();
		t.addDictionary(dictionary);
		//t.addWord(expressionEntity);

		//List<ExpressionEntity> list = t.listAll();

		//list.forEach(WordEntity -> System.out
			//	.println(expressionEntity.getExpression() + " " + expressionEntity.getMeaning()));

		
	}

}
