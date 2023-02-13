import React, {Component} from "react";
import {withRouter} from 'react-router-dom';
import Header from "../header/Header";
import {Button, ButtonGroup} from "reactstrap";

class LoginPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: ""
        };
    }

    handleChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    handleLogin = e => {
        e.preventDefault();
        if (this.inputIsValid()) {
            this.login();
        } else {
            alert("Check inputted fields!")
        }
    }

    login = () => {
        fetch('/do-login', {
            method: 'POST',
            body: JSON.stringify(this.state),
            headers: {
                'Content-Type': 'application/json'
            }})
            .then(response => {
                if (response.status === 200) {
                    this.props.history.push("/");
                } else {
                    alert("Login failed");
                }
            });
    }

    inputIsValid = () => {
        return this.state.email.trim() && this.state.password.trim();
    }

    handleRegister = e => {
        this.props.history.push("/register");
    }

    render() {
        return (
            <>
                <Header title={"Логин"}/>
                <div className="form-group">
                    <label htmlFor="exampleInputEmail1">Email</label>
                    <input type="email" className="form-control" onChange={this.handleChange}
                           aria-describedby="emailHelp" placeholder="Email" value={this.state.email}/>
                </div>
                <div className="form-group">
                    <label htmlFor="exampleInputPassword1">Пароль</label>
                    <input type="password" className="form-control"
                           placeholder="Пароль" value={this.state.password}/>
                </div>
                <ButtonGroup>
                    <Button color="secondary" onClick={() => this.handleLogin}>Вход</Button>
                </ButtonGroup>
                <hr className="my-4"/>
                <ButtonGroup>
                    <Button color="secondary" onClick={() => this.handleRegister}>Регистрация</Button>
                </ButtonGroup>
            </>
        )
    }
}

export default withRouter(LoginPage)