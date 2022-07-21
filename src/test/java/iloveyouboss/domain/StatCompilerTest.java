package iloveyouboss.domain;

import iloveyouboss.controller.QuestionController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatCompilerTest {

    private QuestionController controller;

    @BeforeEach
    void setUp() {
        controller = new QuestionController();
    }

    @Test
    void responsesByQuestionAnswersCountsByQuestionText() {
        StatCompiler statCompiler = new StatCompiler();
        List<BooleanAnswer> answers = new ArrayList<>();
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, true));
        answers.add(new BooleanAnswer(1, false));
        answers.add(new BooleanAnswer(2, true));
        answers.add(new BooleanAnswer(2, true));
        Map<Integer, String> questions = new HashMap<>();
        questions.put(1, "Tuition reimbursement?");
        questions.put(2, "Relocation package?");

        Map<String, Map<Boolean, AtomicInteger>> responses =
            statCompiler.responsesByQuestion(answers, questions);

        assertThat(responses.get("Tuition reimbursement?").get(Boolean.TRUE).get()).isEqualTo(3);
        assertThat(responses.get("Tuition reimbursement?").get(Boolean.FALSE).get()).isEqualTo(1);
        assertThat(responses.get("Relocation package?").get(Boolean.TRUE).get()).isEqualTo(2);
        assertThat(responses.get("Relocation package?").get(Boolean.FALSE).get()).isZero();
    }

    @Test
    void questionAnswersDateAdded() {
        Instant now = new Date().toInstant();
        controller.setClock(Clock.fixed(now, ZoneId.of("Asia/Seoul")));
        int id = controller.addBooleanQuestion("text");

        Question question = controller.find(id);

        assertThat(question.getCreatedTimestamp()).isEqualTo(now);
    }
}
