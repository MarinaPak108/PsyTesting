package com.psytest.controller;

import com.psytest.service.TestService;
import com.psytest.service.UserService;
import com.psytest.web.CreateAnswerFormData;
import com.psytest.web.CreateQuestionFormData;
import com.psytest.web.CreateTestFormData;
import com.psytest.web.CreateTestFullFormData;
import com.psytest.web.form.QuestionForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class TestController {

    private final TestService service;
    private final UserService uService;

    Boolean submitted = false;

    public TestController(TestService service, UserService uService) {
        this.service = service;
        this.uService = uService;
    }

    @GetMapping("/tests-home")
    public String showListOfTests(Model model) {
        model.addAttribute("tests", service.getTests());
        return "psyTest/tests/home";
    }

    @GetMapping("/create-test")
    public String showCreateTestForm(Model model) {
        model.addAttribute("formDataTest", new CreateTestFormData());
        return "psyTest/tests/createtest";
    }

    @PostMapping("/create-test")
    public String doCreateTest(@Valid @ModelAttribute("formDataTest") CreateTestFormData formData,
                               BindingResult bindingResult,
                               Model model, RedirectAttributes atts) {

        service.createTest(formData.toParameters());
        return "redirect:/tests-home";

    }

    @GetMapping("/questions-home")
    public String showListOfQuestions(Model model) {
        model.addAttribute("questions", service.getQuestions());
        return "psyTest/tests/questions-home";
    }

    @GetMapping("/create-question")
    public String showCreateQuestionForm(Model model) {
        model.addAttribute("formDataQuestion", new CreateQuestionFormData());
        model.addAttribute("tests", service.getTests());
        return "psyTest/tests/createquestion";
    }

    @PostMapping("/create-question")
    public String doCreateQuestion(@Valid @ModelAttribute("formDataQuestion") CreateQuestionFormData formDataQ,
                                   BindingResult bindingResult,
                                   Model model, RedirectAttributes atts) {

        service.createQuestion(formDataQ.toParameters());
        return "redirect:/questions-home";

    }

    @GetMapping("/answers-home")
    public String showListOfAnswers(Model model) {
        model.addAttribute("answers", service.getAnswers());
        return "psyTest/tests/answers-home";
    }

    @GetMapping("/create-answer")
    public String showCreateAnswerForm(Model model) {
        model.addAttribute("formDataAnswer", new CreateAnswerFormData());
        model.addAttribute("questions", service.getQuestions());
        return "psyTest/tests/createanswer";
    }

    @PostMapping("/create-answer")
    public String doCreateAnswer(@Valid @ModelAttribute("formDataAnswer") CreateAnswerFormData formDataA,
                                 BindingResult bindingResult,
                                 Model model, RedirectAttributes atts) {

        service.createAnswer(formDataA.toParameters());
        return "redirect:/answers-home";

    }

    @GetMapping("/create-testfull")
    public String showCreateTestFullForm(Model model) {
        model.addAttribute("formDataTestFull", new CreateTestFullFormData());
        model.addAttribute("tests", service.getTests());
        model.addAttribute("questions", service.getQuestions());
        model.addAttribute("answers", service.getAnswers());
        return "psyTest/tests/test-full-creation";
    }

    @GetMapping("/showTest")
    public String showTestWithQuestions(Model model) {
        model.addAttribute("test", service.getAllQuestionsByTestId(1l));

        model.addAttribute("testFullFormData", new CreateTestFullFormData());
        return "psyTest/tests/showTest";
    }

    @PostMapping("/showTest")
    public String getTestWithQuestions(@Valid @ModelAttribute("testFullFormData")
                                                   CreateTestFullFormData formDataR) {
        return "psyTest/tests/showTest";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable(value = "id") long id, Model model, RedirectAttributes atts) {
        model.addAttribute("tests", service.getTests());
        model.addAttribute("testResult", service.createTestResult(id));
        return "psyTest/tests/index";
    }

    @PostMapping("/quiz/{id}")
    public String quiz(@PathVariable(value = "id") long resultId, @RequestParam Long testId, Model m, RedirectAttributes atts) {
        QuestionForm qForm = service.getQuestions(testId);
        m.addAttribute("qForm", qForm);
        m.addAttribute("resultTest", service.assignTestToTestResult(resultId,testId));
        return "psyTest/tests/quiz";
    }

    @PostMapping("/submit/{id}")
    public String submit(@PathVariable(value = "id") long resultId,@ModelAttribute QuestionForm qForm, Model m) {
        service.getResult(qForm,resultId);
        m.addAttribute("results",service.showResults(resultId));
        return "psyTest/tests/results"; ///test
    }
}

