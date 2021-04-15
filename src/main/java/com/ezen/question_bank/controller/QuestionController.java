package com.ezen.question_bank.controller;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.question_bank.entity.QuestionEntity;
import com.ezen.question_bank.entity.TestedEntity;
import com.ezen.question_bank.entity.TestedInfoEntity;
import com.ezen.question_bank.model.PagingModel;
import com.ezen.question_bank.model.TestedModel;
import com.ezen.question_bank.repository.QuestionRepository;
import com.ezen.question_bank.repository.TestedInfoRepository;
import com.ezen.question_bank.repository.TestedRepository;
import com.ezen.question_bank.viewmodel.QuestionForm;

@Controller
@RequestMapping("/question")
@EnableJpaRepositories("com.ezen.question_bank.repository")
public class QuestionController {
	@Autowired
	private PagingModel pagingModel;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private TestedRepository testedRepository;

	@Autowired
	private TestedInfoRepository testedInfoRepository;

	@GetMapping("/home")
	public String index(
			Model model
			) {
		
		model.addAttribute("selectedMenu", "active");
		return "question/home";
	}
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page_gubun", defaultValue = "first") String pageGubun,
			@RequestParam("page_size") Optional<Integer> pageSize) {

		int pageSizeSelected = pageSize.orElse(pagingModel.getPageSize());
		pagingModel.setPageSize(pageSizeSelected);

		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(
				pagingModel.getPageNumber(),
				pagingModel.getPageSize());
		
		
		Page<QuestionEntity> page = 
				questionRepository.findAll(pageable);
		pagingModel.setTotalPages(page.getTotalPages());
		
		int pageNumber = pagingModel.getPageNumber();
		switch (pageGubun) {
		case "first":
			pageNumber = 0;
			break;
			
		case "previous":
			if(pageNumber - 1 >= 0) {
				pageNumber--;
			}
			break;
			
		case "next":
			if(pageNumber + 1 <= pagingModel.getTotalPages()-1) {
				pageNumber++;
			}
			break;
			
		case "last":
			pageNumber = pagingModel.getTotalPages()-1;
			break;
		}
		
		pagingModel.setPageNumber(pageNumber);

		@SuppressWarnings("deprecation")
		Pageable pageSortable = new PageRequest(
				pagingModel.getPageNumber(),
				pagingModel.getPageSize(),
				Sort.Direction.DESC, "createdDate");

		List<QuestionEntity> list = questionRepository.findAll(
		PageRequest.of(pagingModel.getPageNumber(),
				pagingModel.getPageSize(), Sort.by("createdDate").descending())).getContent();

		List<QuestionEntity> questionList = 
		questionRepository.findAll(pageSortable).getContent();
				
		model.addAttribute("questionList", questionList);

		model.addAttribute("pagingModel", pagingModel);
		
		model.addAttribute("menuTitle", "출제 문제");
		model.addAttribute("activeMenuTitle", "전체 표시");
		
		return "question/list";
	}

	@GetMapping("/create_table")
	public String create_table() {
		QuestionEntity q = new QuestionEntity();

		String uuid = UUID.randomUUID().toString();
		q.setUuid(uuid);
		q.setTitle("문제1111111");
		q.setChoice("1,2,3,4");
		q.setCorrectAnswer(1);

		// 서버 날짜인데 간편하게 클라이언트 날짜 사용
		Date date = Calendar.getInstance().getTime();
		String currentDate = MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", date);
		q.setCreatedDate(currentDate);

		questionRepository.save(q);
		
		TestedEntity testedEntity = new TestedEntity();
		//testedEntity.setUserId("a@a.com");
		testedEntity.setGubun("2018-0001");
		testedEntity.setQuestionUuid(uuid);
		
		testedRepository.save(new TestedEntity());
		
		
		TestedInfoEntity testedInfoEntity = 
				new TestedInfoEntity();
		testedInfoRepository.save(new TestedInfoEntity());
		
		return "create_table";
	}
	
	/* 문제 추가하는 페이지 */
	@GetMapping("/add")		// get 메소드로 연결 → 주소창 주소
	public String add(
			QuestionForm questionForm
			) {
		
		return "question/add";		// 뷰 페이지 이름
	}
	
	/* 문제등록 버튼 눌렀을 때 */
	@PostMapping("/add")		// get 메소드로 연결 → 주소창 주소
	public String add1(
			@Valid QuestionForm questionForm,
			BindingResult bindingResult
			) {
		
		if(bindingResult.hasErrors()) {
			return "question/add";
		}
			QuestionEntity q = new QuestionEntity();
			if(questionRepository.save(q) != null) {
				
				// 정상 등록
				// 컨트롤러로 이동
				return "redirect:/question/list";
			}
			
			return "add";
		}
		
		
	
	
	@GetMapping("/tested")
	public String tested(
			Model model
			) {
//		entityManager
//        .createQuery("from TestedRepository")
//        .getResultList();
		
		Iterable<TestedEntity> testedPage = 
				testedRepository.findAll();
		
		List<TestedModel> testedList = 
					new ArrayList<>();
		
		for(TestedEntity t : testedPage) {
			TestedModel testedModel = new TestedModel();
			testedModel.setId(t.getId());
			testedModel.setUuid(t.getQuestionUuid());
			testedModel.setTitle(t.getQuestion().getTitle());
			testedModel.setChoice(t.getQuestion().getChoice());
			testedModel.setCorrectAnswer(t.getQuestion().getCorrectAnswer());
			
			testedList.add(testedModel);
		}
		
		model.addAttribute("testedList", testedList);
				
		return "test/tested";
	}

	@GetMapping("/edit")
	public String edit(
			Model model,
			@RequestParam("uuid") String uuid
			) {
	QuestionEntity questionEntity = 
			questionRepository.findByUuidNamedParamsNative(uuid);
	
		
		System.out.println(questionEntity);
		
		model.addAttribute("questionEntity", questionEntity);
	
		return "question/edit";
	}
	
	@GetMapping("/delete")
	public String delete(
			Model model,
			@RequestParam("uuid") String uuid
			) {
	QuestionEntity questionEntity = 
			questionRepository.findByUuidNamedParamsNative(uuid);
	
		
		System.out.println(questionEntity);
		
		model.addAttribute("questionEntity", questionEntity);
	
		return "question/delete";
	}

	@GetMapping("/delete_ok")
	public String delete_ok(			
			@RequestParam("uuid") String uuid
			) {
	QuestionEntity questionEntity = 
			questionRepository.findByUuidNamedParamsNative(uuid);
	
	questionRepository.delete(questionEntity);
		
		
		//메소드로 이동하는 redirect
	
		return "redirect:/question/list";
	}


}
