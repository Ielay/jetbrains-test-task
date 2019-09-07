package org.mm.testprojectjetbrainsdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lelay
 * @since 06.09.19
 */
public class App {

    public static void main(String[] args) {
        String firstOperationsSequence = args[0];
        String secondOperationsSequence = args[1];

        App app = new App();

        System.out.println(
                app.resultWillBeEqual(firstOperationsSequence, secondOperationsSequence)
        );
    }

    public boolean resultWillBeEqual(String firstSeq, String secondSeq) {
        List<String> firstSeqSubSequences = parseToSubSequences(firstSeq);
        List<String> secondSeqSubSequences = parseToSubSequences(secondSeq);

        if (firstSeqSubSequences.size() != secondSeqSubSequences.size()) {
            return false;
        }

        List<List<String>> firstSeqGroups = removeSigns(firstSeqSubSequences);
        List<List<String>> secondSeqGroups = removeSigns(secondSeqSubSequences);

        for (int i = 0; i < firstSeqGroups.size(); ++i) {
            if (!listsAreTheSame(firstSeqGroups.get(i), secondSeqGroups.get(i))) {
                return false;
            }
        }

        return true;
    }

    private List<String> parseToSubSequences(String sequence) {
        sequence = changeInSpecialCases(sequence);

        Pattern pattern = Pattern.compile("(\\+[0-9+]+|\\*[0-9*]+)");
        Matcher matcher = pattern.matcher(sequence);

        List<String> subSequences = new ArrayList<>();
        while (matcher.find()) {
            String subString = matcher.group();

            if (!subString.isEmpty()) {
                subSequences.add(subString);
            }
        }

        return subSequences;
    }

    private String changeInSpecialCases(String sequence) {
        if (sequence.contains("+0")) {
            sequence = sequence.replace("+0", "");
        }
        if (sequence.contains("*1")) {
            sequence = sequence.replace("*1", "");
        }

        return sequence;
    }

    private List<List<String>> removeSigns(List<String> subSequences) {
        List<List<String>> numberGroups = new ArrayList<>();
        for (String subSeq : subSequences) {
            String[] splittedNumbers = subSeq.split("[*+]");

            numberGroups.add(
                    Arrays.asList(
                            Arrays.copyOfRange(splittedNumbers, 1, splittedNumbers.length)
                    )
            );
        }

        return numberGroups;
    }

    private boolean listsAreTheSame(List<String> list1, List<String> list2) {
        return list1.containsAll(list2) && list2.containsAll(list1);
    }
}
