$(document).ready(function () {
    let temp_question = $(".temp-question").html()
    let temp_answer = $(".temp-question .answers").html()

    $(document).on("click", ".add_question", function(){
        let question_id = $(".questions .question").length
        $(".questions").append(temp_question)

        let question = $(".questions .question").filter(":last")
        question.attr('question-id', question_id)
    })

    $(document).on("click", ".add_answer", function(){
        $(this).parent("div").parent("div").children("div.answers").append(temp_answer)
    })

    $(document).on("click", ".delete_answer", function(){
        let current = $(this).parent("div").parent("div")
        if (current.parent("div").children("div.answer").length === 1) {
            alert("Нельзя удалить последний вариант ответа")
            return false
        }
        current.remove()
    })

    $(document).on("click", ".delete_question", function(){
        $(this).parent("div").parent("div").remove()
    })

    $(document).on("click", ".save_test", function(){
        let test = {
            name : $(".test-builder input#name").val(),
            ball : $(".test-builder input#ball").val(),
            description : $(".test-builder textarea#description").val(),
        }

        console.log(test)
    })
})