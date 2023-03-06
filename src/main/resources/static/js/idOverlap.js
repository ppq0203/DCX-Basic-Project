var id_overlap = 2;	//id 중복체크를 진행하였는지 확인하기 위한 변수 0이 되면 진행가능
var oldValId = "";	//기존 아이디를 저장해둘 변수

function checkId(){
	var id = $('#userId').val(); //id값이 "id"인 입력란의 값을 저장
	$.ajax({
	    url:'./idCheck', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    data:{id:id},
	    success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
	        if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
	            $('.id_ok').css("display","inline-block"); 
	            $('.id_already').css("display", "none");
	        } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
	            $('.id_already').css("display","inline-block");
	            $('.id_ok').css("display", "none");
	            alert("아이디를 다시 입력해주세요");
	            $('#userId').val('');
	        }
	        id_overlap = cnt;
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
	//console.log(id_overlap);	//id중복체크 확인
};

// userId의 input내용이 변경되면 실행하는 함수
$("#userId").on("propertychange change keyup paste input", function() {
    var currentVal = $(this).val();	// 변경된 아이디 변수
    if(currentVal == oldValId) {	//	변경하기전과 동일하면
        return;	//아래코드를 실행하지 않음
    }
    oldValId = currentVal;	// 변경된 값을 기존값으로 저장
    id_overlap = 2;	// id가 변경되었으므로 아이디 중복체크 다시해야함.
});

