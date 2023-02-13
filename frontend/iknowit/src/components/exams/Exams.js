import React from "react"
import Header from "../header/Header";
import Exam from "./Exam";
import {Table} from "reactstrap";
import {withRouter} from "react-router-dom";

class Exams extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            exams: [],
            page: 0,
            totalPages: 0,
            totalElements: 0,
            size: 25
        };
    }

    getFetchUrl(user) {
        if (user.role === 'ADMINISTRATOR') {
            return '/api/v1/exams?page=' + this.state.page + "&size=" + this.state.size;
        } else {
            return '/api/v1/exams?page=' + this.state.page + "&size=" + this.state.size + '$ownerId=' + user.id;
        }
    }

    getCurrentUser() {
        return fetch('/api/v1/users/current')
            .then(response => response.json())
    }

    componentDidMount() {
        this.getCurrentUser()
            .then(user => {
                fetch(this.getFetchUrl(user))
                    .then(response => response.json())
                    .then(data => this.setState({
                        exams: data.content.map(exam => {
                            return exam;
                        }),
                        totalPages: data.totalPages,
                        totalElements: data.totalElements,
                    }));
            })
    }

    navigateToCreatePage = () => {
        this.props.history.push('exam/create');
    }

    render() {
        const examList = this.state.exams.map(exam => (
            <Exam
                key={exam.id}
                exam={exam}
            />
        ));

        return (
            <>
                <Header title={"Экзамены"}/>
                <button type="button" onClick={this.navigateToCreatePage} className="btn btn-secondary btn-lg btn-block">Создать новый экзамен</button>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="25%">Название</th>
                        <th width="25%">Описание</th>
                        <th width="20%">Лимит времени</th>
                        <th width="20%"/>
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

export default withRouter(Exams)