package com.bajicdusko.kotlinstarterkit.data

import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.OwnerData
import com.bajicdusko.kotlinstarterkit.data.api.model.stackoverflow.SOQuestionData
import com.bajicdusko.kotlinstarterkit.domain.model.Owner
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import org.joda.time.DateTime
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */

@RunWith(MockitoJUnitRunner::class)
class MapperTest {

    @Test
    fun shouldMapOwnerToOwnerData(){
        val owner = Owner(5, 1, "avatar", "name", "someurl")
        var ownerData = owner.transform()
        Assert.assertEquals(ownerData.avatar, owner.avatar)
        Assert.assertEquals(ownerData.displayName, owner.displayName)
        Assert.assertEquals(ownerData.profileUrl, owner.profileUrl)
        Assert.assertEquals(ownerData.reputation, owner.reputation)
        Assert.assertEquals(ownerData.userId, owner.userId)
    }

    @Test
    fun shouldMapOwnerDataToOwner(){
        val ownerData = OwnerData(5, 1, "avatar", "name", "someurl")
        var owner = ownerData.transform()
        Assert.assertEquals(owner.avatar, ownerData.avatar)
        Assert.assertEquals(owner.displayName, ownerData.displayName)
        Assert.assertEquals(owner.profileUrl, ownerData.profileUrl)
        Assert.assertEquals(owner.reputation, ownerData.reputation)
        Assert.assertEquals(owner.userId, ownerData.userId)
    }

    @Test
    fun shouldMapSOQuestionToSOQUestionData(){

        val ownerData = OwnerData(5, 1, "avatar", "name", "someurl")

        var soQuestionData = SOQuestionData(
                ArrayList<String>(),
                ownerData,
                true,
                1,
                2,
                3,
                DateTime.now(),
                null,
                DateTime.now().minusDays(1),
                5,
                "http://test",
                "First question"
        )

        var soQuestion = soQuestionData.transform()

        Assert.assertEquals(soQuestionData.answerCount, soQuestion.answerCount)
        Assert.assertEquals(soQuestionData.creationDate, soQuestion.creationDate)
        Assert.assertEquals(soQuestionData.isAnswered, soQuestion.isAnswered)
        Assert.assertEquals(soQuestionData.lastActivityDate, soQuestion.lastActivityDate)
        Assert.assertEquals(soQuestionData.lastEditDate, soQuestion.lastEditDate)
        Assert.assertEquals(soQuestionData.ownerData.avatar, soQuestion.owner.avatar)
        Assert.assertEquals(soQuestionData.ownerData.displayName, soQuestion.owner.displayName)
        Assert.assertEquals(soQuestionData.ownerData.profileUrl, soQuestion.owner.profileUrl)
        Assert.assertEquals(soQuestionData.ownerData.reputation, soQuestion.owner.reputation)
        Assert.assertEquals(soQuestionData.ownerData.userId, soQuestion.owner.userId)
        Assert.assertEquals(soQuestionData.questionId, soQuestion.questionId)
        Assert.assertEquals(soQuestionData.questionUrl, soQuestion.questionUrl)
        Assert.assertEquals(soQuestionData.score, soQuestion.score)
        Assert.assertEquals(soQuestionData.tags?.size, soQuestion.tags?.size)
        Assert.assertEquals(soQuestionData.title, soQuestion.title)
        Assert.assertEquals(soQuestionData.viewCount, soQuestion.viewCount)
    }

    @Test
    fun shouldMapSOQuestionDataToSOQUestion(){

        val owner = Owner(5, 1, "avatar", "name", "someurl")

        var soQuestion = SOQuestion(
                ArrayList<String>(),
                owner,
                true,
                1,
                2,
                3,
                DateTime.now(),
                null,
                DateTime.now().minusDays(1),
                5,
                "http://test",
                "First question"
        )

        var soQuestionData = soQuestion.transform()

        Assert.assertEquals(soQuestion.answerCount, soQuestionData.answerCount)
        Assert.assertEquals(soQuestion.creationDate, soQuestionData.creationDate)
        Assert.assertEquals(soQuestion.isAnswered, soQuestionData.isAnswered)
        Assert.assertEquals(soQuestion.lastActivityDate, soQuestionData.lastActivityDate)
        Assert.assertEquals(soQuestion.lastEditDate, soQuestionData.lastEditDate)
        Assert.assertEquals(soQuestion.owner.avatar, soQuestionData.ownerData.avatar)
        Assert.assertEquals(soQuestion.owner.displayName, soQuestionData.ownerData.displayName)
        Assert.assertEquals(soQuestion.owner.profileUrl, soQuestionData.ownerData.profileUrl)
        Assert.assertEquals(soQuestion.owner.reputation, soQuestionData.ownerData.reputation)
        Assert.assertEquals(soQuestion.owner.userId, soQuestionData.ownerData.userId)
        Assert.assertEquals(soQuestion.questionId, soQuestionData.questionId)
        Assert.assertEquals(soQuestion.questionUrl, soQuestionData.questionUrl)
        Assert.assertEquals(soQuestion.score, soQuestionData.score)
        Assert.assertEquals(soQuestion.tags?.size, soQuestionData.tags?.size)
        Assert.assertEquals(soQuestion.title, soQuestionData.title)
        Assert.assertEquals(soQuestion.viewCount, soQuestionData.viewCount)
    }
}