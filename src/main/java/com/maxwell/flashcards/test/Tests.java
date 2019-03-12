package com.maxwell.flashcards.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.maxwell.flashcards.entity.DictionaryEntity;
import com.maxwell.flashcards.entity.ExpressionEntity;
import com.maxwell.flashcards.service.DictionaryService;
import com.maxwell.flashcards.service.impl.DictionaryServiceImpl;
import com.maxwell.flashcards.service.impl.ExpressionServiceImpl;

public class Tests {
	
	@Autowired
	private ExpressionServiceImpl expressionService;
	
	@Autowired
	private DictionaryServiceImpl dictionaryService;
	
	public void addDictionary(DictionaryEntity dictionary) {
		try {
			dictionaryService.addDictionary(dictionary);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<DictionaryEntity> listAllDic(){
		List<DictionaryEntity> list = dictionaryService.findAll();
		return list;
	}
	
	public void addWord(ExpressionEntity word) {
		expressionService.addWord(word);
	}
	
	public List<ExpressionEntity> listAll(){
		List<ExpressionEntity> list = expressionService.findAll();
		return list;
	}
	
	public static void main(String[] args) {
		
		DictionaryEntity dictionary = new DictionaryEntity();
		dictionary.setDictionaryName("English");
		
		ExpressionEntity expressionEntity = new ExpressionEntity();
		expressionEntity.setExpression("Wind");
		expressionEntity.setMeaning("Vento");
		
		List<ExpressionEntity> expression = new ArrayList<>();
		
		expression.add(expressionEntity);
		
		dictionary.setWords(expression);
		
		Tests t = new Tests();
		t.addDictionary(dictionary);
		t.addWord(expressionEntity);
		
		List<ExpressionEntity> list = t.listAll();
		
		list.forEach(WordEntity -> System.out.println(expressionEntity.getExpression() + " " + expressionEntity.getMeaning()));
		
		
	}

}
