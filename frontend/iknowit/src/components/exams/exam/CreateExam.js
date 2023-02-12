import React from "react"
import Header from "../../header/Header";
import {Button, Table} from "reactstrap";
import {withRouter} from "react-router-dom";
import {CanvasJSChart} from 'canvasjs-react-charts'

class CreateExam extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            description: "",
            timeLimit: -1,
            userId: "",

            knowledge: 0,
            comprehension: 0,
            application: 0,
            analysis: 0,
            synthesis: 0,
            evaluation: 0,
            questions: [],

            options: {
                exportEnabled: false,
                animationEnabled: true,
                title: {
                    text: "Questions by Bloom Level"
                },
                data: [{
                    type: "pie",
                    startAngle: 100,
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    // dataPoints: []
                    dataPoints: [
                        { y: 6, label: "Knowledge" },
                        { y: 26, label: "Comprehension" },
                        { y: 37, label: "Application" },
                        { y: 5, label: "Analysis" },
                        { y: 4, label: "Synthesis" },
                        { y: 22, label: "Evaluation" }
                    ]
                }]
            }
        };
    }

    getCurrentUser() {
        return fetch('/api/v1/users/current')
            .then(response => response.json())
    }

    componentDidMount() {
        this.getCurrentUser()
            .then(user => this.setState({
                userId: user.id
            }))
    }

    createNewQuestion = () => {
        this.props.history.push('exam/create');
    }

    render() {
        const stat = this.state.options.data[0].dataPoints.length > 0 ? <div>
                <CanvasJSChart options = {this.state.options}/>
            </div> : "";

        return (
            <>
                <div className="flex-fill">
                    <Header title={"Create new exam"}/>
                    <div className="form-group">
                        <label>Exam name</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                               placeholder="Exam name"/>
                    </div>
                    <div className="form-group">
                        <label>Description</label>
                        <input type="text" className="form-control" placeholder="Description"/>
                    </div>
                    <div className="form-group">
                        <label>Time limit</label>
                        <input type="text" className="form-control" placeholder="Time limit"/>
                        <small id="emailHelp" className="form-text text-muted">In minutes, without unit</small>
                    </div>

                    {stat}

                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th className="text-center" width="100%">Questions</th>
                        </tr>
                        </thead>
                    </Table>

                    <>
                        <div className="alert alert-info" role="alert">
                            <label>Question type</label>
                            <div className="form-group">
                                <select className="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                                    <option selected>Choose...</option>
                                    <option value="1">Open text</option>
                                    <option value="2">Select</option>
                                    <option value="3">Code</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>Bloom level</label>
                                <select className="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">
                                    <option selected>Choose...</option>
                                    <option value="1">Knowledge</option>
                                    <option value="2">Comprehension</option>
                                    <option value="3">Application</option>
                                    <option value="4">Analysis</option>
                                    <option value="5">Synthesis</option>
                                    <option value="6">Evaluation</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label>Weight</label>
                                <input type="text" className="form-control" placeholder="Question weight"/>
                            </div>
                            <hr className="my-4"/>
                            <div className="form-group">
                                <label>Question content</label>
                                <input type="text" className="form-control" aria-describedby="emailHelp"
                                       placeholder="Question content"/>
                            </div>
                            <div className="form-group">
                                <label>Correct answers</label>
                                <input type="text" className="form-control" aria-describedby="emailHelp"
                                       placeholder="Correct answers"/>
                            </div>
                        </div>
                    </>

                    <button type="button" onClick={this.createNewQuestion} className="btn btn-secondary btn-lg btn-block">+</button>
                </div>
                <hr className="my-4"/>
                <footer className="footer mt-auto py-3 bg-light">
                    <div className="container">
                        <span className="text-light">
                            <Button color="secondary">Save</Button>
                        </span>
                    </div>
                </footer>
            </>
        )
    }
}

export default withRouter(CreateExam)