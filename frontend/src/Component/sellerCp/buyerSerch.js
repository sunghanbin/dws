import React from "react";
import axios from "axios";
import { Form, Button, Table, ButtonGroup, ButtonToolbar } from "react-bootstrap";
import dotenv from "dotenv";
import { siguSearch } from "../GlobalF"
import { connect } from "react-redux";
import { change } from "../../redux/store";
dotenv.config();



class buyerSerch extends React.Component {

    state = {
        SiGuCd: "",
        totalpage: 0,
        pagecount: 0
    }



    // 다음 버튼 클릭시 --> 버튼 value 를 Pagefivebtn로 전달
    Nextbtn = (e) => {
        console.log(e.target.value);
        this.setState(state => {
            return { pagecount: Number(e.target.value) };
        })

        this.ListAddBtn(this.state.pagecount);
    }

    // 5개이하의 버튼 그룹 생성
    FiveBelowbtn = () => {
        var pageC = this.state.pagecount
        var start = 0;
        if (pageC > 0) {
            start = pageC;
        }
        let btngroup = document.querySelector(".btn-group");
        for (var i = start; i < this.state.totalpage; i++) {
            var addbtn = document.createElement("button");
            addbtn.type = "button";
            addbtn.classList.add("btn", "btn-primary");
            addbtn.value = i;
            addbtn.innerText = i + 1;
            btngroup.appendChild(addbtn);
        }
    }


