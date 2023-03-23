$(document).ready(function () {
    let temp_question = $(".temp-question").html()
    let temp_answer = $(".temp-question .answers").html()

    $(document).on("click", ".add_question", function () {
        $(".questions").append(temp_question)
    })

    $(document).on("click", ".add_answer", function () {
        $(this).parent("div").parent("div").children("div.answers").append(temp_answer)
    })

    $(document).on("click", ".delete_answer", function () {
        let current = $(this).parent("div").parent("div")
        if (current.parent("div").children("div.answer").length === 1) {
            alert("Нельзя удалить последний вариант ответа")
            return false
        }
        current.remove()
    })

    $(document).on("click", ".delete_question", function () {
        $(this).parent("div").parent("div").remove()
    })

    $(document).on("click", ".save_test", function () {
        let test = {
            id: $(".test-builder input#test_id").val() * 1,
            name: $(".test-builder input#name").val(),
            ball: $(".test-builder input#ball").val() * 1,
            description: $(".test-builder textarea#description").val(),
            questions: Array()
        }

        $(".questions .question").each(function () {
            let questionItem = {
                id: $(this).attr("question-id") * 1,
                questionText: $(this).find("textarea.question_text").val(),
                answers: Array()
            }

            $(this).find("div.answers div.answer").each(function () {
                let answer = {
                    answerText: $(this).find("input.answer_text").val(),
                    answerCheck: $(this).find("input.answer_check").prop("checked")
                }
                questionItem.answers.push(answer)
            })

            test.questions.push(questionItem)
        });

        let data = JSON.stringify(test)

        $.ajax({
            url: "/admin/testBuilder",
            method: "post",
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: data,
            success: function (response) {
                let obj = response
                if (obj.message !== undefined) {
                    addMessage(obj.message)
                    return false;
                }
                if (obj.location !== undefined){
                    window.location = obj.location
                    return false
                }
                alert("Не уалось распознать ответ сервера")
                console.log(obj)
            }
        });

        console.log(data)
    })
})