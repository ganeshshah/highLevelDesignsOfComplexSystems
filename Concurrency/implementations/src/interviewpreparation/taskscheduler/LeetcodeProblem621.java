package interviewpreparation.taskscheduler;

import java.util.Arrays;

public class LeetcodeProblem621 {

/**
 * You are given an array of CPU tasks, each represented by letters A to Z, and a cooling time, n. Each cycle or interval
 * allows the completion of one task. Tasks can be completed in any order, but there's a constraint: identical tasks must
 * be separated by at least n intervals due to cooling time.
 *
 * Return the minimum number of intervals required to complete all tasks.
 */


public static void main(String[] args) {

    char[] tasks = new char[]{'A','A','A','B','B','B','C','C','C','D'};
    int[] frequencyArray = new int[26];
    int coolingPeriod = 2;


    for(int i=0;i<tasks.length;i++){
        frequencyArray[tasks[i]-'A']++;
    }

    Arrays.sort(frequencyArray);

    int maxFrequency = frequencyArray[25]-1;
    int totalIdleSlotsRequired  = maxFrequency*coolingPeriod;

    for(int i=24;i>0;i--){
        totalIdleSlotsRequired -= Math.min(maxFrequency,frequencyArray[i]);
    }

    int result = totalIdleSlotsRequired > 0 ? totalIdleSlotsRequired + tasks.length : tasks.length;
    System.out.println(result);

}

}
