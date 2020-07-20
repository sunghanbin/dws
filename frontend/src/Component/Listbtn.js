import React from "react";
import { change } from "../redux/store";
import { connect } from "react-redux";










// 다음 버튼 클릭시 --> 버튼 value 를 Pagefivebtn로 전달


const Nextbtn = () => {
    this.setState(state => {
        return { pagecount: 3 };
    })

    // ListAddBtn(Number(e.target.value));
    // ListAddBtn(this.state.pagecount);
}

// 5개이하의 버튼 그룹 생성
const FiveBelowbtn = (totalpage, pagecount) => {
    var start = 0;
    if (pagecount > 0) {
        start = pagecount;
    }
    let btngroup = document.querySelector(".btn-group");
    for (var i = start; i < totalpage; i++) {
        var addbtn = document.createElement("button");
        addbtn.type = "button";
        addbtn.classList.add("btn", "btn-primary");
        addbtn.value = i;
        addbtn.innerText = i + 1;
        btngroup.appendChild(addbtn);
    }
}


//  다음버튼 있는용
const PageFivebtn = (pagecount) => {
    console.log(pagecount);
    let btngroup = document.querySelector(".btn-group");
    // 버튼 그룹 갯수
    let pGroup = 5;
    console.log(btngroup);

    //  기존버튼 초기화
    while (btngroup.hasChildNodes()) { btngroup.removeChild(btngroup.firstChild); }
    console.log("aaa");
    //  페이지가 5개 이상일시  다음버튼 추가 
    if (pagecount % pGroup === 0 || pagecount === 0) {
        // 5개씩....
        for (var i = pagecount; i < pagecount + pGroup; i++) {
            var addbtn = document.createElement("button");
            addbtn.type = "button";
            addbtn.classList.add("btn", "btn-primary");
            addbtn.value = i;
            addbtn.innerText = i + 1;
            btngroup.appendChild(addbtn);
        }
        let addnext = document.createElement("button");
        addnext.type = "button";
        addnext.classList.add("btn", "btn-primary");
        let btnval = Number(btngroup.lastChild.value) + 1;
        addnext.value = btnval;
        addnext.innerText = "다음";
        addnext.addEventListener("click", Nextbtn);
        btngroup.appendChild(addnext);

    }

}

//  
export const ListAddBtn = (totalpage, pagecount) => {
    // console.log(props);
    console.log("df");
    console.log(totalpage);

    let maxcount = pagecount + (totalpage % 5);

    // 마지막 다음탭과 토탈값 나머지 더했을때 토탈과 같을때  마지막 버튼그룹판단
    // 마지막버튼그룹아니라면 if
    //  마지막버튼그룹이라면 else 마지막 버튼 그룹말고도 최초 5이하의 버튼그룹 
    if (maxcount !== totalpage) {
        console.log("d");
        console.log(pagecount);
        PageFivebtn(pagecount);


    } else if (maxcount === totalpage || totalpage < 5) {

        FiveBelowbtn(totalpage, pagecount);

    }
}




//  최초,전체검색용 
//  페이지별 검색용을 따로 만들어야한다.
// page버튼 
// const PageAddbtn = (totalpage,pagecount) => {

//     if (totalpage < 5) {
//         this.FiveBelowbtn(totalpage,pagecount);

//     } else {
//         this.Pagefivebtn(pagecount);

//     }
// }

// const mapStateToProps = (state) => {
//     // console.log();
//     return { pagecount: state };
// }


// const mapDispatchToProps = (dispatch) => {
//     return { counter: pagecount => dispatch(change(pagecount)) };

// }



