import React, { Component } from "react";
import {withRouter} from "react-router-dom";
import Header from "../header/Header";

class RegistrationPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            surname: "",
            email: "",
            password: "",
            doubledPassword: ""
        };
    }

    handleChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleRegister = (e) => {
        e.preventDefault();
        if (this.inputIsValid()) {
            this.register();
        } else {
            alert("Check inputted fields!")
        }
    }

    register = () => {

    }

    inputIsValid = () => {
        if (this.state.name.trim() &&
            this.state.surname.trim() &&
            this.state.email.trim() &&
            this.state.password.trim() &&
            this.state.doubledPassword.trim()
        ) {
            if (this.state.password === this.state.doubledPassword) {
                return true;
            }
        }

        return false;
    }

    render() {
        return (
            <>
                <Header title={"Регистрация"}/>
                <form className="form-layout" onSubmit={this.handleRegister}>
                    <input name="name" type="text" placeholder="Name"
                           value={this.state.name} onChange={this.handleChange} className="submit-text"/>
                    <input name="surname" type="text" placeholder="Surname"
                           value={this.state.surname} onChange={this.handleChange} className="submit-text"/>
                    <input name="email" type="text" placeholder="Email"
                           value={this.state.email} onChange={this.handleChange} className="submit-text"/>
                    <input name="password" type="text" placeholder="Password"
                           value={this.state.password} onChange={this.handleChange} className="submit-text"/>
                    <input name="doubledPassword" type="text" placeholder="Confirm password"
                           value={this.state.doubledPassword} onChange={this.handleChange} className="submit-text"/>
                    <button className="submit-button">Log-in</button>
                </form>
            </>
        )
    }
}

export default withRouter(RegistrationPage)