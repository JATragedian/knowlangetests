import React, {Component} from 'react';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import Exams from "./components/exams/Exams";
import MyExams from "./components/exams/student/MyExams";
import Users from "./components/users/Users";
import Students from "./components/users/students/Students";
import LoginPage from "./components/auth/LoginPage";
import RegistrationPage from "./components/auth/RegistrationPage";
import CreateExam from "./components/exams/exam/CreateExam";

class App extends Component {

    render() {
        return (
            <div className="main-container">
                <div className="main-layout">
                    <Router>
                        <Switch>
                            <Route path='/' exact component={Exams}/>
                            <Route path='/exam/create' exact component={CreateExam}/>
                            <Route path='/my-exams' exact component={MyExams}/>
                            <Route path='/log-in' exact component={LoginPage}/>
                            <Route path='/register' exact component={RegistrationPage}/>
                            <Route path='/students' exact component={Students}/>
                            <Route path='/users' exact component={Users}/>
                            <Route path="*" component={Exams}/>
                        </Switch>
                    </Router>
                </div>
            </div>
        )
    }
}

export default App;
