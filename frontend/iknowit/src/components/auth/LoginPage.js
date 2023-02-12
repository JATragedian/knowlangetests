import React, { Component } from "react";
import { withRouter } from 'react-router-dom';
import Header from "../header/Header";

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
                <Header title={"Login"}/>
                <form className="form-layout" onSubmit={this.handleLogin}>
                    <input name="email" type="text" placeholder="Email" value={this.state.email}
                           onChange={this.handleChange} className="submit-text"/>
                    <input name="password" type="text" placeholder="Password" value={this.state.password}
                           onChange={this.handleChange} className="submit-text"/>
                    <button className="submit-button">Log-in</button>
                </form>
                <button className="submit-button" onClick={() => this.handleRegister()}>Register</button>
            </>
        )
    }
}

export default withRouter(LoginPage)