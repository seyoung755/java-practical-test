package iloveyouboss;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    private Profile profile;
    private Question question;
    private Criteria criteria;

    @BeforeEach
    void setUp() {
        profile = new Profile("Bull Hockey, Inc.");
        question = new BooleanQuestion(1, "Got bonuses?");
        criteria = new Criteria();
    }

    @Test
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        profile.add(new Answer(Bool.FALSE, question));

        criteria.add(new Criterion(new Answer(Bool.TRUE, question), Weight.MustMatch));

        boolean matches = profile.matches(criteria);

        assertFalse(matches);
    }

    @Test
    void matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(new Answer(Bool.FALSE, question));

        criteria.add(new Criterion(new Answer(Bool.TRUE, question), Weight.DontCare));

        boolean matches = profile.matches(criteria);

        assertTrue(matches);
    }
}
