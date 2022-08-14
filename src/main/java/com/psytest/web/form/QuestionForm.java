package com.psytest.web.form;

import com.psytest.entity.QuestionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class QuestionForm {


        private List<QuestionEntity> questions;

        public List<QuestionEntity> getQuestions() {
            return questions;
        }

        public void setQuestions(List<QuestionEntity> questions) {
            this.questions = questions;
        }

}
