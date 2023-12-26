package com.psytest.service;

import com.psytest.entity.*;
import com.psytest.repo.*;
import com.psytest.web.form.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestService {
    @Autowired
    QuestionForm qForm;
    private static final Logger logger = Logger.getLogger(TestService.class.getName());
    private final TestRepository repository;
    private final QuestionRepository questionRepository;
    private final CountResultRepo resultRepository;
    private final TestResultRepo testResultRepo;
    private final UserRepository uRepo;
    private final AnswerRepository answerRepository;

    public TestService(TestRepository repository, QuestionRepository questionRepository, CountResultRepo resultRepository, TestResultRepo testResultRepo, UserRepository uRepo, AnswerRepository answerRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
        this.testResultRepo = testResultRepo;
        this.uRepo = uRepo;
        this.answerRepository = answerRepository;
    }

    public TestEntity createTest(TestCreationParameters parameters) {
        logger.info(String.format("creating test with parameters: %s and %s", parameters.getTestName(), parameters.getDescription(), parameters.getCategoryCount()));
        TestEntity test = new TestEntity(parameters.getTestName(), parameters.getDescription(), parameters.getCategoryCount());
        logger.info("Save to repository test");
        return repository.save(test);
    }

    public TestResultEntity createTestResult (long id){
        UserEntity userFind = uRepo.getUserById(id);
        logger.info("Create Test Result  empty");
        TestResultEntity testR = new TestResultEntity();
        logger.info("Save to repository  test result");
        testResultRepo.save(testR);
        logger.info("assign test result to user");
        List<TestResultEntity> testResults = userFind.getResults();
        testResults.add(testR);
        userFind.setResults(testResults);
        uRepo.save(userFind);
        logger.info("Test Result- " + testR.getId() + "is assigned to user - " + userFind.getFathersName());
        return testR;
    };

    public TestResultEntity assignTestToTestResult (long testResult, long testId){
        TestResultEntity testR = testResultRepo.getById(testResult);
        TestEntity test = repository.getTestById(testId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        testR.setDateStart(timestamp);
        testResultRepo.save(testR);
        logger.info("assign test  to test result");
        List<TestResultEntity> testResults = test.getResults();
        testResults.add(testR);
        test.setResults(testResults);
        repository.save(test);
        logger.info("Test Result- " + testR.getId() + "is assigned to user - " + test.getTestName());

        return testR;
    };

    public long getTestIdByTestResult (long testResult){
        TestResultEntity testR = testResultRepo.getById(testResult);
        TestEntity test = testR.getTest();
        long testId = test.getId();
        return testId;
    }

    public void createQuestion(QuestionCreationParameters questionParameters) {
        //logger.info(String.format("creating test with parameters: %s and %s", questionParameters.getQuestion(), questionParameters.getQuestionNumber(), questionParameters.getTestId()));
        QuestionEntity question = new QuestionEntity(questionParameters.getQuestion(), questionParameters.getQuestionNumber(), questionParameters.getQuestionValue());
        //logger.info("Save to repository question");
        questionRepository.save(question);


        TestEntity test = repository.getById(questionParameters.getTestId());
        List<QuestionEntity> questionList = test.getQuestionsList();
        questionList.add(question);
        test.setQuestionsList(questionList);
        repository.save(test);
        //logger.info("Test- " + test.getTestName() + "is assigned to question - " + question.getQuestion());
    }

    public void createAnswer(AnswerCreationParameters answerParameters) {

        AnswerEntity answer = new AnswerEntity(answerParameters.getAnswer(), answerParameters.getAnswerValue());
        //logger.info("Save to repository answer");
        answerRepository.save(answer);


        QuestionEntity question = questionRepository.getById(answerParameters.getQuestionId());
        List<AnswerEntity> answerList = question.getAnswerList();
        answerList.add(answer);
        question.setAnswerList(answerList);
        questionRepository.save(question);

    }

    public void createTestFull(TestFullCreationParameters testFullParameters) {


    }

    public TestEntity getTest(String testName) {
        TestEntity test = repository.getTestByTestName(testName).get();
        logger.info("found test with id: " + test.getId());
        return test;
    }

    public QuestionEntity getQuestion(String questionName) {
        QuestionEntity question = questionRepository.getQuestionByQuestion(questionName).get();
        logger.info("found test with id: " + question.getId());
        return question;
    }

    public AnswerEntity getAnswer(String answerName) {
        AnswerEntity answer = answerRepository.getAnswerByAnswer(answerName).get();
        logger.info("found answer with id: " + answer.getId());
        return answer;
    }

    public List<QuestionEntity> getAllQuestionsByTestId(Long testId) {
        Optional<TestEntity> test = repository.findById(testId);
        return questionRepository.getQuestionEntityByTest(test);
    }

    public List<AnswerEntity> getAllAnswersByQuestionId(Long questionId) {
        Optional<QuestionEntity> question = questionRepository.findById(questionId);
        return answerRepository.getAnswerEntityByQuestion(question);
    }

    public List<TestEntity> getTests() {
        List<TestEntity> tests = new ArrayList<>();
        Long records = repository.countByDescriptionIsNotNull();
        if (records>1){
            tests = repository.findAll();
        }
        else{
            TestEntity test= repository.getTestById(1l);
            tests.add(test);
        }
        logger.info("found test number is" + tests.size());
        return tests;
    }

    public List<QuestionEntity> getQuestions() {
        return questionRepository.findAll();
    }

    public List<AnswerEntity> getAnswers() {
        return answerRepository.findAll();
    }

    public void countTestResult(List<Long> questionId, List<Long> answerId) {

    }

    public QuestionEntity getQuestion(Long testId, Long Id) {
        Optional<TestEntity> test = repository.findById(testId);
        QuestionEntity question = questionRepository.getQuestionEntityByTestAndId(test, Id);
        return question;
    }

    public QuestionForm getQuestions(Long testId) {
        Optional<TestEntity> test = repository.findById(testId);
        List<QuestionEntity> allQues = questionRepository.getQuestionEntitiesByTestAndIsShow(test, 1);
        qForm.setQuestions(allQues);
        return qForm;
    }

    public QuestionForm getRandQuestions(Long testId) {
        Optional<TestEntity> test = repository.findById(testId);
        List<QuestionEntity> allQues = questionRepository.getQuestionEntityByTest(test);
        List<QuestionEntity> qList = new ArrayList<QuestionEntity>();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        qForm.setQuestions(qList);

        return qForm;
    }

    public ArrayList<String> checkAnswers(QuestionForm qForm){
        int j=0;
        ArrayList<String> notANsweredQuestions = new ArrayList<>();
        for(QuestionEntity q : qForm.getQuestions()){
            if (q.getChoose() == null){
                notANsweredQuestions.add(j, q.getQuestionNumber().toString());{
                    j=j+1;
                }
            }

        }
        return notANsweredQuestions;
    }

    public ArrayList<Double> getResult(QuestionForm qForm, long testResult) {
        TestResultEntity testR = testResultRepo.getById(testResult);
        int uiSize = qForm.getQuestions().size();
        TestEntity test = testR.getTest();
        long testId = test.getId();
        ArrayList<Double> resultArray =  new ArrayList<>(Collections.nCopies(test.getCategoryCount(), -1.0));
        qForm = addAdditionalQuestions(qForm, testId);
        for (QuestionEntity q : qForm.getQuestions()) {
            QuestionEntity ques = questionRepository.getQuestionEntityByQuestionNumberAndIsShow(q.getQuestionNumber(), 1);
            List<AnswerEntity> answers = answerRepository.findAnswerEntityByQuestion(ques);
            AnswerEntity answer = answers.get(q.getChoose());
            if (resultArray.get(q.getQuestionValue()) == -1.0) {
                resultArray.set(q.getQuestionValue(), Double.valueOf(answer.getAnswerValue()));
            } else {
                Double sum = 0.0;
                sum = resultArray.get(q.getQuestionValue()) + Double.valueOf(answer.getAnswerValue());
                resultArray.set(q.getQuestionValue(), sum);
            }
        }
        for (int i = 0; i < test.getCategoryCount(); i++) {
            int qtyQ = questionRepository.countQuestionEntitiesByTestIdAndQuestionValue(testId, i);
            Double arrayResult = Double.valueOf(resultArray.get(i));
            if(arrayResult != -1.0){
                Double result = arrayResult /qtyQ;
                resultArray.set(i, result);
            }
        }
        QuestionEntity quesSize = questionRepository.getFirstByTestAndIsShow(test, 0);
        // add 13th element with total srednearifmeticheskoe:
        List <Double> doubleResult = getNewListOfDouble(resultArray,quesSize.getQuestionValue());
        //convert ListString to String
        String stringResult = listToStringConverter(doubleResult);
        // set to test Result
        testR.setResults(stringResult);
        //save to repo
        testResultRepo.save(testR);
        // set to DateEnd
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        testR.setDateEnd(timestamp);
        //save to Repo
        testResultRepo.save(testR);

        return resultArray;
    }

    public QuestionForm addAdditionalQuestions(QuestionForm qForm, Long testId){
        Optional<TestEntity> test = repository.findById(testId);
        List<QuestionEntity> questions = questionRepository.getQuestionEntitiesByTestAndIsShow(test, 0);
        List<QuestionEntity> questionsUI = qForm.getQuestions();
        for(QuestionEntity q : questions){
            QuestionEntity commonQ = questionsUI.get(q.getQuestionNumber()-1);
            q.setChoose(commonQ.getChoose());
            questionsUI.add(q);
        }
        //qForm.setQuestions(questionsUI);
        return qForm;
    }


    public String listToStringConverter (List<Double> list){
        StringBuilder strbul=new StringBuilder();
        for(Double str : list)
        {
            strbul.append(str.toString());
            //for adding comma between elements
            strbul.append(",");
        }
        //just for removing last comma
        strbul.setLength(strbul.length()-1);
        String str=strbul.toString();
        return str;
    }

    public List<Double> stringToDoubleConverter (String str) {
        List<Double> convertedStringList = Stream.of(str.split(","))
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        return convertedStringList;
    }

    public ArrayList<String> countResults (List<Double> resultsList, long testResult){
        TestResultEntity testR = testResultRepo.getById(testResult);
        TestEntity test = testR.getTest();
        ArrayList<String> showResults = new ArrayList<>(Collections.nCopies(test.getCategoryCount(), null));
        for (int i = 0; i <= test.getCategoryCount(); i++) {
            if (i == 0){
                if (resultsList.get(i)>=4){
                    showResults = new ArrayList<>(Collections.nCopies(1, null));
                    showResults.set(i, "results are not valid. please try again");
                    return showResults;
                }

                else{showResults.set(i, "Ваши результаты:");}
            } else{
                List<CountResult> countResults = resultRepository.findAllByQuestionValue(i);
                for ( int j=0; j<countResults.size(); j++){
                    CountResult countResult = countResults.get(j);
                    if (resultsList.get(i)<=countResult.getMaxValue() && resultsList.get(i) >= countResult.getMinValue()){
                        String level = countResult.getLevel();
                        showResults.set(i,level);
                    }
                }
            }
        }
        return showResults;

    }

    public ArrayList<String> showResults (long testResult) {
        List<String> description = Arrays.asList(new String[]{"0.",
                "1.Ценности личностной гармонии  ЦГЛ : ",
                "2.Ценности духовные : ",
                "3.Образ жизни (его оптимальность) : ",
                "4.Саморегуляция эмоций и др : ",
                "5.Конструктивность общения : ",
                "6.Самогармонизация личности : ",
                "7.Умеренность силы желаний и достижений : ",
                "8.Самостоятельность : ",
                "9.Удовлетворённость жизнью и отношениями с людьми : ",
                "10.Жизненное самоопределение : ",
                "11.Жизненная самореализация : ",
                "12.Позитивность самооценки : ",
                "13.Интегральная гармоничность личности : ",
                "14.Нравственные ценности : ",
                "15.Эстетические ценности : ",
                "16.Творчество : ",
                "17.Реалистичность картины Мира : ",
        });
        TestResultEntity testR = testResultRepo.getById(testResult);
        String stringResults = testR.getResults();
        List<Double> resultsList = stringToDoubleConverter(stringResults);
        TestEntity test = testR.getTest();
        ArrayList<String> showResults = new ArrayList<>(Collections.nCopies(test.getCategoryCount(), null));
            for (int i = 0; i <= test.getCategoryCount(); i++) {
                if (i == 0){
                    if (resultsList.get(i)>=4){
                        showResults = new ArrayList<>(Collections.nCopies(1, null));
                        showResults.set(i, "results are not valid. please try again");
                        return showResults;
                    }

                    else{showResults.set(i, "Ваши результаты:");}
                } else{
                    List<CountResult> countResults = resultRepository.findAllByQuestionValue(i);
                    for ( int j=0; j<countResults.size(); j++){
                        CountResult countResult = countResults.get(j);
                        if (resultsList.get(i)<=countResult.getMaxValue() && resultsList.get(i) >= countResult.getMinValue()){
                            String level = countResult.getLevel();
                            showResults.set(i,description.get(i)+level);
                        }
                    }
                }

            }
        return showResults;
        }

    public List<Double> getNewListOfDouble(List<Double> doubleArr, int subsractSize) {
        Double sumElementsOfArray = 0.0;
        for (int i = 1; i < subsractSize-1; i++) {
            sumElementsOfArray+= doubleArr.get(i);
        }

        Double newElement = sumElementsOfArray/(subsractSize-2);
        int category13th = doubleArr.indexOf(-1.0);
        doubleArr.set(category13th, newElement);
        return doubleArr;
    }

    }


