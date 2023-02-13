import React from "react"
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class MyExam extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }

    componentDidMount() {
        fetch(this.getStudentInstancesFetchUrl(this.props.exam.id, this.props.studentId))
            .then(response => response.json())
            .then(data => this.setState({
                lastResult: this.getLastResult(data.content),
                bestResult: this.getBestResult(data.content)
            }))
    }

    getBestResult(instances) {
        let bestResult = 0;
        instances.forEach(instance => {
            if (instance.examStatus === "COMPLETE") {
                if (instance.score > bestResult) {
                    bestResult = instance.score;
                }
            }
        })
        return bestResult;
    }

    getLastResult(instances) {
        for (let i = 0; i < instances.length; i++) {
            if (instances[i].examStatus === "COMPLETE") {
                return instances[i].score;
            }
        }
        return "-";
    }

    getMinutes(seconds) {
        return Math.floor(seconds / 60);
    }

    getStudentInstancesFetchUrl(examId, studentId) {
        return "/api/v1/exam-instances?examId=" + examId + "&studentId=" + studentId
    }

    render() {
        return (
            <tr key={this.props.exam.id}>
                <td style={{whiteSpace: 'nowrap'}}>{this.props.exam.name}</td>
                <td>{this.props.exam.description}</td>
                <td>{this.getMinutes(this.props.exam.timeLimit)} мин</td>
                <td>{this.state.lastResult ? this.state.lastResult : "-"} / 100 %</td>
                <td>{this.state.bestResult ? this.state.bestResult : "-"} / 100 %</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="secondary" tag={Link} to={"/user/" + this.props.exam.id}>Просмотр</Button>
                    </ButtonGroup>
                </td>
            </tr>
        )
    }
}

export default MyExam