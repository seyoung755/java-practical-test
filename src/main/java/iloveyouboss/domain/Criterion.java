package iloveyouboss.domain;

public class Criterion implements Scoreable {
    private Weight weight;
    private Answer answer;
    private int score;

    public Criterion(Answer answer, Weight weight) {
        this.weight = weight;
        this.answer = answer;
    }

    public Weight getWeight() {
        return weight;
    }

    public Answer getAnswer() {
        return answer;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
