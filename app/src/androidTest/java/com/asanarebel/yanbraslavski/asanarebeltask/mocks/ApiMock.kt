package com.affinitas.task.main.mocks

import com.affinitas.task.api.ApiService
import com.affinitas.task.models.responses.PersonalityTestResponseModel
import com.affinitas.task.models.responses.Question
import com.affinitas.task.models.responses.QuestionType
import io.reactivex.Observable

/**
 * Created by yan.braslavski on 10/28/17.
 */
class ApiMock : ApiService {

    override fun fetchData(): Observable<PersonalityTestResponseModel> {

        val mockCategoriesList = listOf("fakeCategory")
        val mockQuestionsList = listOf(
                Question("fakeQuestion", "fakeCategory",
                QuestionType("single_choice", listOf("option1","option2","option3"), null)),
                Question("fakeQuestion2", "fakeCategory2",
                        QuestionType("single_choice", listOf("option4","option5","option6"), null))
        )

        return Observable.just(PersonalityTestResponseModel(mockCategoriesList,
                mockQuestionsList))
    }
}