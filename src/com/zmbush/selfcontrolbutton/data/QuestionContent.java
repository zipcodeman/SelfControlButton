package com.zmbush.selfcontrolbutton.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuestionContent {
    public static List<Question> ITEMS = new ArrayList<Question>();
    public static Map<String, Question> ITEM_MAP = new HashMap<String, Question>();
    
    static {
        addItem(new Question("Should I have candy?", 1.0, 0.80, 0.1));
    }
    
    private static void addItem(Question question) {
        ITEMS.add(question);
        ITEM_MAP.put(question.question, question);
    }
    
    public static class Question {
        private String question;
        private double probability;
        private double probabilityDecrease;
        private double targetProbability;
        
        private Random   rand = new Random();
        
        public Question(String question, double start, double decrease, double target) {
            this.question = question;
            this.probability = start;
            this.probabilityDecrease = decrease;
            this.targetProbability = target;
        }
        
        public String toString() {
            return this.question + this.probability;
        }
        
        public String getQuestion() {
            return this.question;
        }
        
        public boolean getAnswer() {
            final double selection = rand.nextDouble();
            
            boolean answer = (selection <= probability);
            
            if (answer && probability > targetProbability) {
                this.probability *= this.probabilityDecrease;
                if (probability < targetProbability) {
                    probability = targetProbability;
                }
            }
            
            return answer;
        }
    }
}
