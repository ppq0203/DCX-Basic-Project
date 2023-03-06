

function userInfoChange() {
	var phone = document.getElementsByName("userPhone");
	if (userPhone.value == "") {
			alert("휴대폰 번호를 입력해 주세요.");
			idFind.userPhone.focus();
			return;
		}
	if (userInfoChange.userAddress.value == "") {
			alert("휴대폰 번호를 입력해 주세요.");
			idFind.userPhone.focus();
			return;
		}
				
}