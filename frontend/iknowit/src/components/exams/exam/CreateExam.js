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
                    text: "Уровни вопросов"
                },
                data: [{
                    type: "pie",
                    startAngle: 100,
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    dataPoints: []
                    // dataPoints: [
                    //     { y: 6, label: "Знания" },
                    //     { y: 26, label: "Понимание" },
                    //     { y: 37, label: "Применение" },
                    //     { y: 5, label: "Анализ" },
                    //     { y: 4, label: "Ситез" },
                    //     { y: 22, label: "Оценка" }
                    // ]
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
                    <Header title={"Создать новый экзамен"}/>
                    <div className="form-group">
                        <label>Название экзамена</label>
                        <input type="text" className="form-control" aria-describedby="emailHelp"
                               placeholder="Название экзамена"/>
                    </div>
                    <div className="form-group">
                        <label>Описание</label>
                        <input type="text" className="form-control" placeholder="Описание"/>
                    </div>
                    <div className="form-group">
                        <label>Лимит времени</label>
                        <input type="text" className="form-control" placeholder="Лимит времени"/>
                        <small id="emailHelp" className="form-text text-muted">В минутах, без единиц измерения</small>
                    </div>

                    {stat}

                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th className="text-center" width="100%">Вопросы</th>
                        </tr>
                        </thead>
                    </Table>

                    {/*<>*/}
                    {/*    <div className="alert alert-info" role="alert">*/}
                    {/*        <label>Тип вопроса</label>*/}
                    {/*        <div className="form-group">*/}
                    {/*            <select className="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">*/}
                    {/*                <option selected>Выберите...</option>*/}
                    {/*                <option value="1">С текстовым ответом</option>*/}
                    {/*                <option value="2">С выбором вариантов</option>*/}
                    {/*                <option value="3">Код</option>*/}
                    {/*            </select>*/}
                    {/*        </div>*/}
                    {/*        <div className="form-group">*/}
                    {/*            <label>Уровень вопроса</label>*/}
                    {/*            <select className="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref">*/}
                    {/*                <option selected>Выберите...</option>*/}
                    {/*                <option value="1">Знания</option>*/}
                    {/*                <option value="2">Понимание</option>*/}
                    {/*                <option value="3">Применение</option>*/}
                    {/*                <option value="4">Анализ</option>*/}
                    {/*                <option value="5">Синтез</option>*/}
                    {/*                <option value="6">Оценка</option>*/}
                    {/*            </select>*/}
                    {/*        </div>*/}
                    {/*        <div className="form-group">*/}
                    {/*            <label>Вес вопроса</label>*/}
                    {/*            <input type="text" className="form-control" placeholder="Вес вопроса"/>*/}
                    {/*        </div>*/}
                    {/*        <hr className="my-4"/>*/}
                    {/*        <div className="form-group">*/}
                    {/*            <label>Текст вопроса</label>*/}
                    {/*            <input type="text" className="form-control" aria-describedby="emailHelp"*/}
                    {/*                   placeholder="Текст вопроса"/>*/}
                    {/*        </div>*/}
                    {/*        <div className="form-group">*/}
                    {/*            <label>Правильные ответы</label>*/}
                    {/*            <input type="text" className="form-control" aria-describedby="emailHelp"*/}
                    {/*                   placeholder="Правильные ответы"/>*/}
                    {/*        </div>*/}
                    {/*    </div>*/}
                    {/*</>*/}

                    <button type="button" onClick={this.createNewQuestion} className="btn btn-secondary btn-lg btn-block">+</button>
                </div>
                <hr className="my-4"/>
                <footer className="footer mt-auto py-3 bg-light">
                    <div className="container">
                        <span className="text-light">
                            <Button color="secondary">Сохранить</Button>
                        </span>
                    </div>
                </footer>
            </>
        )
    }
}

export default withRouter(CreateExam)