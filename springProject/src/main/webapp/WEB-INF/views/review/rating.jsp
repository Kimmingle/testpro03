<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.rating__input {
	display: none; /* 라디오버튼 hide */
}

.rating__label .star-icon {
	width: 24px;
	height: 24px;
	display: block;
	background-image: url("../images/ico-star-empty.svg");
	background-repeat: no-repeat;
}

.rating__label--full .star-icon {
	background-position: right;
}
.rating__label--half .star-icon {
	background-position: left;
}

</style>
</head>
<body>
<div class="text-center border-bottom mt-3">
            <h6>별점을 등록하세요</h6>
            
            <div class="rating">
    <label class="rating__label rating__label--half" for="starhalf">
        <input type="radio" id="starhalf" class="rating__input" name="rating" value="">
        <span class="star-icon"></span>
    </label>
    <label class="rating__label rating__label--full" for="star1">
        <input type="radio" id="star1" class="rating__input" name="rating" value="">
        <span class="star-icon"></span>
    </label>
  	...
</div>
            
            
            <div class="d-flex justify-content-center align-items-center">

                <fieldset class="rate">
                    <input type="radio" id="rating10" name="rating" value="10.0" class="rating"><label for="rating10" title="5점"></label>
                    <input type="radio" id="rating9" name="rating" value="9.0"  class="rating"><label class="half" for="rating9" title="4.5점"></label>
                    <input type="radio" id="rating8" name="rating" value="8.0"  class="rating"><label for="rating8" title="4점"></label>
                    <input type="radio" id="rating7" name="rating" value="7.0"  class="rating"><label class="half" for="rating7" title="3.5점"></label>
                    <input type="radio" id="rating6" name="rating" value="6.0"  class="rating"><label for="rating6" title="3점"></label>
                    <input type="radio" id="rating5" name="rating" value="5.0"  class="rating"><label class="half" for="rating5" title="2.5점"></label>
                    <input type="radio" id="rating4" name="rating" value="4.0"  class="rating"><label for="rating4" title="2점"></label>
                    <input type="radio" id="rating3" name="rating" value="3.0"  class="rating"><label class="half" for="rating3" title="1.5점"></label>
                    <input type="radio" id="rating2" name="rating" value="2.0"  class="rating"><label for="rating2" title="1점"></label>
                    <input type="radio" id="rating1" name="rating" value="1.0"  class="rating"><label class="half" for="rating1" title="0.5점"></label>
                </fieldset>
<%--                <c:if test="${mem_num != null}">--%>
                <button class="btn rating btn-outline-success" type="button" id="rating_btn">등록</button>
<%--                </c:if>--%>
                <h3 class="mt-2 ps-2 pe-2 text-muted">/</h3>
                <i class="bi bi-star-fill me-1"></i>
                <div id="result"><%--total--%>
                <h3 class="mt-2">
                    <fmt:formatNumber value="${ratingAvg}" pattern="0.0"/>
                  </h3> <%-- total--%>
                </div>
            </div>
        </div>
        
        <script>
    $("#rating_btn").on("click", function () {
        const review_num = ${review.review_num};
        const rating = $(".rating:checked").val();

        console.log("rate = " + rating);
        console.log("review_num = " + review_num);

        const data = {
            review_num: review_num,
            rating: rating
        };

        $.ajax({
            data: data,
            type: 'POST',
            url: "${path}/review/ratinginsert",
            success: function(result){
                if(confirm("별점 등록 완료")){

                    $("#result").html(result);
                }
            }
        });
    });
    
    
    const rateWrap = document.querySelectorAll('.rating'),
    label = document.querySelectorAll('.rating .rating__label'),
    input = document.querySelectorAll('.rating .rating__input'),
    labelLength = label.length,
    opacityHover = '0.5';

let stars = document.querySelectorAll('.rating .star-icon');

checkedRate();


rateWrap.forEach(wrap => {
    wrap.addEventListener('mouseenter', () => {
        stars = wrap.querySelectorAll('.star-icon');

        stars.forEach((starIcon, idx) => {
            starIcon.addEventListener('mouseenter', () => {
                if (wrap.classList.contains('readonly') == false) {
                    initStars(); // 기선택된 별점 무시하고 초기화
                    filledRate(idx, labelLength);  // hover target만큼 별점 active

                    // hover 시 active된 별점의 opacity 조정
                    for (let i = 0; i < stars.length; i++) {
                        if (stars[i].classList.contains('filled')) {
                            stars[i].style.opacity = opacityHover;
                        }
                    }
                }
            });

            starIcon.addEventListener('mouseleave', () => {
                if (wrap.classList.contains('readonly') == false) {
                    starIcon.style.opacity = '1';
                    checkedRate(); // 체크된 라디오 버튼 만큼 별점 active
                }
            });

            // rate wrap을 벗어날 때 active된 별점의 opacity = 1
            wrap.addEventListener('mouseleave', () => {
                if (wrap.classList.contains('readonly') == false) {
                    starIcon.style.opacity = '1';
                }
            });

            // readonnly 일 때 비활성화
            wrap.addEventListener('click', (e) => {
                if (wrap.classList.contains('readonly')) {
                    e.preventDefault();
                }
            });
        });
    });
});

// target보다 인덱스가 낮은 .star-icon에 .filled 추가 (별점 구현)
function filledRate(index, length) {
    if (index <= length) {
        for (let i = 0; i <= index; i++) {
            stars[i].classList.add('filled');
        }
    }
}

// 선택된 라디오버튼 이하 인덱스는 별점 active
function checkedRate() {
    let checkedRadio = document.querySelectorAll('.rating input[type="radio"]:checked');


    initStars();
    checkedRadio.forEach(radio => {
        let previousSiblings = prevAll(radio);

        for (let i = 0; i < previousSiblings.length; i++) {
            previousSiblings[i].querySelector('.star-icon').classList.add('filled');
        }

        radio.nextElementSibling.classList.add('filled');

        function prevAll() {
            let radioSiblings = [],
                prevSibling = radio.parentElement.previousElementSibling;

            while (prevSibling) {
                radioSiblings.push(prevSibling);
                prevSibling = prevSibling.previousElementSibling;
            }
            return radioSiblings;
        }
    });
}

// 별점 초기화 (0)
function initStars() {
    for (let i = 0; i < stars.length; i++) {
        stars[i].classList.remove('filled');
    }
}
    </script>

</body>
</html>