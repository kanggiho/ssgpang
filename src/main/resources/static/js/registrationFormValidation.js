let idCheckPass = false;
let emailCheckPass = false;
let telCheckPass = false;

// 실시간 아이디 입력 검증
document.getElementById("id").addEventListener("input", function () {
    const id = document.getElementById("id").value.trim();
    const idCheckResult = document.getElementById("idCheckResult");
    const idRegex = /^[a-zA-Z0-9]{4,}$/;

    if (!id) {
        idCheckResult.textContent = "";
        return;
    }

    if (idRegex.test(id)) {
        idCheckResult.textContent = "";
        idCheckPass = true;
    } else {
        idCheckResult.style.color = "crimson";
        idCheckResult.textContent = "ID는 4자 이상, 영문자와 숫자만 포함해야 합니다.";
        idCheckPass = false;
    }
});

// 비밀번호 일치 및 유효성 실시간 검사
function checkPasswordMatch() {
    const password = document.getElementById("password").value.trim();
    const password2 = document.getElementById("password2").value.trim();
    const pwCheckResult = document.getElementById("pwCheckResult");

    // 비밀번호 유효성 검사 (영어, 숫자, 특수문자 포함, 8자 이상)
    const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;

    // 두 번째 비밀번호 입력란이 비어있을 때
    if (!password2) {
        pwCheckResult.textContent = "";
        return;
    }

    // 비밀번호 형식 유효성 검사
    if (!passwordPattern.test(password)) {
        pwCheckResult.style.color = "crimson";
        pwCheckResult.textContent = "비밀번호는 영어, 숫자, 특수문자를 포함해 8자 이상이어야 합니다.";
        return;
    }

    // 비밀번호가 일치하는지 검사
    if (password === password2) {
        pwCheckResult.style.color = "green";
        pwCheckResult.textContent = "비밀번호가 일치합니다.";
    } else {
        pwCheckResult.style.color = "crimson";
        pwCheckResult.textContent = "비밀번호가 일치하지 않습니다.";
    }
}

document.getElementById("password").addEventListener("input", checkPasswordMatch);
document.getElementById("password2").addEventListener("input", checkPasswordMatch);

// ID 중복 체크 버튼 이벤트
document.getElementById("idCheck").addEventListener("click", function () {
    const id = document.getElementById("id").value.trim();
    if (!id) {
        alert("아이디를 입력해주세요.");
        return;
    }
    axios.get("/user/member/checkId?id=" + id).then(function (response) {
        const result = response.data;
        const idCheckResult = document.getElementById("idCheckResult");
        if (result) {
            idCheckPass = true;
            idCheckResult.style.color = "green";
            idCheckResult.textContent = "사용 가능한 ID";
        } else {
            idCheckPass = false;
            idCheckResult.style.color = "crimson";
            idCheckResult.textContent = "이미 사용 중인 ID";
        }
    }).catch(function (error) {
        alert("Error! ID 중복 체크를 확인해주세요.");
        console.error(error);
    });
});

// 전화번호 입력 시 실시간 유효성 검사
document.getElementById("tel").addEventListener("input", function () {
    // 공백 제거
    const tel = document.getElementById("tel").value.trim();
    // 전화번호 입력 결과를 표시할 요소
    const telCheckResult = document.getElementById("telCheckResult");

    // 전화번호가 올바른 형식인지 검사
    const telRegex = /^010\d{8}$/;


    // 전화번호가 비어있을 경우
    if (!tel) {
        telCheckResult.style.color = "crimson";
        telCheckResult.textContent = "전화번호를 입력하세요.";
        telCheckPass = false;
        return;
    }

    // 전화번호 형식이 올바르지 않을 때
    if (!telRegex.test(tel)) {
        telCheckResult.style.color = "crimson";
        telCheckResult.textContent = "전화번호는 '010'으로 시작하며, '-'를 제외한 11자리 숫자여야 합니다.";
        telCheckPass = false;
        return;
    }

    // 전화번호가 수정되었을 때 중복 체크
    axios.get("/user/member/checkTel?tel=" + tel)
        .then(function (response) {
            const isTelAvailable = response.data;

            if (!isTelAvailable) {
                telCheckResult.style.color = "green";
                telCheckResult.textContent = "사용 가능한 전화번호입니다.";
                telCheckPass = true;  // 전화번호 중복 체크 완료
            } else {
                telCheckResult.style.color = "crimson";
                telCheckResult.textContent = "이미 사용 중인 전화번호입니다.";
                telCheckPass = false;  // 중복된 전화번호일 경우
            }
        })
        .catch(function (error) {
            console.error(error);
            telCheckResult.style.color = "crimson";
            telCheckResult.textContent = "전화번호 확인 중 오류가 발생했습니다.";
            telCheckPass = false;
        });
});


document.getElementById("email").addEventListener("input", function () {
    const email = document.getElementById("email").value.trim();
    const emailCheckResult = document.getElementById("emailCheckResult");

    // 이메일 형식 유효성 검사
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;

    // 이메일이 비어있을 때
    if (!email) {
        emailCheckResult.style.color = "crimson";
        emailCheckResult.textContent = "이메일을 입력하세요.";
        emailCheckPass = false;
        return;
    }

    // 이메일 형식이 올바르지 않을 때
    if (!emailPattern.test(email)) {
        emailCheckResult.style.color = "crimson";
        emailCheckResult.textContent = "잘못된 이메일 형식입니다.";
        emailCheckPass = false;
        return;
    }

    // 이메일이 수정되었을 때 중복 체크
    axios.get("/user/member/checkEmail?email=" + encodeURIComponent(email))
        .then(function (response) {
            const isEmailAvailable = response.data;

            if (!isEmailAvailable) {
                emailCheckResult.style.color = "green";
                emailCheckResult.textContent = "사용 가능한 이메일입니다.";
                emailCheckPass = true;  // 이메일 중복 체크 완료
            } else {
                emailCheckResult.style.color = "crimson";
                emailCheckResult.textContent = "이미 사용 중인 이메일입니다.";
                emailCheckPass = false;  // 중복된 이메일일 경우
            }
        })
        .catch(function (error) {
            console.error(error);
            emailCheckResult.style.color = "crimson";
            emailCheckResult.textContent = "이메일 확인 중 오류가 발생했습니다.";
            emailCheckPass = false;
        });
});

// 회원가입 버튼 클릭 이벤트
document.getElementById("registerBtn").addEventListener("click", function (event) {
    event.preventDefault();

    const id = document.getElementById("id").value.trim();
    const pw = document.getElementById("password").value.trim();
    const pw2 = document.getElementById("password2").value.trim();
    const name = document.getElementById("name").value.trim();
    const tel = document.getElementById("tel").value.trim();
    const email = document.getElementById("email").value.trim();

    // 유효성 검사
    if (pw !== pw2) {
        alert("PW가 일치하지 않습니다!");
        return;
    }

    if (!id || !pw || !name || !tel || !email) {
        alert("입력란이 비어있습니다!");
        return;
    }

    if (!idCheckPass) {
        alert("ID 중복체크가 완료되지 않았습니다!");
        return;
    }

    if (!emailCheckPass) {
        alert("이메일 중복체크가 완료되지 않았습니다!");
        return;
    }

    if (!telCheckPass) {
        alert("전화번호 중복체크가 완료되지 않았습니다!");
        return;
    }
    // 모든 유효성 검사를 통과하면 폼 제출
    document.getElementById("signUpForm").submit();
});