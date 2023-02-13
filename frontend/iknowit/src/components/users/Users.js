import React from "react"
import Header from "../header/Header";
import {Table} from "reactstrap";
import User from "./User";

class Users extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            users: [],
            page: 0,
            totalPages: 0,
            totalElements: 0,
            size: 25
        };
    }

    getFetchUrl() {
        return '/api/v1/users?page=' + this.state.page + "&size=" + this.state.size;
    }

    componentDidMount() {
        fetch(this.getFetchUrl())
            .then(response => response.json())
            .then(data => this.setState({
                users: data.content.map(user => {
                    user.selected = false;
                    return user;
                }),
                totalPages: data.totalPages,
                totalElements: data.totalElements,
            }));
    }

    handleSelect = id => {
        this.setState(prevState => ({
            users: prevState.users.map(user => {
                if (user.id === id) {
                    return {
                        ...user,
                        selected: !user.selected
                    }
                }
                return user;
            })
        }))
    };

    render() {
        const userList = this.state.users.map(user => (
            <User
                key={user.id}
                user={user}
                handleSelect={this.handleSelect}
            />
        ));

        return (
            <>
                <Header title={"Пользователи"}/>
                <Table className="mt-4">
                    <thead>
                    <tr>
                        <th width="5%"/>
                        <th width="25%">Имя</th>
                        <th width="25%">Email</th>
                        <th width="25%">Роль</th>
                        <th width="20%"/>
                    </tr>
                    </thead>
                    <tbody>
                    {userList}
                    </tbody>
                </Table>
            </>
        )
    }
}

export default Users