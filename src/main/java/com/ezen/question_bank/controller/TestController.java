package com.ezen.question_bank.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.question_bank.entity.QuestionEntity;
import com.ezen.question_bank.entity.TestedEntity;
import com.ezen.question_bank.entity.TestedInfoEntity;
import com.ezen.question_bank.repository.QuestionRepository;
import com.ezen.question_bank.repository.TestedInfoRepository;
import com.ezen.question_bank.repository.TestedRepository;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestedRepository testedRepository;

	@Autowired
	private TestedInfoRepository testedInfoRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Value("${test.question.counts}")
	private int testQuestionCounts;
	
	@GetMapping("/create")
	public String create() {

		List<String> uuidList = new ArrayList<>();
		List<String> uuidListExtracted = 
				new ArrayList<>();
		
		List<String> allQuestions 
		= questionRepository.findUUidNative();
		
		for (String q : allQuestions) {
			uuidList.add(q);
		}
		
		Random r = new Random();
		for(int i=0; i<= testQuestionCounts-1; i++) {
			int index = r.nextInt(uuidList.size());
			String uuid = uuidList.get(index);
			uuidListExtracted.add(uuid);
			uuidList.remove(index);
		}
		
		int seq = 0;
		for (String uuid : uuidListExtracted) {
			QuestionEntity q = 
			questionRepository.findByUuidNamedParamsNative(uuid);
			
			TestedEntity t = new TestedEntity();
			t.setGubun("2018-0001");
			t.setSeq(++seq);
			t.setQuestionUuid(q.getUuid());
			
			testedRepository.save(t);
		}
		
		TestedInfoEntity testedInfo = new TestedInfoEntity();
		testedInfo.setUserId("a@a.com");
		testedInfo.setGubun("2018-0001");
		testedInfo.setQuestion_counts(testQuestionCounts);
		
		Date date = Calendar.getInstance().getTime();
		String currentDate = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", date);
		testedInfo.setTestedDate(currentDate);
		
		testedInfoRepository.save(testedInfo);
		
		return "test/create";
	}
}
