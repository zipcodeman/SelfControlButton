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
        addItem(new Question("Should I have candy?", 1.0, 0.01));
    }
    
    private static void addItem(Question question) {
        ITEMS.add(question);
        ITEM_MAP.put(question.question, question);
    }
    
    public static class Question {
        public String question;
        public double startingProbability;
        public double probabilityDecrease;
        
        private Random   rand = new Random();
        
        public Question(String question, double start, double decrease) {
            this.question = question;
            this.startingProbability = start;
            this.probabilityDecrease = decrease;
        }
        
        public String toString() {
            return this.question + this.startingProbability;
        }
        
        public boolean getAnswer() {
            final double selection = rand.nextDouble();
            
            boolean answer = (selection <= startingProbability);
            
            if (answer) {
                this.startingProbability -= this.probabilityDecrease;
            }
            
            return answer;
        }
    }
}