    //  다음버튼 있는용
    PageFivebtn = () => {
        var pageC = this.state.pagecount
        var btngroup = document.querySelector(".btn-group");
        // 버튼 그룹 갯수
        let pGroup = 5;


        //  기존버튼 초기화
        while (btngroup.hasChildNodes()) { btngroup.removeChild(btngroup.firstChild); }


        // 5개씩....
        for (var i = pageC; i < pageC + pGroup; i++) {

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
        addnext.addEventListener("click", this.Nextbtn);
        btngroup.appendChild(addnext);

    }

    // 
    ListAddBtn = () => {
        var totalp = this.state.totalpage;

        let maxcount = this.state.pagecount + (totalp % 5);

        // 마지막 다음탭과 토탈값 나머지 더했을때 토탈과 같을때  마지막 버튼그룹판단
        // 마지막버튼그룹아니라면 if
        //  마지막버튼그룹이라면 else 마지막 버튼 그룹말고도 최초 5이하의 버튼그룹 
        if (maxcount !== totalp) {

            this.PageFivebtn();


        } else if (maxcount === totalp || totalp < 5) {

            this.FiveBelowbtn();

        }
    }



    // 시.도 선택 따라 시,구 셀렉박스 변경
    ChageSelect = () => {
        const select = document.querySelector(".search_select1").options;
        const selectcode = Number(select[select.selectedIndex].value);
        const sigudata = siguSearch(this.state.SiGuCd, selectcode);
        const siguCd = document.querySelector(".search_select2");
        siguCd.options.length = 0;

        siguCd.append("<option></option>");
        console.log(sigudata);
        for (var i = 0; i < sigudata.length; i++) {
            var opdata = Object.values(sigudata[i]);
            // new Option(i,i) //새로운 Option(텍스트,value)
            siguCd.options[i + 1] = new Option(opdata[3], opdata[0]);
        }



    }


    //  시,도 데이터 조회
    Citycode = async () => {
        axios.get(`${process.env.REACT_APP_RESTAPI_SERVER}/citycode`)
            .then((response) => {
                const {
                    data
                } = response;
                const citycCd = document.querySelector(".search_select1");
                for (var i = 0; i < data.length; i++) {
                    var opdata = Object.values(data[i]);
                    // new Option(i,i) //새로운 Option(텍스트,value)
                    citycCd.options[i + 1] = new Option(opdata[1], opdata[0]);
                }

            })
            .catch((error) => {

            })

    }

    //  시,구 데이터 조회
    SiGucode = async () => {
        axios.get(`${process.env.REACT_APP_RESTAPI_SERVER}/sigucode`)
            .then((response) => {
                const {
                    data
                } = response;
                console.log(data);


                this.setState(state => {
                    return { SiGuCd: data }
                });


            })
            .catch((error) => {

            })

    }

    // 렌더링 시 기본검색
    SurchAllEvent = async () => {

        axios
            .get(
                `${process.env.REACT_APP_RESTAPI_SERVER}/buyerall`, {
                params: {
                    page: this.state.pagecount,
                    size: 50
                }
            }
            )
            .then((response) => {
                const {
                    data: { content, totalPages },
                } = response;

                this.setState(state => {
                    return { totalpage: totalPages };
                })

                // 기본 조회
                const buyertable_head = document.querySelector(".buyertable_head");
                const buyertable_row = document.querySelector(".buyertable_row");
                const columconut = buyertable_head.children[0].childElementCount;
                const rowleng = content.length;

                for (var i = 0; i < rowleng; i++) {
                    var row = buyertable_row.insertRow(i);
                    var rowdata = content[i];
                    row.insertCell(0).innerHTML = i + 1;
                    for (var j = 0; j < columconut - 1; j++) {
                        var celldata = Object.values(rowdata);
                        var cell = row.insertCell(j + 1);
                        cell.innerHTML = celldata[j];

                    }
                }

                console.log(this.props);
                this.ListAddBtn(this.state.totalpage, this.state.pagecount);
            })
            .catch((error) => {

            })

    }

    //  렌더링후 ..
    componentDidMount() {
        this.SiGucode();
        this.Citycode();
        this.SurchAllEvent();



    }



    SurchEvent = async (e) => {
    }






    render() {
        return (

            <>
                <span className="menu_title"> 거래처 검색</span>
                <Form className="search_from" onSubmit={this.SurchEvent}>
                    <Form.Group className="search_fg">
                        <Form.Label>사업자 번호</Form.Label>
                        <Form.Control type="text" placeholder="Search" className="search_input" />
                    </Form.Group>
                    <Form.Group className="search_fg">
                        <Form.Label>상호 명</Form.Label>
                        <Form.Control type="text" placeholder="Search" className="search_input" />
                    </Form.Group>
                    <Form.Group className="search_fg">
                        <Form.Label>대표자 명</Form.Label>
                        <Form.Control type="text" placeholder="Search" className="search_input" />
                    </Form.Group>


                    <Form.Group className="search_fg4" onChange={this.ChageSelect}>
                        <Form.Label>시,도 단위</Form.Label>
                        <Form.Control as="select" className="search_select1">
                            <option></option>
                        </Form.Control>
                    </Form.Group>
                    <Form.Group className="search_fg">
                        <Form.Label>시,구 단위</Form.Label>
                        <Form.Control as="select" className="search_select2">
                            <option></option>
                        </Form.Control>
                    </Form.Group>
                    <Button variant="primary" type="submit" className="search_btn">검색</Button>
                </Form>
                <div className="buyer_tb" >
                    <Table striped bordered size="sm">
                        <thead className="buyertable_head">
                            <tr>
                                <th>No.</th>
                                <th>사업자 번호</th>
                                <th>상호 명</th>
                                <th>대표자 명</th>
                                <th>전화번호</th>
                                <th>업종코드</th>
                                <th>종목코드</th>
                                <th>주소</th>
                            </tr>
                        </thead>
                        <tbody className="buyertable_row">

                        </tbody>
                    </Table>
                </div>
                <div className="buyer-search_btnG">

                    <ButtonGroup>

                    </ButtonGroup>
                </div>

            </>



        )
    }

}

// //  redux 에서 state 가져오기
// const mapStateToProps = (state) => {
//     // console.log();
//     return { pagecount: state };
// }
// const mapDispatchToProps = (dispatch) => {
//     return { counter: pagecount => dispatch(change(pagecount)) };

// }

export default buyerSerch;


