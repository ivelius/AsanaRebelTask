package com.affinitas.task.models.responses

/**
 * Created by yan.braslavski on 10/24/17.
 */

data class PersonalityTestResponseModel(
        val categories: List<String>,
        val questions: List<Question>
)

data class Question(
        val question: String, //What is your gender?
        val category: String, //hard_fact
        val question_type: QuestionType
)

data class QuestionType(
        val type: String, //single_choice
        val options: List<String>,
        val condition: Condition?
)

data class Condition(
        val predicate: Predicate,
        val if_positive: IfPositive,
        var conditionAnswer: String?
)

data class IfPositive(
        val question: String, //What age should your potential partner be?
        val category: String, //hard_fact
        val question_type: ConditionalQuestionType
)

data class ConditionalQuestionType(
        val type: String, //number_range
        val range: Range
)

data class Range(
        val from: Int, //18
        val to: Int //140
)

data class Predicate(
        val exactEquals: List<String>
)