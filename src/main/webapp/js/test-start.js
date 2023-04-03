$(document).ready(function () {
    $(document).on("click", ".save-result-test", function () {
        let testObj = {
            id: $(".test-start input#test_id").val() * 1,
            questions: Array()
        }

        $(".questions .question").each(function () {
            let question = {
                id: $(this).attr("question-id") * 1,
                answers: Array()
            }

            $(this).find("div.answers div.answer").each(function(){
                $(this).find("input.answer_check:checked").each(function(){
                    let answer = {
                        id: $(this).attr("answer-id") * 1
                    }
                    question.answers.push(answer)
                })
            })

            testObj.questions.push(question)
        })

        let data = JSON.stringify(testObj)

        $.ajax({
            url: "/testStart",
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

    })
})