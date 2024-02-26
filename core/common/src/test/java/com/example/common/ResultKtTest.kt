package com.example.common

import app.cash.turbine.test
import com.example.common.result.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class ResultKtTest {

    @Test
    fun test_result_catches_errors() = runTest {
        flow {
            emit(1)
            throw Exception("Test Exception")
        }
            .asResult()
            .test {

                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success(1), awaitItem())

                when (val errorResult = awaitItem()) {
                    is Result.Error -> assertEquals(
                        "Test Exception",
                        errorResult.exception.message,
                    )
                    Result.Loading,
                    is Result.Success,
                    -> throw IllegalStateException("The flow should have emitted an Error Result")
                }

                awaitComplete()
            }
    }
}
