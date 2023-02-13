import Exams from "../Exams";
import Header from "../../header/Header";
import React from "react";
import MyExam from "./MyExam";
import {Table} from "reactstrap";
import {withRouter} from "react-router-dom";

class MyExams extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            exams: [],
            studentId: "",
            page: 0,
            totalPages: 0,
            totalElements: 0,
            size: 25
        };
    }

    getCurrentUser() {
        return fetch('/api/v1/users/current')
            .then(response => response.json())
    }

    componentDidMount() {
        this.getCurrentUser()
            .then(user => {
                this.setState({
                    studentId: user.id
                })
                fetch(this.getInstancesFetchUrl(user))
                    .then(response => response.json())
                    .then(data => this.setState({
                        exams: data.map(exam => {
                            return exam;
                        }),
                        totalPages: data.totalPages,
                        totalElements: data.totalElements,
                    }));
            })
    }

    getInstancesFetchUrl(user) {
        return '/api/v1/exams/available/' + user.id + '?page=' + this.state.page + '&size='+ this.state.size
    }

    render() {
        const examList = this.state.exams.map(exam => (
            <MyExam key={exam.id} exam={exam} studentId={this.state.studentId}/>
        ));

        return (
            <>
                <Header title={"Мои экзамены"}/>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="20%">Название</th>
                        <th width="25%">Описание</th>
                        <th width="20%">Лимит времени</th>
                        <th width="15%">Последний результат</th>
                        <th width="15%">Лучший результат</th>
                        <th width="5%"/>
                    </tr>
                    </thead>
                    <tbody>
                    {examList}
                    </tbody>
                </Table>
            </>
        )
    }
}

export default withRouter(MyExams)