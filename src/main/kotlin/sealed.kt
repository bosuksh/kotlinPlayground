import java.lang.Exception

sealed class Result {
    data class Success(val data: String) : Result()
    data class Error(val exception: Exception) : Result()
    object InProgress : Result()

}


fun main() {
    var parsedResult: Result = Result.Success("Sealed Class are used for representing...")
    showResult(parsedResult)

    parsedResult = Result.Error(Exception("Got error while parsing this url"))
    showResult(parsedResult)

    parsedResult = Result.InProgress
    showResult(parsedResult)
}

fun showResult(result: Result) {
    when(result) {
        is Result.Success -> {
            println("Success: ${result.data}")
        }
        is Result.Error -> {
            println("Exception: ${result.exception}")
        }
        is Result.InProgress -> {
            println("In Progress")
        }
    }
}