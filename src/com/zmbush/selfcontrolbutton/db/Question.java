package com.zmbush.selfcontrolbutton.db;

import java.util.List;
import java.util.Random;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name="Questions")
public class Question extends Model {
    @Column(name="question")
    public String question;
    
    @Column(name="probability")
    public double probability;
    
    @Column(name="target_probability")
    public double target_probability;
    
    @Column(name="decrease_percent")
    public double decrease_percent;

    private Random   rand = new Random();
    
    public String toString() {
        return this.question + this.probability;
    }
    
    public String getQuestion() {
        return this.question;
    }
    
    public boolean getAnswer() {
        final double selection = rand.nextDouble();
        
        boolean answer = (selection <= probability);
        
        if (answer && probability > target_probability) {
            this.probability *= (1.0 - this.decrease_percent);
            if (probability < target_probability) {
                probability = target_probability;
            }
        }
        
        this.save();
        
        return answer;
    }
    
    public static List<Question> getAll() {
        return new Select().from(Question.class).execute();
    }
}
